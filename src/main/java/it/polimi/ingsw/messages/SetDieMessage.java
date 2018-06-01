package it.polimi.ingsw.messages;

import com.google.gson.Gson;

public class SetDieMessage extends Message {

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

    public SetDieMessage deserialize(String s){
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
