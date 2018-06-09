package it.polimi.ingsw.messages.client;

import com.google.gson.Gson;
import it.polimi.ingsw.client.ClientManager;

public class NotificationMessage extends ClientMessage {

    private String id;
    private String username;
    private String event;       //reconnect or disconnect or suspended

    public NotificationMessage(){
        id = "notification";
    }

    public NotificationMessage(String username, String event){
        id = "notification";
        this.username = username;
        this.event = event;
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

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}