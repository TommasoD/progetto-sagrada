package it.polimi.ingsw.messages.client;

import com.google.gson.Gson;
import it.polimi.ingsw.client.ClientManager;

public class NotificationMessage extends ClientMessage {

    private String id;

    public NotificationMessage(){
        id = "notification";
    }

    public String serialize() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public void accept(ClientManager c) {
        c.visit(this);
    }

    public static NotificationMessage deserializeNotificationMessage(String s){
        Gson gson = new Gson();
        return gson.fromJson(s, NotificationMessage.class);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}