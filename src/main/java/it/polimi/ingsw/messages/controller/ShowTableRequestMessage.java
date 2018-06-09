package it.polimi.ingsw.messages.controller;

import com.google.gson.Gson;
import it.polimi.ingsw.controller.Controller;

public class ShowTableRequestMessage extends ControllerMessage {

    private String id;

    public ShowTableRequestMessage() {
        this.id = "show_table";
    }

    public String serialize() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public void accept(Controller c, int player) {
        c.visit(this, player);
    }

    public static ShowTableRequestMessage deserializeShowTableRequestMessage(String s){
        Gson gson = new Gson();
        return gson.fromJson(s, ShowTableRequestMessage.class);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
