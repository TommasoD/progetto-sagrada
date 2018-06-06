package it.polimi.ingsw.messages.controller;

import com.google.gson.Gson;
import it.polimi.ingsw.controller.Controller;

public class ToolCardCMessage extends ControllerMessage {

    private String id;
    private int num;
    private int x;
    private int y;
    private int a;
    private int b;
    private int x2;
    private int y2;
    private int a2;
    private int b2;


    public ToolCardCMessage(){
        id = "toolcardC";
    }

    public ToolCardCMessage(int num, int x, int y, int a, int b, int x2, int y2, int a2, int b2){
        id = "toolcardC";
        this.num = num;
        this.x = x;
        this.y = y;
        this.a = a;
        this.b = b;
        this.x2 = x2;
        this.y2 = y2;
        this.a2 = a2;
        this.b2 = b2;
    }
    public String serialize() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public void accept(Controller c, int player) {
        c.visit(this, player);
    }

    public ToolCardCMessage deserialize(String s){
        Gson gson = new Gson();
        return gson.fromJson(s, ToolCardCMessage.class);
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

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public int getA2() {
        return a2;
    }

    public void setA2(int a2) {
        this.a2 = a2;
    }

    public int getB2() {
        return b2;
    }

    public void setB2(int b2) {
        this.b2 = b2;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
