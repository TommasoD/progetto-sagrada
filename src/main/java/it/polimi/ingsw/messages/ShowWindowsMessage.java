package it.polimi.ingsw.messages;

import com.google.gson.Gson;

public class ShowWindowsMessage extends Message {

    private String id;
    private String name1;
    private int token1;
    private String w1;
    private String name2;
    private int token2;
    private String w2;
    private String name3;
    private int token3;
    private String w3;
    private String name4;
    private int token4;
    private String w4;

    public ShowWindowsMessage(){}

    public ShowWindowsMessage(String name1, int token1, String w1, String name2, int token2, String w2,
                              String name3, int token3, String w3, String name4, int token4, String w4){
        this.id = "windows";
        this.name1 = name1;
        this.token1 = token1;
        this.w1 = w1;
        this.name2 = name2;
        this.token2 = token2;
        this.w2 = w2;
        this.name3 = name3;
        this.token3 = token3;
        this.w3 = w3;
        this.name4 = name4;
        this.token4 = token4;
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

    public int getToken1() {
        return token1;
    }

    public String getName2() {
        return name2;
    }

    public int getToken2() {
        return token2;
    }

    public String getName3() {
        return name3;
    }

    public int getToken3() {
        return token3;
    }

    public String getName4() {
        return name4;
    }

    public int getToken4() {
        return token4;
    }
}
