package it.polimi.ingsw.messages;

import com.google.gson.Gson;

public class SetDieMessage extends Message {

    private String id;
    private int x;
    private int y;
    private int index;

    public SetDieMessage(){}

    public SetDieMessage(int x, int y, int index) {
        this.id = "place";
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getIndex() {
        return index;
    }

}
