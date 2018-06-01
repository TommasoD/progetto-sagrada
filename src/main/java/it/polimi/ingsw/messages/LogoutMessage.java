package it.polimi.ingsw.messages;

import com.google.gson.Gson;

public class LogoutMessage extends Message {

    private String id;

    public LogoutMessage() {
        this.id = "quit";
    }

    public String serialize() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public LogoutMessage deserialize(String s){
        Gson gson = new Gson();
        return gson.fromJson(s, LogoutMessage.class);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
