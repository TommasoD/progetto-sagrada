package it.polimi.ingsw.messages.controller;

import com.google.gson.Gson;
import it.polimi.ingsw.controller.Controller;

public class ChooseWindowMessage extends ControllerMessage {

    private String id;
    private String windowName;

    public ChooseWindowMessage(){
        id = "window";
    }

    public ChooseWindowMessage(String windowName){
        id = "window";
        this.windowName = windowName;
    }

    public String serialize() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public void accept(Controller c, int player) {
        c.visit(this, player);
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
