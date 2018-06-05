package it.polimi.ingsw.messages.client;

import com.google.gson.Gson;
import it.polimi.ingsw.client.ClientManager;

public class EndTurnMessage extends ClientMessage {

    private String id;

    public EndTurnMessage(){
        id = "end";
    }

    public String serialize() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public void accept(ClientManager c) {
        c.visit(this);
    }

    public EndTurnMessage deserialize(String s){
        Gson gson = new Gson();
        return gson.fromJson(s, EndTurnMessage.class);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
