package it.polimi.ingsw.messages.client;

import com.google.gson.Gson;
import it.polimi.ingsw.model.WindowPattern;
import it.polimi.ingsw.client.ClientManager;

/**
 * Contains four window patterns from which the player
 * will choose the one he wants to play the game with.
 * <p>
 * Contains everything needed to be transformed into a Json string
 * to be sent across the network as a source of information.
 */
public class ShowWindowsMessage extends ClientMessage {

    private String id;
    private WindowPattern w1;
    private WindowPattern w2;
    private WindowPattern w3;
    private WindowPattern w4;

    /**
     * Class constructor.
     */

    public ShowWindowsMessage(){
        id = "windows";
    }

    /**
     * Class constructor specifying four window patterns.
     * @param w1 the first window pattern.
     * @param w2 the second window pattern.
     * @param w3 the third window pattern.
     * @param w4 the fourth window pattern.
     */

    public ShowWindowsMessage(WindowPattern w1, WindowPattern w2, WindowPattern w3, WindowPattern w4){
        id = "windows";
        this.w1 = w1;
        this.w2 = w2;
        this.w3 = w3;
        this.w4 = w4;
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

    public static ShowWindowsMessage deserializeShowWindowsMessage(String s){
        Gson gson = new Gson();
        return gson.fromJson(s, ShowWindowsMessage.class);
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
     * @return the first window pattern.
     */

    public WindowPattern getW1() {
        return w1;
    }

    /**
     *
     * @param w1 the first window pattern.
     */

    public void setW1(WindowPattern w1) {
        this.w1 = w1;
    }

    /**
     *
     * @return the second window pattern.
     */

    public WindowPattern getW2() {
        return w2;
    }

    /**
     *
     * @param w2 the second window pattern.
     */

    public void setW2(WindowPattern w2) {
        this.w2 = w2;
    }

    /**
     *
     * @return the third window pattern.
     */

    public WindowPattern getW3() {
        return w3;
    }

    /**
     *
     * @param w3 the third window pattern.
     */

    public void setW3(WindowPattern w3) {
        this.w3 = w3;
    }

    /**
     *
     * @return the fourth window pattern.
     */

    public WindowPattern getW4() {
        return w4;
    }

    /**
     *
     * @param w4 the fourth window pattern.
     */

    public void setW4(WindowPattern w4) {
        this.w4 = w4;
    }
}
