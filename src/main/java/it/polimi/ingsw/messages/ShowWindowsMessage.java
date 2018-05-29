package it.polimi.ingsw.messages;

import com.google.gson.Gson;

public class ShowWindowsMessage extends Message {

    private String id;
    private String name1;
    private String w1;
    private String name2;
    private String w2;
    private String name3;
    private String w3;
    private String name4;
    private String w4;

    public ShowWindowsMessage(){}

    public ShowWindowsMessage(String name1, String w1, String name2, String w2,
                              String name3, String w3, String name4, String w4){
        this.id = "windows";
        this.name1 = name1;
        this.w1 = w1;
        this.name2 = name2;
        this.w2 = w2;
        this.name3 = name3;
        this.w3 = w3;
        this.name4 = name4;
        this.w4 = w4;
    }

    public String serialize() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public ShowWindowsMessage deserialize(String s){
        Gson gson = new Gson();
        return gson.fromJson(s, ShowWindowsMessage.class);
    }

    public String getId() {
        return id;
    }

    public String getW1() {
        return w1;
    }

    public String getW2() {
        return w2;
    }

    public String getW3() {
        return w3;
    }

    public String getW4() {
        return w4;
    }

    public String getName1() {
        return name1;
    }

    public String getName2() {
        return name2;
    }

    public String getName3() {
        return name3;
    }

    public String getName4() {
        return name4;
    }
}
