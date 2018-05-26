package it.polimi.ingsw.controller;
import com.google.gson.stream.JsonReader;
import it.polimi.ingsw.messages.ShowWindowsMessage;
import it.polimi.ingsw.model.*;

import java.io.IOException;
import java.io.StringReader;

public class Controller {

    private Game model;
    private RoundHandler handler;

    /*
        constructor
     */

    public Controller(){
        this.model = new Game();
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
    }

    /*
        creates four windows, puts them in a Message type class and returns the Message serialized as a String
     */

    public String showWindows(){
        WindowPatternFactory factory = new WindowPatternFactory();
        WindowPattern[] w = new WindowPattern[4];
        for (int i = 0; i < 4; i++) {
            w[i] = factory.getWindow();
        }
        return new ShowWindowsMessage(w[0].getName(), w[0].getDifficultyToken(), w[0].toString(),
                    w[1].getName(), w[1].getDifficultyToken(), w[1].toString(),
                    w[2].getName(), w[2].getDifficultyToken(), w[2].toString(),
                    w[3].getName(), w[3].getDifficultyToken(), w[3].toString()).serialize();
    }

    /*
        receives a window identifier (name) and a player identifier (number), creates the window calling a factory
        then assigns that window to the correct player
     */

    public void setWindowPattern(int playerIndex, String windowName){
        WindowPatternFactory factory = new WindowPatternFactory();
        Player p = model.getPlayers(playerIndex);
        p.setPlayerWindow(factory.createWindow(windowName));
    }

    /*
        receives an identifier of a player (playerIndex) and a serialized representation of a Gson message,
        the message contains all the information regarding the player's move,
        controller handles the move, validates/invalidates it, change model according to the move, returns a String
            describing the output of the move (valid/invalid)
     */

    public String handleMove(int playerIndex, String gsonMessage){

            String id = getIdMessage(gsonMessage);

            /*if(id.equals("quit"){
                //setta il player come offline e chiama il metodo per finire il suo turno
            }
            if(id.equals("pass"){
               //fa finire il turno del giocatore
            }
            if(id.equals("place"){

                //if(!...isValid()) return("Invalid placement");
                //model.useDie
                return "Die placed";
            }
            if(id.equals("toolcard") {
            //int cardId = getIdCard(String message);

            //validità: token sufficienti? no=return, sì=switchcase

            //validità: model.getToolCard(cardId).validation? no=return, sì=model.useToolCard(cardId)

            }*/

        return null;
    }

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

    /*private int getIdCard(String s){

        //jsonReader here
        return jsonReader.nextString();

    }*/

}
