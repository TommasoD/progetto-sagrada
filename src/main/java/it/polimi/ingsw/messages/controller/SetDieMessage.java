package it.polimi.ingsw.messages.controller;

import com.google.gson.Gson;
import it.polimi.ingsw.controller.Controller;

public class SetDieMessage extends ControllerMessage {

    private String id;
    private int x;
    private int y;
    private int index;

    public SetDieMessage(){
        id = "place";
    }

    public SetDieMessage(int x, int y, int index) {
        id = "place";
        this.x = x;
        this.y = y;
        this.index = index;
    }

    public String serialize() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public void accept(Controller c, int player) {
        c.visit(this, player);
    }

    public static SetDieMessage deserializeSetDieMessage(String s){
        Gson gson = new Gson();
        return gson.fromJson(s, SetDieMessage.class);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
