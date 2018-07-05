package it.polimi.ingsw.controller;
import it.polimi.ingsw.messages.client.*;
import it.polimi.ingsw.messages.controller.*;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.parsers.GsonParser;
import it.polimi.ingsw.utils.Observer;

import java.util.logging.Logger;

/**
 * Manages a game in terms of the order of turns and players' actions.
 * Receives messages from the network and handles them, modifies the model according
 * to the actions of the players and the rules of the game.
 */
public class Controller implements Observer<String>{

    private Game model;
    private RoundHandler handler;
    private ToolCardCheck checker;
    private WindowPatternFactory factory;
    private GsonParser parser;
    private CountdownMove timer;
    private Logger logger;

    /**
     * Constructs a controller from an already existing game.
     * @param model an existing game.
     */

    public Controller(Game model){
        this.model = model;
        factory = new WindowPatternFactory();
        parser = new GsonParser();
        checker = new ToolCardCheck();
        timer = new CountdownMove(this);
        logger = Logger.getLogger(Controller.class.getName());
    }

    /**
     * Constructs a controller.
     */

    public Controller(){
        model = new Game();
        factory = new WindowPatternFactory();
        parser = new GsonParser();
        checker = new ToolCardCheck();
        timer = new CountdownMove(this);
        logger = Logger.getLogger(Controller.class.getName());
    }

    /**
     * Getter for model.
     * @return current model.
     */

    public Game getGame(){
        return model;
    }

    /**
     * Adds default players to the game, waiting for username and window from each client.
     * @param nPlayers the number of players in the game.
     */

    public void newMatch(int nPlayers){
        for(int i = 0; i < nPlayers; i++){
            model.addPlayer(new Player("player" + i, factory.getWindow(), i));
        }
    }

    /**
     * Sends a login request to all the players in the game, right after the start of match.
     */

    public void newLoginRequest(){
        for(Player p : model.getPlayers()){
            if(p.isOnline()){
                model.notifyMessage(new LoginRequestMessage(), p.getId());
            }
        }
        timer.start();
    }

    /**
     * Saves the reference to the turn handler created in the model, then initializes the model class.
     * After the game is initialized, the match starts: the first player is selected and - in case he's
     * already offline - the next player is recursively selected. A notification is sent to the player and
     * the countdown for his turn starts.
     */

    public void startMatch(){
        model.initialize();
        handler = model.getHandler();
        model.notifyUpdate();
        while (!model.getPlayers(handler.getCurrentPlayer()).isOnline()){
            try{
                handler.nextTurn();
            } catch (NewRoundException e){
                logger.info("new round");
            }
        }
        model.notifyMessage(new NewTurnMessage(), model.getPlayers(handler.getCurrentPlayer()).getId());
        timer.wakeUp(handler.getCurrentPlayer());
    }

    /**
     * When a tool card is used, some changes in the model must be made (tokens decreased, card set as used);
     * after that, the player is notified of the result (action successful) and everyone gets an update of the board.
     * In case the player has already done both his moves (set a die and use a card), his turn ends.
     * @param toolcard the number of the tool card used.
     * @param player the ID of the player who performed the move.
     */

    private void toolCardUsed(int toolcard, int player){
        model.useToolCard(toolcard, player);
        model.notifyMessage(new OkMessage(), player);
        model.notifyUpdate();
    }

    /**
     * Sets a player as offline and checks if there's only one active player left.
     * In that case the game ends, otherwise the game goes on.
     * @param playerID the ID of a player.
     * @param event either disconnect or suspended.
     */

    public void setPlayerOffline(int playerID, String event){
        model.getPlayerFromId(playerID).setOnline(false);
        model.notifyAllPlayers(new NotificationMessage(model.getPlayerFromId(playerID).getUsername(), event));
        if(model.getNumberOfOnlinePlayers() == 1){
            for(Player p : model.getPlayers()){
                if(p.isOnline()){
                    model.notifyAllPlayers(new GameOverMessage(p.getUsername()));
                    break;
                }
            }
            model.setGameEnded(true);
        }
    }

    /**
     * When the next player is selected, notifies the previous player of the end of his turn,
     * then checks if the now current player is offline, thus selecting a new next player recursively.
     * Before sending a notification to the current player, checks if ten rounds have already being player,
     * i.e. the game is ended and all player are notified of the winner.
     * @param player the ID of the current player.
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
            logger.info("round " + e.getRound() + " is starting");
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
            model.setGameEnded(true);
        }
        else{
            model.notifyMessage(new NewTurnMessage(), model.getPlayers(handler.getCurrentPlayer()).getId());
            timer.wakeUp(handler.getCurrentPlayer());
        }
    }

    /**
     * Receives name of the window selected by a player, creates the window using a factory, then
     * assigns the window to the player and sets the player as ready-to-play.
     * If all players are ready, the game shall start.
     * @param player the player's ID.
     * @param windowName the name of the window.
     */

    public void setWindowPattern(int player, String windowName){
        model.getPlayerFromId(player).setPlayerWindow(factory.createWindow(windowName));
        model.getPlayerFromId(player).setReady(true);
        if(model.allReadyToPlay()){
            timer.setDone();
        }
    }

    /**
     * Uses a parser to analyze a message, which is managed by the controller itself through visitor pattern.
     * @param gson Json representation of a message by the current player.
     * @param player the current player.
     */

