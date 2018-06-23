package it.polimi.ingsw.messages.controller;

import com.google.gson.Gson;
import it.polimi.ingsw.controller.Controller;

/**
 * Contains the information about the use of a tool card number 1, 5, 6, 10 or 11 as
 * all of them regard a die from the draft pool and usually give the the player a choice
 * between two actions, like increasing or decreasing the value of a selected die.
 * <p>
 * Contains everything needed to be transformed into a Json string
 * to be sent across the network as a source of information.
 */
public class ToolCardAMessage extends ControllerMessage {

    private String id;
    private int num;
    private int dieIndex;
    private int action;

    /**
     * Class constructor.
     */

    public ToolCardAMessage(){
        id = "toolcardA";
    }

    /**
     * Class constructor specifying the number of the tool card (1, 5, 6, 10 or 11),
     * the index of a die from the draft pool and an identifier of the action.
     * @param num the number of the tool card.
     * @param dieIndex the index of the die from the draft pool.
     * @param action the identifier of the action which depends on the particular tool card.
     */

    public ToolCardAMessage(int num, int dieIndex, int action){
        id = "toolcardA";
        this.num = num;
        this.dieIndex = dieIndex;
        this.action = action;
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

    public static ToolCardAMessage deserializeToolCardAMessage(String s){
        Gson gson = new Gson();
        return gson.fromJson(s, ToolCardAMessage.class);
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
     * @return the index of the die from the draft pool.
     */

    public int getDieIndex() {
        return dieIndex;
    }

    /**
     *
     * @param dieIndex the index of the die from the draft pool.
     */

    public void setDieIndex(int dieIndex) {
        this.dieIndex = dieIndex;
    }

    /**
     *
     * @return the identifier of the action which depends on the particular tool card.
     */

    public int getAction() {
        return action;
    }

    /**
     *
     * @param action the identifier of the action which depends on the particular tool card.
     */

    public void setAction(int action) {
        this.action = action;
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
