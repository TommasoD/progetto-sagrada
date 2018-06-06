package it.polimi.ingsw.messages.controller;

import com.google.gson.Gson;
import it.polimi.ingsw.controller.Controller;

public class LogoutMessage extends ControllerMessage {

    private String id;

    public LogoutMessage() {
        this.id = "quit";
    }

    public String serialize() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public void accept(Controller c, int player) {
        c.visit(this, player);
    }

    public static LogoutMessage deserializeLogoutMessage(String s){
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
