package it.polimi.ingsw.messages.client;

import com.google.gson.Gson;
import it.polimi.ingsw.client.ClientManager;

public class DieColorMessage extends ClientMessage {

    private String id;
    private String color;

    public DieColorMessage(){
        id = "die_color";
    }

    public DieColorMessage(String color){
        id = "die_color";
        this.color = color;
    }

    public String serialize() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public void accept(ClientManager c) {
        c.visit(this);
    }

    public static DieColorMessage deserializeDieColorMessage(String s){
        Gson gson = new Gson();
        return gson.fromJson(s, DieColorMessage.class);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
