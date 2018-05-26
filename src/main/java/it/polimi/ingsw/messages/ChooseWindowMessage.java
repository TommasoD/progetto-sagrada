package it.polimi.ingsw.messages;

import com.google.gson.Gson;

public class ChooseWindowMessage extends Message {

    private String id;
    private String windowName;

    public ChooseWindowMessage(){}

    public ChooseWindowMessage(String windowName){
        this.id = "window";
        this.windowName = windowName;
    }

    public String serialize() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public ChooseWindowMessage deserialize(String s){
        Gson gson = new Gson();
        return gson.fromJson(s, ChooseWindowMessage.class);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWindowName() {
        return windowName;
    }

    public void setWindowName(String windowName) {
        this.windowName = windowName;
    }
}
