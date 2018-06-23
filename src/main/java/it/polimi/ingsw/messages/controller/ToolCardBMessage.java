package it.polimi.ingsw.messages.controller;

import com.google.gson.Gson;
import it.polimi.ingsw.controller.Controller;

/**
 * Contains the information about the use of a tool card number 2 or 3 as
 * all of them let the player move a die inside his window pattern from
 * a position (x, y) to a new position (a, b).
 * <p>
 * Contains everything needed to be transformed into a Json string
 * to be sent across the network as a source of information.
 */
public class ToolCardBMessage extends ControllerMessage {

    private String id;
    private int num;
    private int x;
    private int y;
    private int a;
    private int b;

    /**
     * Class constructor.
     */

    public ToolCardBMessage(){
        id = "toolcardB";
    }

    /**
     * Class constructor specifying the number of the tool card (either 2 or 3),
     * the position of the die to move (x, y) and where move it (a, b).
     * @param num the number of the tool card.
     * @param x the index of the row where the die is placed.
     * @param y the index of the column where the die is placed.
     * @param a the index of the row where the die will be moved to.
     * @param b the index of the column where the die will be moved to.
     */

    public ToolCardBMessage(int num, int x, int y, int a, int b){
        id = "toolcardB";
        this.num = num;
        this.x = x;
        this.y = y;
        this.a = a;
        this.b = b;
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

    public static ToolCardBMessage deserializeToolCardBMessage(String s){
        Gson gson = new Gson();
        return gson.fromJson(s, ToolCardBMessage.class);
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
     * @return the index of the row where the die is placed.
     */

    public int getX() {
        return x;
    }

    /**
     *
     * @param x the index of the row where the die is placed.
     */

    public void setX(int x) {
        this.x = x;
    }

    /**
     *
     * @return the index of the column where the die is placed.
     */

    public int getY() {
        return y;
    }

    /**
     *
     * @param y the index of the column where the die is placed.
     */

    public void setY(int y) {
        this.y = y;
    }

    /**
     *
     * @return the index of the row where the die will be moved to.
     */

    public int getA() {
        return a;
    }

    /**
     *
     * @param a the index of the row where the die will be moved to.
     */

    public void setA(int a) {
        this.a = a;
    }

    /**
     *
     * @return the index of the column where the die will be moved to.
     */

    public int getB() {
        return b;
    }

    /**
     *
     * @param b the index of the column where the die will be moved to.
     */

    public void setB(int b) {
        this.b = b;
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
}
