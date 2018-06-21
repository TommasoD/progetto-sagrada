package it.polimi.ingsw.messages.client;

import com.google.gson.Gson;
import it.polimi.ingsw.client.ClientManager;

/**
 * Notifies the player of the color of the die extracted from the bag
 * after using tool card 11.
 */
public class DieColorMessage extends ClientMessage {

    private String id;
    private String color;

    /**
     * Class constructor.
     */

    public DieColorMessage(){
        id = "die_color";
    }

    /**
     * Class constructor specifying a color.
     * @param color the color of an extracted die.
     */

    public DieColorMessage(String color){
        id = "die_color";
        this.color = color;
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

    public static DieColorMessage deserializeDieColorMessage(String s){
        Gson gson = new Gson();
        return gson.fromJson(s, DieColorMessage.class);
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
     * @return the color.
     */

    public String getColor() {
        return color;
    }

    /**
     *
     * @param color the color.
     */

    public void setColor(String color) {
        this.color = color;
    }
}
