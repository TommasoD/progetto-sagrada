package it.polimi.ingsw.controller;
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
        adds a new Player class to the model
     */

    public void addPlayer(String username){
        Player p = new Player(username);
        model.setPlayers(p);
    }

    /*
        creates a new RoundHandler class and initializes the model class
     */

    public void newMatch(){

        model.initialize();
        handler = new RoundHandler(model.playersSize());
        //il model deve notificare a tutti i giocatori le finestre che andranno a scegliere
    }

    /*
        creates four windows, puts them in a Message type class and returns the Message serialized as a String
     */

    public String showWindows(){
        return new ShowWindowsMessage(factory.getWindow(), factory.getWindow(), factory.getWindow(), factory.getWindow()).serialize();
    }

    /*
        receives a window identifier (name) and a player identifier (number), creates the window calling a factory
        then assigns that window to the correct player
     */

    public void setWindowPattern(int playerIndex, String windowName){
        factory = new WindowPatternFactory();
        Player p = model.getPlayers(playerIndex);
        p.setPlayerWindow(factory.createWindow(windowName));
    }

    /*
        calls RoundHandler method nextTurn() recursively, skipping turns for offline players or
            players who already did their second turn (see tool card 8)
     */

    private void nextPlayer(int player){

        model.getPlayers(player).resetTurn();

        /*
            try catch needs to be added in order to handle the nextRound exception !!!
         */
        handler.nextTurn();
        while(!model.getPlayers(handler.getCurrentPlayer()).isOnline() || model.getPlayers(handler.getCurrentPlayer()).isSkipTurn()){
            handler.nextTurn();
        }

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
        System.out.println(message.getId() + "message received from player " + player);

    }

    public void visit(LoginMessage message, int player){
        System.out.println(message.getId() + "message received from player " + player);
        /*
            if(model.isGameStarted()){
                if(model.findAndReconnect(message.getUsername(), player)){
                    model.okMessage(player);
                }
            }
            else{
                if(!model.find(message.getUsername())){
                    addPlayer(message.getUsername());
                    model.okMessage(player);
                }
                else model.errorMessage(1, player);
            }
         */
    }

    public void visit(LogoutMessage message, int player){
        System.out.println(message.getId() + "message received from player " + player);
        //setta il player come offline e chiama il metodo per finire il suo turno
    }

    public void visit(PassMessage message, int player){
        System.out.println(message.getId() + "message received from player " + player);

        /*
            nextPlayer(player);
         */
    }

    public void visit(SetDieMessage message, int player){
        System.out.println(message.getId() + "message received from player " + player);

        /*
            if(player != handler.getCurrentPlayer()) model.errorMessage(2, handler.getCurrentPlayer());

            Player p = model.getPlayers(player);
            if(p.isDieUsed()) model.errorMessage(2, handler.getCurrentPlayer());

            if(p.isFirstDiePlaced()){
                if(!p.getPlayerWindow().
                        isValid(message.getX(), message.getY(), model.getDieFromDraft(message.getIndex())))
                            model.errorMessage(2, handler.getCurrentPlayer());
            }
            else{
                if(!p.getPlayerWindow().
                        isValidFirstMove(message.getX(), message.getY(), model.getDieFromDraft(message.getIndex())))
                    model.errorMessage(2, handler.getCurrentPlayer());
            }

            model.useDie(player, message.getX(), message.getY(), message.getIndex());
            p.setDieUsed(true);
            if(p.isDieUsed() && p.isToolCardUsed()){
                nextPlayer(player);
            }
            model.okMessage(handler.getCurrentPlayer());
         */
    }

    public void visit(UnexpectedMessage message, int player){
        System.out.println("unexpected message received from player " + player);
    }



}
