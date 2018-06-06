package it.polimi.ingsw.controller;
import it.polimi.ingsw.messages.client.*;
import it.polimi.ingsw.messages.controller.*;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.parsers.GsonParser;
import it.polimi.ingsw.utils.Observer;

public class Controller implements Observer<String>{

    private Game model;
    private RoundHandler handler;
    private ToolCardCheck checker;
    private WindowPatternFactory factory;
    private GsonParser parser;

    /*
        constructors
     */

    public Controller(Game model){
        this.model = model;
        factory = new WindowPatternFactory();
        parser = new GsonParser();
        checker = new ToolCardCheck();
    }

    public Controller(){
        model = new Game();
        factory = new WindowPatternFactory();
        parser = new GsonParser();
        checker = new ToolCardCheck();
    }

    /*
        getters and setters
     */

    public Game getGame(){
        return model;
    }

    public GsonParser getParser() {
        return parser;
    }

    /*
        adds default players to the game, waiting for username and window from each client
     */

    public void newMatch(int nPlayers){
        for(int i = 0; i < nPlayers; i++){
            model.addPlayer(new Player("player" + i, factory.getWindow(), i));
        }
    }

    /*
        sends a login request to all the players in the game
     */

    public void newLoginRequest(){
        //parte il countdown per l'inizio partita: i giocatori che non scelgono username o finestra avranno quelli default
        for(Player p : model.getPlayers()){
            if(p.isOnline()){
                model.notifyMessage(new LoginRequestMessage(), p.getId());
            }
        }
    }

    /*
        creates a new RoundHandler class and initializes the model class
     */

    public void startMatch(){
        model.setGameStarted(true);
        model.initialize();
        handler = new RoundHandler(model.playersSize());
        model.notifyUpdate();
        model.notifyMessage(new NewTurnMessage(), model.getPlayers(handler.getCurrentPlayer()).getId());
    }

    /*
        receives a window identifier (name) and a player identifier (number), creates the window calling a factory
        then assigns that window to the correct player and set the player as ready to play
     */

    public void setWindowPattern(int playerId, String windowName){
        factory = new WindowPatternFactory();
        Player p = model.getPlayerFromId(playerId);
        p.setPlayerWindow(factory.createWindow(windowName));
        p.setReady(true);
    }

    /*
        when a tool card is used, some changes in the model must be made (tokens decreased, card set as used)
        then the player is notified of the result (action successful) and everyone gets an update of the board
        in case the player has already done both his moves (set a die and use a card), his turn ends
     */

    private void toolCardUsed(int toolcard, int player){
        model.useToolCard(toolcard, player);
        model.notifyMessage(new OkMessage(), player);
        model.notifyUpdate();
        Player p = model.getPlayerFromId(player);
        if(p.isDieUsed() && p.isToolCardUsed()){
            nextPlayer(player);
        }
    }

    /*
        calls RoundHandler method nextTurn() recursively, skipping turns for offline players or
            players who already did their second turn (see tool card 8)
     */

    private void nextPlayer(int player){
        model.notifyMessage(new EndTurnMessage(), player);
        model.getPlayerFromId(player).resetTurn();
        /*
            try catch needs to be added in order to handle the nextRound exception !!!
         */
        handler.nextTurn();
        while(!model.getPlayers(handler.getCurrentPlayer()).isOnline() || model.getPlayers(handler.getCurrentPlayer()).isSkipTurn()){
            handler.nextTurn();
        }
        model.notifyMessage(new NewTurnMessage(), model.getPlayers(handler.getCurrentPlayer()).getId());
        /*in case of new round: -if all players are offline except one -> end game
                                -dice left from draft to track
                                -create new draft
                                -skipTurn set to false for every player
                                -model.notify
        */
    }

    /*
        receives an identifier of a player (int player) and a string representation of a Gson message (Object gson),
        calls parse() method from GsonParser and uses visitor pattern to handle the returned Message
     */

