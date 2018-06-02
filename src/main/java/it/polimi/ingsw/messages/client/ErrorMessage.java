package it.polimi.ingsw.messages.client;

import com.google.gson.Gson;
import it.polimi.ingsw.view.View;

public class ErrorMessage extends ClientMessage {

    private String id;
    private int type;

    public ErrorMessage(){
        id = "error";
    }

    public ErrorMessage(int type){
        id = "error";
        this.type = type;
    }

    public String serialize() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public void accept(View v) {

    }

    public ErrorMessage deserialize(String s){
        Gson gson = new Gson();
        return gson.fromJson(s, ErrorMessage.class);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
