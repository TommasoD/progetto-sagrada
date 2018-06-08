package it.polimi.ingsw.messages.controller;

import com.google.gson.Gson;
import it.polimi.ingsw.controller.Controller;

public class ToolCardEMessage extends ControllerMessage {

    private String id;
    private int num;

    public ToolCardEMessage(){
        id = "toolcardE";
    }

    public ToolCardEMessage(int num){
        id = "toolcardE";
        this.num = num;
    }
    public String serialize() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public void accept(Controller c, int player) {
        c.visit(this, player);
    }

    public static ToolCardEMessage deserializeToolCardEMessage(String s){
        Gson gson = new Gson();
        return gson.fromJson(s, ToolCardEMessage.class);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
