package it.polimi.ingsw.messages.controller;

import com.google.gson.Gson;
import it.polimi.ingsw.controller.Controller;

public class UnexpectedMessage extends ControllerMessage {

    public void accept(Controller c, int player) {
        c.visit(this, player);
    }

    public String serialize() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static UnexpectedMessage deserializeUnexpectedMessage(String s){
        Gson gson = new Gson();
        return gson.fromJson(s, UnexpectedMessage.class);
    }

}
