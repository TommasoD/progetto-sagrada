package it.polimi.ingsw.messages.client;

import com.google.gson.Gson;
import it.polimi.ingsw.client.ClientManager;

public class ShowTableMessage extends ClientMessage {

    private String id;

    public ShowTableMessage(){
        id = "show_table";
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

}