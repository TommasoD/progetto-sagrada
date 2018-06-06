package it.polimi.ingsw.messages.controller;

import com.google.gson.Gson;
import it.polimi.ingsw.controller.Controller;

public class PassMessage extends ControllerMessage {

    private String id;

    public PassMessage() {
        this.id = "pass";
    }

    public String serialize() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public void accept(Controller c, int player) {
        c.visit(this, player);
    }

    public static PassMessage deserializePassMessage(String s){
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
