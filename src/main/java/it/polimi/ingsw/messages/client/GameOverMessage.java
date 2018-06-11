package it.polimi.ingsw.messages.client;

import com.google.gson.Gson;
import it.polimi.ingsw.client.ClientManager;

public class GameOverMessage extends ClientMessage {

    private String id;

    public GameOverMessage(){
        id = "game_over";
    }

    public String serialize() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public void accept(ClientManager c) {
        c.visit(this);
    }

    public static GameOverMessage deserializeGameOverMessage(String s){
        Gson gson = new Gson();
        return gson.fromJson(s, GameOverMessage.class);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}