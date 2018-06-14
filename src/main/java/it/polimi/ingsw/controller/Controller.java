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
    private CountdownMove timer;

    /*
        constructors
     */

    public Controller(Game model){
        this.model = model;
        factory = new WindowPatternFactory();
        parser = new GsonParser();
        checker = new ToolCardCheck();
        timer = new CountdownMove(this);
    }

    public Controller(){
        model = new Game();
        factory = new WindowPatternFactory();
        parser = new GsonParser();
        checker = new ToolCardCheck();
        timer = new CountdownMove(this);
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
        for(Player p : model.getPlayers()){
            if(p.isOnline()){
                model.notifyMessage(new LoginRequestMessage(), p.getId());
            }
        }
        timer.start();
    }

    /*
        creates a new RoundHandler class and initializes the model class
     */

    public void startMatch(){
        model.initialize();
        handler = model.getHandler();
        model.notifyUpdate();
        while (!model.getPlayers(handler.getCurrentPlayer()).isOnline()){
            try{
                handler.nextTurn();
            } catch (NewRoundException e){}
        }
        model.notifyMessage(new NewTurnMessage(), model.getPlayers(handler.getCurrentPlayer()).getId());
        timer.wakeUp(handler.getCurrentPlayer());
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
    }

    /*
        @player is the id of the current player
        calls RoundHandler method nextTurn() recursively, skipping turns for offline players or
            players who already did their second turn (see tool card 8)
     */

    public void nextPlayer(int player){
        model.notifyMessage(new EndTurnMessage(), player);
        model.getPlayerFromId(player).resetTurn();
        if(model.getPlayerFromId(player).isFirstTurnDone()) model.getPlayerFromId(player).setSecondTurnDone(true);
        else model.getPlayerFromId(player).setFirstTurnDone(true);

        try {
            handler.nextTurn();
            while (!model.getPlayers(handler.getCurrentPlayer()).isOnline() ||
                    model.getPlayers(handler.getCurrentPlayer()).isSecondTurnDone()) {
                handler.nextTurn();
            }
        } catch(NewRoundException e) {
            System.out.println("round " + e.getRound() + " is starting");
            model.diceLeft();
            model.setDraft();
            for(Player p : model.getPlayers()){
                p.resetRound();
            }
            model.notifyUpdate();
        }

        if(handler.isGameEnded()){
            GameOver gameOver = new GameOver();
            String winner = gameOver.determineWinner(model.getPlayers(), model.getPublicObjectiveActive(), handler.getTurnOrder());
            model.notifyAllPlayers(new GameOverMessage(winner));
            // TODO : segnalare fine del gioco al server in qualche modo?
        }
        else{
            model.notifyMessage(new NewTurnMessage(), model.getPlayers(handler.getCurrentPlayer()).getId());
            timer.wakeUp(handler.getCurrentPlayer());
        }
    }

    /*
        receives a window identifier (name) and a player identifier (number), creates the window calling a factory
        then assigns that window to the correct player and set the player as ready to play
     */

    public void setWindowPattern(int player, String windowName){
        model.getPlayerFromId(player).setPlayerWindow(factory.createWindow(windowName));
        model.getPlayerFromId(player).setReady(true);
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
    --------------------------------------------------------------
        visitor pattern methods
    --------------------------------------------------------------
     */

    /*
        message contains the name of the window chosen by the player
        when all players are ready to play, the timer is stopped and StartMatch() method from controller is called
        after that, the first turn of the first player starts
     */

    public void visit(ChooseWindowMessage message, int player){
        printMessage(message.getId(), player);
        setWindowPattern(player, message.getWindowName());
        if(model.allReadyToPlay()){
            timer.setDone();
        }
    }

    public void visit(LoginMessage message, int player){
        printMessage(message.getId(), player);
        if(!model.find(message.getUsername())){
            model.getPlayerFromId(player).setUsername(message.getUsername());
            model.notifyMessage(new ShowWindowsMessage(factory.getWindow(), factory.getWindow(), factory.getWindow(), factory.getWindow()), player);
        }
        else model.notifyMessage(new ErrorMessage(1), player);
    }

    public void visit(LogoutMessage message, int player){
        printMessage(message.getId(), player);
        model.getPlayerFromId(player).setOnline(false);
        // TODO : if all players are offline except one -> end game
        model.notifyAllPlayers(new NotificationMessage(model.getPlayerFromId(player).getUsername(), "disconnect"));
    }

    public void visit(PassMessage message, int player){
        printMessage(message.getId(), player);
        timer.setDone();
        nextPlayer(player);
    }

    public void visit(SetDieMessage message, int player){
        printMessage(message.getId(), player);
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
        model.notifyMessage(new OkMessage(), player);
        model.notifyUpdate();
    }

    /*
        tool cards type A change one die from the draft pool according to an identifier
            - tool card 1 increase or decrease the value of the chosen die
            - tool card 5 switch a die from the draft with a selected one in the round track
            - tool card 6 allows the player re-roll a die from the draft
            - tool card 10 flips a die
            - tool card 11 puts a die from the draft back in the bag, then a new die is taken from the bag and
                a new value is given to it
     */

    public void visit(ToolCardAMessage message, int player){
        printMessage(message.getId(), player);
        if(message.getNum() ==  11 && message.getAction() != 0){
            model.getDieFromDraft(0).setValue(message.getAction());
            model.notifyMessage(new OkMessage(), player);
            model.notifyUpdate();
            return;
        }
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
        if(message.getNum() ==  6){
            model.getDieFromDraft(message.getDieIndex()).roll();
        }
        if(message.getNum() ==  10){
            model.getDieFromDraft(message.getDieIndex()).flipDie();
        }
        if(message.getNum() ==  11){
            model.getDiceBag().addDie(model.removeDieFromDraft(message.getDieIndex()));
            model.setDieDraft(0, model.getDiceBag().getDie());
            model.useToolCard(message.getNum(), player);
            model.notifyMessage(new DieColorMessage(model.getDieFromDraft(0).getColor()), player);
            return;
        }
        toolCardUsed(message.getNum(), player);
    }

    /*
        tool cards type B move one die in the window of a player
            - tool card 2 ignores color restrictions
            - tool card 3 ignores value restrictions
     */

    public void visit(ToolCardBMessage message, int player){
        printMessage(message.getId(), player);
        WindowPattern w = model.getPlayerFromId(player).getPlayerWindow();
        if(!model.canUseToolCard(message.getNum(), player)){
            model.notifyMessage(new ErrorMessage(3), player);
            return;
        }
        if(message.getNum() ==  2 &&
                !checker.toolCard2(w, message.getX(), message.getY(), message.getA(), message.getB())){
            model.notifyMessage(new ErrorMessage(2), player);
            return;
        }
        if(message.getNum() ==  3 &&
                !checker.toolCard3(w, message.getX(), message.getY(), message.getA(), message.getB())){
            model.notifyMessage(new ErrorMessage(2), player);
            return;
        }
        model.moveDie(player, message.getX(), message.getY(), message.getA(), message.getB());
        toolCardUsed(message.getNum(), player);
    }

    /*
        tool cards type C move two dice in the window of a player
            - tool card 4 does not have an restriction
            - tool card 12 moves two dice of the same color of a die from the round track
     */

    public void visit(ToolCardCMessage message, int player){
        printMessage(message.getId(), player);
        WindowPattern w = model.getPlayerFromId(player).getPlayerWindow();
        if(!model.canUseToolCard(message.getNum(), player)){
            model.notifyMessage(new ErrorMessage(3), player);
            return;
        }
        if(message.getNum() ==  4 &&
                !checker.toolCard4(w, message.getX(), message.getY(), message.getA(),
                        message.getB(), message.getX2(), message.getY2(), message.getA2(), message.getB2())){
            model.notifyMessage(new ErrorMessage(2), player);
            return;
        }
        if(message.getNum() ==  12 &&
                !checker.toolCard12(model.getRoundTrack(), w, message.getX(), message.getY(), message.getA(),
                        message.getB(), message.getX2(), message.getY2(), message.getA2(), message.getB2())){
            model.notifyMessage(new ErrorMessage(2), player);
            return;
        }
        model.moveDie(player, message.getX(), message.getY(), message.getA(), message.getB());
        model.moveDie(player, message.getX2(), message.getY2(), message.getA2(), message.getB2());
        toolCardUsed(message.getNum(), player);
    }

    /*
        tool cards type D place one die in the window of a player
            - tool card 8 allows the player to place a second die in the same turn
            - tool card 9 places a die in a spot not adjacent to other dice in the window
     */

    public void visit(ToolCardDMessage message, int player){
        printMessage(message.getId(), player);
        WindowPattern w = model.getPlayerFromId(player).getPlayerWindow();
        if(!model.canUseToolCard(message.getNum(), player)){
            model.notifyMessage(new ErrorMessage(3), player);
            return;
        }
        if(message.getNum() ==  8){
            if(!checker.toolCard8(w, model.getDieFromDraft(message.getDieIndex()), message.getX(), message.getY()) ||
                    model.getPlayerFromId(player).isFirstTurnDone() ||
                    model.getPlayerFromId(player).isDieUsed()) {
                model.notifyMessage(new ErrorMessage(2), player);
                return;
            }
            model.getPlayerFromId(player).setSecondTurnDone(true);
        }
        if(message.getNum() ==  9 &&

                !checker.toolCard9(w, model.getDieFromDraft(message.getDieIndex()), message.getX(), message.getY())){
            model.notifyMessage(new ErrorMessage(2), player);
            return;
        }
        model.useDie(player, message.getX(), message.getY(), message.getDieIndex());
        toolCardUsed(message.getNum(), player);
    }

    /*
        tool cards type D do not need any parameter
            - tool card 7 re-roll all the dice in the draft pool
     */

    public void visit(ToolCardEMessage message, int player){
        printMessage(message.getId(), player);
        if(!model.canUseToolCard(message.getNum(), player)){
            model.notifyMessage(new ErrorMessage(3), player);
            return;
        }
        if(message.getNum() ==  7){
            if(model.getPlayerFromId(player).isFirstTurnDone() && !model.getPlayerFromId(player).isSecondTurnDone()) {
                model.notifyMessage(new ErrorMessage(2), player);
                return;
            }
            for(Die d : model.getDraft()){
                d.roll();
            }
        }
        toolCardUsed(message.getNum(), player);
    }

    public void visit(ShowTableRequestMessage message, int player){
        printMessage(message.getId(), player);
        model.notifyMessage(new ShowTableMessage(model.getPlayerFromId(player).getPlayerObjective(), model.getToolCards(), model.getObjectivesAsString()), player);
    }

    public void visit(ReconnectMessage message, int player){
        printMessage(message.getId(), player);
        if(model.getPlayerFromId(player).isOnline()) model.notifyMessage(new ErrorMessage(5), player);
        else{
            model.getPlayerFromId(player).setOnline(true);
            model.notifyAllPlayers(new NotificationMessage(model.getPlayerFromId(player).getUsername(), "reconnect"));
        }
    }

    /*
        when GsonParser fails to identify a Json String
     */

    public void visit(UnexpectedMessage message, int player){
        printMessage("unexpected", player);
    }

    /*
        print the latest message received
     */

    private void printMessage(String id, int player){
        System.out.println(id + " message received from player " + player);
    }

    /*
        unsupported method
     */

    public void update(String message) {
        throw new UnsupportedOperationException();
    }

}