package it.polimi.ingsw.messages.controller;

import com.google.gson.Gson;
import it.polimi.ingsw.controller.Controller;

/**
 * Contains the information about the placement of a die inside a window pattern, i.e.
 * the index of the die in the list representing the draft pool and
 * the coordinates (x, y) corresponding to a slot in the window.
 * <p>
 * Contains everything needed to be transformed into a Json string
 * to be sent across the network as a source of information.
 */
public class SetDieMessage extends ControllerMessage {

    private String id;
    private int x;
    private int y;
    private int index;

    /**
     * Class constructor.
     */

    public SetDieMessage(){
        id = "place";
    }

    /**
     * Class constructor specifying coordinates and the index of the die.
     * @param x the index of the row.
     * @param y the index of the column.
     * @param index the index of the die.
     */

    public SetDieMessage(int x, int y, int index) {
        id = "place";
        this.x = x;
        this.y = y;
        this.index = index;
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

    public static SetDieMessage deserializeSetDieMessage(String s){
        Gson gson = new Gson();
        return gson.fromJson(s, SetDieMessage.class);
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
     * @return the index of the row.
     */

    public int getX() {
        return x;
    }

    /**
     *
     * @param x the index of the row.
     */

    public void setX(int x) {
        this.x = x;
    }

    /**
     *
     * @return the index of the column.
     */

    public int getY() {
        return y;
    }

    /**
     *
     * @param y the index of the column.
     */

    public void setY(int y) {
        this.y = y;
    }

    /**
     *
     * @return the index of the die.
     */

    public int getIndex() {
        return index;
    }

    /**
     *
     * @param index the index of the die.
     */

    public void setIndex(int index) {
        this.index = index;
    }
}
