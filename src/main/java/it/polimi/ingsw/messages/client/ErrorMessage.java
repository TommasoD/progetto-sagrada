package it.polimi.ingsw.messages.client;

import com.google.gson.Gson;
import it.polimi.ingsw.client.ClientManager;

/**
 * Contains an integer explaining the error occurred:
 * <p>
 * 1 -> username already used;<p>
 * 2 -> invalid move;<p>
 * 3 -> unexpected message from parser;<p>
 * 4 -> not enough tokens to use the card or already used a card this turn;<p>
 * 5 -> reconnection but player is online.<p>
 * Contains everything needed to be transformed into a Json string
 * to be sent across the network as a source of information.
 */
public class ErrorMessage extends ClientMessage {

    private String id;
    private int type;

    /**
     * Class constructor.
     */

    public ErrorMessage(){
        id = "error";
    }

    /**
     * Class constructor specifying the type of the error.
     * @param type the type of the error.
     */

    public ErrorMessage(int type){
        id = "error";
        this.type = type;
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

    public static ErrorMessage deserializeErrorMessage(String s){
        Gson gson = new Gson();
        return gson.fromJson(s, ErrorMessage.class);
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
     * @return the type of the error.
     */

    public int getType() {
        return type;
    }

    /**
     *
     * @param type the type of the error.
     */

    public void setType(int type) {
        this.type = type;
    }
}
