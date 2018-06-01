package it.polimi.ingsw.messages;

import com.google.gson.Gson;

public class PassMessage {

    private String id;

    public PassMessage() {
        this.id = "pass";
    }

    public String serialize() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public PassMessage deserialize(String s){
        Gson gson = new Gson();
        return gson.fromJson(s, PassMessage.class);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
