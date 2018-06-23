package it.polimi.ingsw.messages.controller;

import com.google.gson.Gson;
import it.polimi.ingsw.controller.Controller;

/**
 * Notifies the controller that a player intend to look at the cards in the game, i.e.
 * all the tool cards with corresponding description and status (if already used or not),
 * the public objectives selected for the match and his own private objective.
 * <p>
 * Contains everything needed to be transformed into a Json string
 * to be sent across the network as a source of information.
 */
public class ShowTableRequestMessage extends ControllerMessage {

    private String id;

    /**
     * Class constructor.
     */

    public ShowTableRequestMessage() {
        this.id = "show_table";
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

    public static ShowTableRequestMessage deserializeShowTableRequestMessage(String s){
        Gson gson = new Gson();
        return gson.fromJson(s, ShowTableRequestMessage.class);
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
