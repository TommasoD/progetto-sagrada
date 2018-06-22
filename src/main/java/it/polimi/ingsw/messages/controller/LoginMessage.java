package it.polimi.ingsw.messages.controller;

import com.google.gson.Gson;
import it.polimi.ingsw.controller.Controller;

/**
 * Contains the username selected by a player right at the start of the match.
 * <p>
 * Contains everything needed to be transformed into a Json string
 * to be sent across the network as a source of information.
 */
public class LoginMessage extends ControllerMessage {

    private String id;
    private String username;

    /**
     * Class constructor.
     */

    public LoginMessage(){
        id = "login";
    }

    /**
     * Class constructor specifying the name chosen by a player.
     * @param username the selected name.
     */

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

    /**
     * Takes a Json representation of the class as a string and
     * converts it in the corresponding object.
     * @param s Json representation of the class.
     * @return an instance of the class.
     */

    public static LoginMessage deserializeLoginMessage(String s){
        Gson gson = new Gson();
        return gson.fromJson(s, LoginMessage.class);
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

    /**
     *
     * @return the selected name.
     */

    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username the selected name.
     */

    public void setUsername(String username) {
        this.username = username;
    }
}
