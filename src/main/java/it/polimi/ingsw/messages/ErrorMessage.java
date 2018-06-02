package it.polimi.ingsw.messages;

import com.google.gson.Gson;

public class ErrorMessage extends Message{

    private String id;
    private int type;

    /*
        type 0 = unexpected error when parsing
        type 1 =
        type 2 =
        type 3 =
     */

    public ErrorMessage(){
        id = "error";
    }

    public ErrorMessage(int type){
        id = "error";
        this.type = type;
    }

    public String serialize() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public ErrorMessage deserialize(String s){
        Gson gson = new Gson();
        return gson.fromJson(s, ErrorMessage.class);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
