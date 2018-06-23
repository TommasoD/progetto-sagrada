package it.polimi.ingsw.messages.controller;

import com.google.gson.Gson;
import it.polimi.ingsw.controller.Controller;

/**
 * Contains the information about the use of a tool card number 7,
 * which in fact doesn't need any parameter.
 * <p>
 * Contains everything needed to be transformed into a Json string
 * to be sent across the network as a source of information.
 */
public class ToolCardEMessage extends ControllerMessage {

    private String id;
    private int num;

    /**
     * Class constructor.
     */

    public ToolCardEMessage(){
        id = "toolcardE";
    }

    /**
     * Class constructor specifying the number of the tool card.
     * @param num the number of the tool card.
     */

    public ToolCardEMessage(int num){
        id = "toolcardE";
        this.num = num;
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

    public static ToolCardEMessage deserializeToolCardEMessage(String s){
        Gson gson = new Gson();
        return gson.fromJson(s, ToolCardEMessage.class);
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