    public synchronized void update(String gson, int player) {
        ControllerMessage message = parser.parseController(gson);
        message.accept(this, player);
    }

    /*
        visitor pattern methods
     */

    public void visit(ChooseWindowMessage message, int player){
        System.out.println(message.getId() + " message received from player " + player);

        setWindowPattern(player, message.getWindowName());

        if(model.allReadyToPlay()){
            //fermo il countdown
            startMatch();
        }
    }

    public void visit(LoginMessage message, int player){
        System.out.println(message.getId() + " message received from player " + player);

        if(model.isGameStarted()){
            if(model.findAndReconnect(message.getUsername(), player)){
                model.notifyMessage(new OkMessage(), player);
            }
            else model.notifyMessage(new ErrorMessage(0), player);
        }
        else{
            if(!model.find(message.getUsername())){
                model.getPlayerFromId(player).setUsername(message.getUsername());
                model.notifyMessage(new ShowWindowsMessage(factory.getWindow(), factory.getWindow(), factory.getWindow(), factory.getWindow()), player);
            }
            else model.notifyMessage(new ErrorMessage(1), player);
        }
    }

    public void visit(LogoutMessage message, int player){
        System.out.println(message.getId() + " message received from player " + player);
        model.getPlayerFromId(player).setOnline(false);
        //notificare tutti gli altri giocatori della disconnessione
    }

    public void visit(PassMessage message, int player){
        System.out.println(message.getId() + " message received from player " + player);
        nextPlayer(player);
    }

    public void visit(SetDieMessage message, int player){
        System.out.println(message.getId() + " message received from player " + player);
        Player p = model.getPlayerFromId(player);
        if(p != model.getPlayers(handler.getCurrentPlayer()) || p.isDieUsed()){
            model.notifyMessage(new ErrorMessage(2), player);
            return;
        }
        if(p.isFirstDiePlaced()){
            if(!p.getPlayerWindow().isValid(message.getX(), message.getY(), model.getDieFromDraft(message.getIndex()))){
                model.notifyMessage(new ErrorMessage(2), player);
                return;
            }
        }
        else{
            if(!p.getPlayerWindow().isValidFirstMove(message.getX(), message.getY(), model.getDieFromDraft(message.getIndex()))){
                model.notifyMessage(new ErrorMessage(2), player);
                return;
            }
        }
        model.useDie(player, message.getX(), message.getY(), message.getIndex());
        p.setDieUsed(true);
        model.notifyMessage(new OkMessage(), player);
        model.notifyUpdate();
        if(p.isDieUsed() && p.isToolCardUsed()){
            nextPlayer(player);
        }
    }

    /*
        tool cards type A change one die from the draft pool according to an identifier
     */

    public void visit(ToolCardAMessage message, int player){
        System.out.println(message.getId() + " message received from player " + player);
        if(!model.canUseToolCard(message.getNum(), player)){
            model.notifyMessage(new ErrorMessage(3), player);
            return;
        }
        if(message.getNum() ==  1){
            Die d = model.getDieFromDraft(message.getDieIndex());
            if(!checker.toolCard1(d, message.getAction())){
                model.notifyMessage(new ErrorMessage(2), player);
                return;
            }
            if (message.getAction() == 0) d.decreaseValue();
            else d.increaseValue();
        }
        if(message.getNum() ==  5){
            Die d = model.removeDieFromDraft(message.getDieIndex());
            model.setDieDraft(model.removeDieFromRoundTrack(message.getAction()));
            model.setDieRoundTrack(d);
        }
        toolCardUsed(message.getNum(), player);
    }

    public void visit(ToolCardBMessage message, int player){

        //[...]

    }

    public void visit(ToolCardCMessage message, int player){

        //[...]

    }

    /*
        when GsonParser fails to identify a Json String
     */

    public void visit(UnexpectedMessage message, int player){
        System.out.println("unexpected message received from player " + player);
    }

    /*
        unsupported method
     */

    public void update(String message) {
        throw new UnsupportedOperationException();
    }

}
