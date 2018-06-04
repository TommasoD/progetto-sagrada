package it.polimi.ingsw.controller;
import it.polimi.ingsw.messages.client.ErrorMessage;
import it.polimi.ingsw.messages.client.NewTurnMessage;
import it.polimi.ingsw.messages.client.OkMessage;
import it.polimi.ingsw.messages.client.ShowWindowsMessage;
import it.polimi.ingsw.messages.controller.*;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.parsers.GsonParser;
import it.polimi.ingsw.utils.Observer;

public class Controller implements Observer{

    private Game model;
    private RoundHandler handler;
    private WindowPatternFactory factory;
    private GsonParser parser;

    /*
        constructors
     */

    public Controller(Game model){
        this.model = model;
        factory = new WindowPatternFactory();
        parser = new GsonParser();
    }

    public Controller(){
        model = new Game();
        factory = new WindowPatternFactory();
        parser = new GsonParser();
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
        //parte il countdown per l'inizio partita: i giocatori che non scelgono username o finestra avranno quelli default
    }

    /*
        creates a new RoundHandler class and initializes the model class
     */

    public void startMatch(){
        model.setGameStarted(true);
        model.initialize();
        handler = new RoundHandler(model.playersSize());
        model.notifyAllPlayers();
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
        calls RoundHandler method nextTurn() recursively, skipping turns for offline players or
            players who already did their second turn (see tool card 8)
     */

    private void nextPlayer(int player){
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

    public synchronized void update(Object gson, int player) {
        ControllerMessage message = parser.parseController((String) gson);
        message.accept(this, player);
    }

    public void update(Object message) {
        throw new UnsupportedOperationException();
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
        nextPlayer(player);
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
                model.notifyMessage(new ErrorMessage(), player);
                return;
            }
        }

        model.useDie(player, message.getX(), message.getY(), message.getIndex());
        p.setDieUsed(true);
        if(p.isDieUsed() && p.isToolCardUsed()){
            nextPlayer(player);
        }
        model.notifyMessage(new OkMessage(), player);
        model.notifyAllPlayers();
    }

    public void visit(UnexpectedMessage message, int player){
        System.out.println("unexpected message received from player " + player);
    }



}
