package it.polimi.ingsw.messages.client;

import com.google.gson.Gson;
import it.polimi.ingsw.client.ClientManager;
import it.polimi.ingsw.model.ToolCard;
import it.polimi.ingsw.model.objectives.PrivateObjective;
import it.polimi.ingsw.model.objectives.publicobjectives.PublicObjective;

import java.util.List;

public class ShowTableMessage extends ClientMessage {

    private String id;
    private PrivateObjective privateObjective;
    private List<ToolCard> toolCards;
    private List<String> publicObjective;

    public ShowTableMessage(){
        id = "show_table";
    }

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

    public static ShowTableMessage deserializeShowTableMessage(String s){
        Gson gson = new Gson();
        return gson.fromJson(s, ShowTableMessage.class);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PrivateObjective getPrivateObjective() {
        return privateObjective;
    }

    public void setPrivateObjective(PrivateObjective privateObjective) {
        this.privateObjective = privateObjective;
    }

    public List<ToolCard> getToolCards() {
        return toolCards;
    }

    public void setToolCards(List<ToolCard> toolCards) {
        this.toolCards = toolCards;
    }

    public List<String> getPublicObjective() {
        return publicObjective;
    }

    public void setPublicObjective(List<String> publicObjective) {
        this.publicObjective = publicObjective;
    }
}