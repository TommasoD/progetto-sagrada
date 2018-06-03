package it.polimi.ingsw.messages.client;

import com.google.gson.Gson;
import it.polimi.ingsw.view.View;

public class OkMessage extends ClientMessage {

    private String id;

    public OkMessage(){
        id = "ok";
    }

    public String serialize() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public void accept(View v) {

    }

    public OkMessage deserialize(String s){
        Gson gson = new Gson();
        return gson.fromJson(s, OkMessage.class);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}