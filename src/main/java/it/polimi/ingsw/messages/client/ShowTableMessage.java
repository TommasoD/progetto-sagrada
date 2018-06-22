package it.polimi.ingsw.messages.client;

import com.google.gson.Gson;
import it.polimi.ingsw.client.ClientManager;
import it.polimi.ingsw.model.ToolCard;
import it.polimi.ingsw.model.objectives.PrivateObjective;
import it.polimi.ingsw.model.objectives.publicobjectives.PublicObjective;

import java.util.List;

/**
 * Contains the list of the public objectives regarding the current game,
 * the private objective of a player and  the list off all the tool cards with
 * status and description of each one.
 * <p>
 * Contains everything needed to be transformed into a Json string
 * to be sent across the network as a source of information.
 */
public class ShowTableMessage extends ClientMessage {

    private String id;
    private PrivateObjective privateObjective;
    private List<ToolCard> toolCards;
    private List<String> publicObjective;

    /**
     * Class constructor.
     */

    public ShowTableMessage(){
        id = "show_table";
    }

    /**
     * Class constructor specifying the private objective of the player, the list of all
     * tool cards and the list of the current public objectives.
     * @param privateObjective the private objective of the player.
     * @param toolCards the list of all tool cards.
     * @param publicObjective the list of public objectives.
     */

    public ShowTableMessage(PrivateObjective privateObjective, List<ToolCard> toolCards, List<String> publicObjective){
        id = "show_table";
        this.privateObjective = privateObjective;
        this.toolCards = toolCards;
        this.publicObjective = publicObjective;
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

    public static ShowTableMessage deserializeShowTableMessage(String s){
        Gson gson = new Gson();
        return gson.fromJson(s, ShowTableMessage.class);
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
     * @return the private objective of the player
     */

    public PrivateObjective getPrivateObjective() {
        return privateObjective;
    }

    /**
     *
     * @param privateObjective the private objective of the player
     */

    public void setPrivateObjective(PrivateObjective privateObjective) {
        this.privateObjective = privateObjective;
    }

    /**
     *
     * @return the list of all tool cards.
     */

    public List<ToolCard> getToolCards() {
        return toolCards;
    }

    /**
     *
     * @param toolCards the list of all tool cards.
     */

    public void setToolCards(List<ToolCard> toolCards) {
        this.toolCards = toolCards;
    }

    /**
     *
     * @return the list of public objectives.
     */

    public List<String> getPublicObjective() {
        return publicObjective;
    }

    /**
     *
     * @param publicObjective the list of public objectives.
     */

    public void setPublicObjective(List<String> publicObjective) {
        this.publicObjective = publicObjective;
    }
}