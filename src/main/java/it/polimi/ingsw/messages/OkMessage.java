package it.polimi.ingsw.messages;

import com.google.gson.Gson;

public class OkMessage {

    private String id;

    public OkMessage(){
        id = "ok";
    }

    public String serialize() {
        Gson gson = new Gson();
        return gson.toJson(this);
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
