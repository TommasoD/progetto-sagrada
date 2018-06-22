package it.polimi.ingsw.messages.controller;

import com.google.gson.Gson;
import it.polimi.ingsw.controller.Controller;

/**
 * Notifies the controller of the reconnection of a player after being suspended for inactivity.
 * <p>
 * Contains everything needed to be transformed into a Json string
 * to be sent across the network as a source of information.
 */
public class ReconnectMessage extends ControllerMessage {

    private String id;

    /**
     * Class constructor.
     */

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

    /**
     * Takes a Json representation of the class as a string and
     * converts it in the corresponding object.
     * @param s Json representation of the class.
     * @return an instance of the class.
     */

    public static ReconnectMessage deserializeReconnectMessage(String s){
        Gson gson = new Gson();
        return gson.fromJson(s, ReconnectMessage.class);
    }

    /**
     *
     * @return the ID.
     */

    public String getId() {
        return id;
    }

    /**
     *
     * @param id the ID.
     */

    public void setId(String id) {
        this.id = id;
    }
}
