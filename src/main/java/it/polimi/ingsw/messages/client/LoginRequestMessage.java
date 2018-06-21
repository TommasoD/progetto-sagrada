package it.polimi.ingsw.messages.client;

import com.google.gson.Gson;
import it.polimi.ingsw.client.ClientManager;

/**
 * Notifies the player of the start of the game
 * and the need for a personal username in order to play
 */
public class LoginRequestMessage extends ClientMessage {

    private String id;

    /**
     * Class constructor.
     */

    public LoginRequestMessage(){
        id = "login";
    }

    public String serialize() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public void accept(ClientManager c) {
        c.visit(this);
    }

    /**
     * Takes a Json representation of the class as a string and
     * converts it in the corresponding object.
     * @param s Json representation of the class.
     * @return an instance of the class.
     */

    public static LoginRequestMessage deserializeLoginRequestMessage(String s){
        Gson gson = new Gson();
        return gson.fromJson(s, LoginRequestMessage.class);
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
