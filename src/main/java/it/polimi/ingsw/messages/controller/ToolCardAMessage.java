package it.polimi.ingsw.messages.controller;

import com.google.gson.Gson;
import it.polimi.ingsw.controller.Controller;

public class ToolCardAMessage extends ControllerMessage {

    private String id;
    private int num;
    private int dieIndex;
    private int action;

    public ToolCardAMessage(){
        id = "toolcardA";
    }

    public ToolCardAMessage(int num, int dieIndex, int action){
        id = "toolcardA";
        this.num = num;
        this.dieIndex = dieIndex;
        this.action = action;
    }
    public String serialize() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public void accept(Controller c, int player) {
        c.visit(this, player);
    }

    public ToolCardAMessage deserialize(String s){
        Gson gson = new Gson();
        return gson.fromJson(s, ToolCardAMessage.class);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDieIndex() {
        return dieIndex;
    }

    public void setDieIndex(int dieIndex) {
        this.dieIndex = dieIndex;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
