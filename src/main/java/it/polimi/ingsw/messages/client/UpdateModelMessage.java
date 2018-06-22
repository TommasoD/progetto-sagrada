package it.polimi.ingsw.messages.client;

import com.google.gson.Gson;
import it.polimi.ingsw.model.Die;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.client.ClientManager;

import java.util.List;

/**
 * Contains all the elements of the game:
 * - round is the index of the current round;
 * - players is the list off all the players involved in the game;
 * - draft contains all the dice in the draft pool;
 * - roundTrack contains all the dice in the round track.
 * <p>
 * Contains everything needed to be transformed into a Json string
 * to be sent across the network as a source of information.
 */
public class UpdateModelMessage extends ClientMessage {

    private String id;
    private int round;
    private List<Player> players;
    private List<Die> draft;
    private List<Die> roundTrack;

    /**
     * Class constructor.
     */

    public UpdateModelMessage(){
        id = "update";
    }

    /**
     * Class constructor specifying round, players involved, draft pool and
     * round track in a moment of the game.
     * @param round the index of the current round.
     * @param players the list of the players involved in the game.
     * @param draft the list of the dice in the draft pool.
     * @param roundTrack the list of the dice in the round track.
     */

    public UpdateModelMessage(int round, List<Player> players, List<Die> draft, List<Die> roundTrack){
        id = "update";
        this.round = round;
        this.players = players;
        this.draft = draft;
        this.roundTrack = roundTrack;
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

    public static UpdateModelMessage deserializeUpdateModelMessage(String s){
        Gson gson = new Gson();
        return gson.fromJson(s, UpdateModelMessage.class);
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
     * @return the list of the players involved in the game.
     */

    public List<Player> getPlayers() {
        return players;
    }

    /**
     *
     * @param players the list of the players involved in the game.
     */

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    /**
     *
     * @return the list of the dice in the draft pool.
     */

    public List<Die> getDraft() {
        return draft;
    }

    /**
     *
     * @param draft the list of the dice in the draft pool.
     */

    public void setDraft(List<Die> draft) {
        this.draft = draft;
    }

    /**
     *
     * @return the list of the dice in the round track.
     */

    public List<Die> getRoundTrack() {
        return roundTrack;
    }

    /**
     *
     * @param roundTrack the list of the dice in the round track.
     */

    public void setRoundTrack(List<Die> roundTrack) {
        this.roundTrack = roundTrack;
    }

    /**
     *
     * @return the index of the current round.
     */

    public int getRound() {
        return round;
    }

    /**
     *
     * @param round the index of the current round.
     */

    public void setRound(int round) {
        this.round = round;
    }
}
