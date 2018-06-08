package it.polimi.ingsw.messages.controller;

import com.google.gson.Gson;
import it.polimi.ingsw.controller.Controller;

public class ToolCardDMessage extends ControllerMessage {

    private String id;
    private int num;
    private int dieIndex;
    private int x;
    private int y;

    public ToolCardDMessage(){
        id = "toolcardD";
    }

    public ToolCardDMessage(int num, int dieIndex, int x, int y){
        id = "toolcardD";
        this.num = num;
        this.dieIndex = dieIndex;
        this.x = x;
        this.y = y;
    }
    public String serialize() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public void accept(Controller c, int player) {
        c.visit(this, player);
    }

    public static ToolCardDMessage deserializeToolCardDMessage(String s){
        Gson gson = new Gson();
        return gson.fromJson(s, ToolCardDMessage.class);
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

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
