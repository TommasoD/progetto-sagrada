package it.polimi.ingsw.messages.controller;

import com.google.gson.Gson;
import it.polimi.ingsw.controller.Controller;

public class LoginMessage extends ControllerMessage {

    private String id;
    private String username;

    public LoginMessage(){
        id = "login";
    }

    public LoginMessage(String username){
        id = "login";
        this.username = username;
    }

    public String serialize() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public void accept(Controller c, int player) {
        c.visit(this, player);
    }

    public static LoginMessage deserializeLoginMessage(String s){
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
