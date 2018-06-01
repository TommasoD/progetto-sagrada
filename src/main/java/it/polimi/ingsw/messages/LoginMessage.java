package it.polimi.ingsw.messages;

import com.google.gson.Gson;

public class LoginMessage extends Message {

    private String id;
    private String username;

    public LoginMessage(){
        id = "login";
    }

    public LoginMessage(String username){
        id = "login";
        this.username = username;
    }

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

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
