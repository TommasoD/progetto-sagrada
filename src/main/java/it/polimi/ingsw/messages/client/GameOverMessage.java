package it.polimi.ingsw.messages.client;

import com.google.gson.Gson;
import it.polimi.ingsw.client.ClientManager;

/**
 * Notifies the player of the end of the game, telling the name of the winner.
 */
public class GameOverMessage extends ClientMessage {

    private String id;
    private String winner;

    /**
     * Class constructor.
     */

    public GameOverMessage(){
        id = "game_over";
    }

    /**
     * Class constructor specifying the name of the winner.
     * @param winner the name of the winner.
     */

    public GameOverMessage(String winner){
        id = "game_over";
        this.winner = winner;
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

    public static GameOverMessage deserializeGameOverMessage(String s){
        Gson gson = new Gson();
        return gson.fromJson(s, GameOverMessage.class);
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
     * @return the name of the winner.
     */

    public String getWinner() {
        return winner;
    }

    /**
     *
     * @param winner the name of the winner.
     */

    public void setWinner(String winner) {
        this.winner = winner;
    }
}