package it.polimi.ingsw.messages.client;

import com.google.gson.Gson;
import it.polimi.ingsw.model.WindowPattern;
import it.polimi.ingsw.client.ClientManager;

public class ShowWindowsMessage extends ClientMessage {

    private String id;
    private WindowPattern w1;
    private WindowPattern w2;
    private WindowPattern w3;
    private WindowPattern w4;

    public ShowWindowsMessage(){
        id = "windows";
    }

    public ShowWindowsMessage(WindowPattern w1, WindowPattern w2, WindowPattern w3, WindowPattern w4){
        id = "windows";
        this.w1 = w1;
        this.w2 = w2;
        this.w3 = w3;
        this.w4 = w4;
    }

    public String serialize() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public void accept(ClientManager c, int player) {
        c.visit(this, player);
    }

    public ShowWindowsMessage deserialize(String s){
        Gson gson = new Gson();
        return gson.fromJson(s, ShowWindowsMessage.class);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public WindowPattern getW1() {
        return w1;
    }

    public void setW1(WindowPattern w1) {
        this.w1 = w1;
    }

    public WindowPattern getW2() {
        return w2;
    }

    public void setW2(WindowPattern w2) {
        this.w2 = w2;
    }

    public WindowPattern getW3() {
        return w3;
    }

    public void setW3(WindowPattern w3) {
        this.w3 = w3;
    }

    public WindowPattern getW4() {
        return w4;
    }

    public void setW4(WindowPattern w4) {
        this.w4 = w4;
    }
}
