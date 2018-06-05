package it.polimi.ingsw.messages.controller;

import com.google.gson.Gson;
import it.polimi.ingsw.controller.Controller;

public class ToolCardBMessage extends ControllerMessage {

    private String id;
    private int x;
    private int y;
    private int a;
    private int b;


    public ToolCardBMessage(){
        id = "toolcardB";
    }

    public ToolCardBMessage(int x, int y, int a, int b){
        id = "toolcardB";
        this.x = x;
        this.y = y;
        this.a = a;
        this.b = b;
    }
    public String serialize() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public void accept(Controller c, int player) {
        c.visit(this);
    }

    public ToolCardBMessage deserialize(String s){
        Gson gson = new Gson();
        return gson.fromJson(s, ToolCardBMessage.class);
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

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
}
