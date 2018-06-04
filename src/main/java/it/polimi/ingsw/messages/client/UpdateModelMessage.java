package it.polimi.ingsw.messages.client;

import com.google.gson.Gson;
import it.polimi.ingsw.model.Die;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.client.ClientManager;

import java.util.List;

public class UpdateModelMessage extends ClientMessage {

    private String id;
    private List<Player> players;
    private List<Die> draft;
    private List<Die> roundTrack;

    public UpdateModelMessage(){
        id = "update";
    }

    public UpdateModelMessage(List<Player> players, List<Die> draft, List<Die> roundTrack){
        id = "update";
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

    public UpdateModelMessage deserialize(String s){
        Gson gson = new Gson();
        return gson.fromJson(s, UpdateModelMessage.class);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Die> getDraft() {
        return draft;
    }

    public void setDraft(List<Die> draft) {
        this.draft = draft;
    }

    public List<Die> getRoundTrack() {
        return roundTrack;
    }

    public void setRoundTrack(List<Die> roundTrack) {
        this.roundTrack = roundTrack;
    }
}
