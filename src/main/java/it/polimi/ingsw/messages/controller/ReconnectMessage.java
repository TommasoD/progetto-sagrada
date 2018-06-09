package it.polimi.ingsw.messages.controller;

import com.google.gson.Gson;
import it.polimi.ingsw.controller.Controller;

public class ReconnectMessage extends ControllerMessage {

    private String id;

    public ReconnectMessage() {
        this.id = "reconnect";
    }

    public String serialize() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public void accept(Controller c, int player) {
        c.visit(this, player);
    }

    public static ReconnectMessage deserializeReconnectMessage(String s){
        Gson gson = new Gson();
        return gson.fromJson(s, ReconnectMessage.class);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
