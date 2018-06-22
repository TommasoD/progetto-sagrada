package it.polimi.ingsw.messages.controller;

import com.google.gson.Gson;
import it.polimi.ingsw.controller.Controller;

/**
 * Default class created whenever the parser fails to identify a Json string.
 */
public class UnexpectedMessage extends ControllerMessage {

    public void accept(Controller c, int player) {
        c.visit(this, player);
    }

    public String serialize() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
