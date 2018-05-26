package it.polimi.ingsw.messages;

import com.google.gson.Gson;

public class LoginMessage extends Message {

    private String id;
    private String username;

    public LoginMessage(){}

    public LoginMessage(String id, String username){
        this.id = id;
        this.username = username;
    }

    public String serialize() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public LoginMessage deserialize(String s){
        Gson gson = new Gson();
        return gson.fromJson(s, LoginMessage.class);
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

}
