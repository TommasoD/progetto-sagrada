package it.polimi.ingsw.messages.controller;

import com.google.gson.Gson;
import it.polimi.ingsw.controller.Controller;

/**
 * Contains the information about the use of a tool card number 4 or 12 as
 * all of them let the player move two dice inside his window pattern from
 * two different positions (x, y) and (x2, y2) to two new ones (a, b) and (a2, b2).
 * <p>
 * Contains everything needed to be transformed into a Json string
 * to be sent across the network as a source of information.
 */
public class ToolCardCMessage extends ControllerMessage {

    private String id;
    private int num;
    private int x;
    private int y;
    private int a;
    private int b;
    private int x2;
    private int y2;
    private int a2;
    private int b2;

    /**
     * Class constructor.
     */

    public ToolCardCMessage(){
        id = "toolcardC";
    }

    /**
     * Class constructor specifying the number of the tool card, the coordinates
     * of two dice in the window and where to move them.
     * @param num the number of the tool card.
     * @param x the index of the row where the first die is placed.
     * @param y the index of the column where the first die is placed.
     * @param a the index of the row where the first die will be moved to.
     * @param b the index of the column where the first die will be moved to.
     * @param x2 the index of the row where the second die is placed.
     * @param y2 the index of the column where the second die is placed.
     * @param a2 the index of the row where the second die will be moved to.
     * @param b2 the index of the column where the second die will be moved to.
     */

    public ToolCardCMessage(int num, int x, int y, int a, int b, int x2, int y2, int a2, int b2){
        id = "toolcardC";
        this.num = num;
        this.x = x;
        this.y = y;
        this.a = a;
        this.b = b;
        this.x2 = x2;
        this.y2 = y2;
        this.a2 = a2;
        this.b2 = b2;
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

    public static ToolCardCMessage deserializeToolCardCMessage(String s){
        Gson gson = new Gson();
        return gson.fromJson(s, ToolCardCMessage.class);
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
     * @return the index of the row where the first die is placed.
     */

    public int getX() {
        return x;
    }

    /**
     *
     * @param x the index of the row where the first die is placed.
     */

    public void setX(int x) {
        this.x = x;
    }

    /**
     *
     * @return the index of the column where the first die is placed.
     */

    public int getY() {
        return y;
    }

    /**
     *
     * @param y the index of the column where the first die is placed.
     */

    public void setY(int y) {
        this.y = y;
    }

    /**
     *
     * @return the index of the row where the first die will be moved to.
     */

    public int getA() {
        return a;
    }

    /**
     *
     * @param a the index of the row where the first die will be moved to.
     */

    public void setA(int a) {
        this.a = a;
    }

    /**
     *
     * @return the index of the column where the first die will be moved to.
     */

    public int getB() {
        return b;
    }

    /**
     *
     * @param b the index of the column where the first die will be moved to.
     */

    public void setB(int b) {
        this.b = b;
    }

    /**
     *
     * @return the index of the row where the second die is placed.
     */

    public int getX2() {
        return x2;
    }

    /**
     *
     * @param x2 the index of the row where the second die is placed.
     */

    public void setX2(int x2) {
        this.x2 = x2;
    }

    /**
     *
     * @return the index of the column where the second die is placed.
     */

    public int getY2() {
        return y2;
    }

    /**
     *
     * @param y2 the index of the column where the second die is placed.
     */

    public void setY2(int y2) {
        this.y2 = y2;
    }

    /**
     *
     * @return the index of the row where the second die will be moved to.
     */

    public int getA2() {
        return a2;
    }

    /**
     *
     * @param a2 the index of the row where the second die will be moved to.
     */

    public void setA2(int a2) {
        this.a2 = a2;
    }

    /**
     *
     * @return the index of the column where the second die will be moved to.
     */

    public int getB2() {
        return b2;
    }

    /**
     *
     * @param b2 the index of the column where the second die will be moved to.
     */

    public void setB2(int b2) {
        this.b2 = b2;
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
