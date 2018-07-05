package it.polimi.ingsw.messages.controller;

import com.google.gson.Gson;
import it.polimi.ingsw.controller.Controller;

/**
 * Contains the information about the use of a tool card number 8 or 9 as
 * all of them let the player place a die from the draft pool inside his window pattern
 * ignoring some restrictions.
 * <p>
 * Contains everything needed to be transformed into a Json string
 * to be sent across the network as a source of information.
 */
public class ToolCardDMessage extends ControllerMessage {

    private String id;
    private int num;
    private int dieIndex;
    private int x;
    private int y;

    /**
     * Class constructor.
     */

    public ToolCardDMessage(){
        id = "toolcardD";
    }

    /**
     * Class constructor specifying the number of the tool card (either 8 or 9),
     * the index of the die in the draft pool and the coordinates (x, y)
     * of where the player intend to place the die.
     * @param num the number of the tool card.
     * @param dieIndex the index of the die in the list representing the draft pool.
     * @param x the index of the row where to place the die.
     * @param y the index of the column where to place the die.
     */

    public ToolCardDMessage(int num, int dieIndex, int x, int y){
        id = "toolcardD";
        this.num = num;
        this.dieIndex = dieIndex;
        this.x = x;
        this.y = y;
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

    public static ToolCardDMessage deserializeToolCardDMessage(String s){
        Gson gson = new Gson();
        return gson.fromJson(s, ToolCardDMessage.class);
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
     * @return the index of the die in the list representing the draft pool.
     */

    public int getDieIndex() {
        return dieIndex;
    }

    /**
     *
     * @param dieIndex the index of the die in the list representing the draft pool.
     */

    public void setDieIndex(int dieIndex) {
        this.dieIndex = dieIndex;
    }

    /**
     *
     * @return the number of the tool card.
     */

    public int getNum() {
        return num;
    }

    /**
     *
     * @param num the number of the tool card.
     */

    public void setNum(int num) {
        this.num = num;
    }

    /**
     *
     * @return the index of the row where to place the die.
     */

    public int getX() {
        return x;
    }

    /**
     *
     * @param x the index of the row where to place the die.
     */

    public void setX(int x) {
        this.x = x;
    }

    /**
     *
     * @return the index of the column where to place the die.
     */

    public int getY() {
        return y;
    }

    /**
     *
     * @param y the index of the column where to place the die.
     */

    public void setY(int y) {
        this.y = y;
    }
}
