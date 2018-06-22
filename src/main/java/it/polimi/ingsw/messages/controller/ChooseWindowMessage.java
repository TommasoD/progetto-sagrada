package it.polimi.ingsw.messages.controller;

import com.google.gson.Gson;
import it.polimi.ingsw.controller.Controller;

/**
 * Contains the name of the window chosen by a player at the start of the game.
 * Contains everything needed to be transformed into a Json string
 * to be sent across the network as a source of information.
 */
public class ChooseWindowMessage extends ControllerMessage {

    private String id;
    private String windowName;

    /**
     * Class constructor.
     */

    public ChooseWindowMessage(){
        id = "window";
    }

    /**
     * Class constructor specifying the name of the window.
     * @param windowName the name of the window.
     */

    public ChooseWindowMessage(String windowName){
        id = "window";
        this.windowName = windowName;
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

    public static ChooseWindowMessage deserializeChooseWindowMessage(String s){
        Gson gson = new Gson();
        return gson.fromJson(s, ChooseWindowMessage.class);
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
     * @return the name of the window.
     */

    public String getWindowName() {
        return windowName;
    }

    /**
     *
     * @param windowName the name of the window.
     */

    public void setWindowName(String windowName) {
        this.windowName = windowName;
    }
}
