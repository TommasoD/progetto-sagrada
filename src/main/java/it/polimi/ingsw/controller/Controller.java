package it.polimi.ingsw.controller;
import com.google.gson.stream.JsonReader;
import it.polimi.ingsw.messages.SetDieMessage;
import it.polimi.ingsw.messages.ShowWindowsMessage;
import it.polimi.ingsw.model.*;
import java.io.IOException;
import java.io.StringReader;

public class Controller {

    private Game model;
    private RoundHandler handler;
    private WindowPatternFactory factory;

    /*
        constructors
     */

    public Controller(Game model){
        this.model = model;
        this.factory = new WindowPatternFactory();
    }

    public Controller(){
        this.model = new Game();
        this.factory = new WindowPatternFactory();
    }

    /*
        getters and setters
     */

    public Game getGame(){
        return this.model;
    }

    /*
        adds a new Player class to the model
        server must call addPlayer() in the exact order in which every client sent the login message
     */

    public void addPlayer(String username){
        Player p = new Player(username);
        model.setPlayers(p);
    }

    /*
        creates a new RoundHandler class and initializes the model class
     */

    public void newMatch(){
        handler = new RoundHandler(model.playersSize());
        model.initialize();
        model.setDraft();
    }

    /*
        creates four windows, puts them in a Message type class and returns the Message serialized as a String
     */

    public String showWindows(){
        WindowPattern[] w = new WindowPattern[4];
        for (int i = 0; i < 4; i++) {
            w[i] = factory.getWindow();
        }
        return new ShowWindowsMessage(w[0].getName(), w[0].toString(),
                    w[1].getName(), w[1].toString(),
                    w[2].getName(), w[2].toString(),
                    w[3].getName(), w[3].toString()).serialize();
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
        returns an identifier of the current player in the current turn
     */

    public int whoIsNext(){
        return handler.getCurrentPlayer();
    }

    /*
        receives an identifier of a player (playerIndex) and a serialized representation of a Gson message,
        the message contains all the information regarding the player's move,
        controller handles the move, validates/invalidates it, change model according to the move, returns a String
            describing the output of the move (valid/invalid)
     */

    public synchronized String handleMove(int player, String gsonMessage){

        String id = getIdMessage(gsonMessage);

        if(id.equals("place")){
            SetDieMessage message = new SetDieMessage();
            message = message.deserialize(gsonMessage);
            if(player != handler.getCurrentPlayer()) return "Invalid placement";

            Player p = model.getPlayers(player);
            if(p.isDieUsed()) return "Invalid placement";

            if(p.isFirstDiePlaced()){
                if(!p.getPlayerWindow().
                        isValid(message.getX(), message.getY(), model.getDieFromDraft(message.getIndex())))
                            return("Invalid placement");
            }
            else{
                if(!p.getPlayerWindow().
                        isValidFirstMove(message.getX(), message.getY(), model.getDieFromDraft(message.getIndex())))
                            return("Invalid placement");
            }

            model.useDie(player, message.getX(), message.getY(), message.getIndex());
            p.setDieUsed(true);
            if(p.isDieUsed() && p.isToolCardUsed()){
                nextPlayer(player);
            }
            return "Die placed";
        }

        if(id.equals("pass")){
            nextPlayer(player);
        }


        /*if(id.equals("toolcard") {
        //int cardId = getIdCard(String message);

        //validità: token sufficienti? no=return, sì=switchcase

        //validità: model.getToolCard(cardId).validation? no=return, sì=model.useToolCard(cardId)

        }

        if(id.equals("quit"){
            //setta il player come offline e chiama il metodo per finire il suo turno
        }*/

        return null;
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
        while(!model.getPlayers(handler.getCurrentPlayer()).getOnline() || model.getPlayers(handler.getCurrentPlayer()).isSkipTurn()){
            handler.nextTurn();
        }

        /*in case of new round: -if all players are offline except one -> end game
                                -dice left from draft to track
                                -create new draft
                                -skipTurn set to false for every player
        */
    }

    /*
        methods instantiating a reader to read Gson strings
     */

    private String getIdMessage(String s){
        JsonReader jsonReader = new JsonReader(new StringReader(s));
        String action;
        try{
            jsonReader.beginObject();
            jsonReader.nextName();
            action = jsonReader.nextString();
            jsonReader.close();
        } catch(IOException e){
            action = "error";
        }
        return action;
    }

    private int getIdCard(String s){
        //jsonReader here
        return 0;
    }

    /*
        checks if game is ended (i.e. round 10 has concluded)
     */

    public boolean isGameEnded(){
        return handler.getRound() > 10;
    }

}