    public synchronized void update(String gson, int player) {
        ControllerMessage message = parser.parseController(gson);
        message.accept(this, player);
    }

    /*
    --------------------------------------------------------------
        VISITOR PATTERN METHODS
    --------------------------------------------------------------
     */


    /**
     * When a player chooses a window, it is assigned to him.
     * @param message the player's request.
     * @param player the current player.
     */

    public void visit(ChooseWindowMessage message, int player){
        printMessage(message.getId(), player);
        setWindowPattern(player, message.getWindowName());
    }

    /**
     * When a player chooses a username, it is assigned to him. If the username has already been selected
     * by someone else, an error message is sent to the player.
     * @param message the player's request.
     * @param player the current player.
     */

    public void visit(LoginMessage message, int player){
        printMessage(message.getId(), player);
        if(!model.find(message.getUsername())){
            model.getPlayerFromId(player).setUsername(message.getUsername());
            model.notifyMessage(new ShowWindowsMessage(factory.getWindow(), factory.getWindow(), factory.getWindow(), factory.getWindow()), player);
        }
        else model.notifyMessage(new ErrorMessage(1), player);
    }

    /**
     * When a player disconnects, he's set offline in the model.
     * @param message the player's request.
     * @param player the current player.
     */

    public void visit(LogoutMessage message, int player){
        printMessage(message.getId(), player);
        setPlayerOffline(player, "disconnect");
    }

    /**
     * When a player chooses to end his turn, picks the next player.
     * @param message the player's request.
     * @param player the current player.
     */

    public void visit(PassMessage message, int player){
        printMessage(message.getId(), player);
        timer.setDone();
        nextPlayer(player);
    }

    /**
     * When a player chooses to place a die on his window and all the rules are followed, notifies the player
     * of correct result of his move. He won't be able to perform the same move in the current turn,
     * however the turn goes on as he can still use a tool card.
     * @param message the player's request.
     * @param player the current player.
     */

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

    /**
     * Performs all the needed action regarding the usage of a tool card of this type.
     * <p>
     * Tool cards type A change one die from the draft pool according to an identifier:
     * tool card 1 increase or decrease the value of a chosen die;
     * tool card 5 switch a die from the draft with a selected one in the round track;
     * tool card 6 allows the player re-roll a die from the draft;
     * tool card 10 flips a die from the draft;
     * tool card 11 puts a die from the draft back in the bag, then a new die is taken from the bag
     * and a new value is given to it.
     * @param message the player's request.
     * @param player the current player.
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

    /**
     * Performs all the needed action regarding the usage of a tool card of this type.
     * <p>
     * Tool cards type B move one die in the window of a player:
     * tool card 2 ignores color restrictions;
     * tool card 3 ignores value restrictions.
     * @param message the player's request.
     * @param player the current player.
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

    /**
     * Performs all the needed action regarding the usage of a tool card of this type.
     * <p>
     * Tool cards type C move two dice in the window of a player:
     * tool card 4 does not have any restriction;
     * tool card 12 moves two dice of the same color of a die from the round track.
     * @param message the player's request.
     * @param player the current player.
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

    /**
     * Performs all the needed action regarding the usage of a tool card of this type.
     * <p>
     * Tool cards type D place one die in the window of a player:
     * tool card 8 allows the player to place a second die in the same turn;
     * tool card 9 places a die in a spot not adjacent to any other die in the window.
     * @param message the player's request.
     * @param player the current player.
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

    /**
     * Performs all the needed action regarding the usage of a tool card of this type.
     * <p>
     * Tool cards type E do not need any validation:
     * tool card 7 re-roll all the dice in the draft pool.
     * @param message the player's request.
     * @param player the current player.
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

    /**
     * Sends all the information regarding the public objectives of the current game, the personal
     * private objective and all the tool cards (status and description).
     * @param message the player's request.
     * @param player the ID of the player.
     */

    public void visit(ShowTableRequestMessage message, int player){
        printMessage(message.getId(), player);
        model.notifyMessage(new ShowTableMessage(model.getPlayerFromId(player).getPlayerObjective(), model.getToolCards(), model.getObjectivesAsString()), player);
    }

    /**
     * Sets a player as online when he tries to reconnect from inactivity.
     * @param message the player's request.
     * @param player the ID of the player.
     */

    public void visit(ReconnectMessage message, int player){
        printMessage(message.getId(), player);
        if(model.getPlayerFromId(player).isOnline()) model.notifyMessage(new ErrorMessage(5), player);
        else{
            model.getPlayerFromId(player).setOnline(true);
            model.notifyAllPlayers(new NotificationMessage(model.getPlayerFromId(player).getUsername(), "reconnect"));
        }
    }

    /**
     * Prints an error in case the parser fails to identify a message.
     * @param message the player's request.
     * @param player the ID of the player.
     */

    public void visit(UnexpectedMessage message, int player){
        printMessage("unexpected", player);
    }

    /**
     * Prints on screen the type and the sender of any message received.
     * @param id the ID of the message.
     * @param player the ID of the player.
     */

    private void printMessage(String id, int player){
        String s = id + " message received from player " + player;
        logger.info(s);
    }

    /**
     * Unsupported method inherited from Observer.
     * @param message not used.
     */

    public void update(String message) {
        throw new UnsupportedOperationException();
    }

}