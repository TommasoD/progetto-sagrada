package it.polimi.ingsw.messages.client;

import com.google.gson.Gson;
import it.polimi.ingsw.view.View;

public class NewTurnMessage extends ClientMessage {

    private String id;

    public NewTurnMessage(){
        id = "turn";
    }

    public String serialize() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public void accept(View v) {

    }

    public NewTurnMessage deserialize(String s){
        Gson gson = new Gson();
        return gson.fromJson(s, NewTurnMessage.class);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}