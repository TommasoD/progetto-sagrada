package it.polimi.ingsw.messages.client;

import com.google.gson.Gson;
import it.polimi.ingsw.client.ClientManager;

/**
 * Notifies the player of a connection-related event.
 * In particular, contains the name of the player who caused the event and a string
 * identifying the event:
 * disconnect when a player closes the terminal;
 * suspended when a player is suspended for inactivity;
 * reconnect when a player asks to exit the suspended state.
 */
public class NotificationMessage extends ClientMessage {

    private String id;
    private String username;
    private String event;

    /**
     * Class constructor.
     */

    public NotificationMessage(){
        id = "notification";
    }

    /**
     * Class constructor specifying the event and the player.
     * @param username the name of the player.
     * @param event the event.
     */

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

    /**
     * Takes a Json representation of the class as a string and
     * converts it in the corresponding object.
     * @param s Json representation of the class.
     * @return an instance of the class.
     */

    public static NotificationMessage deserializeNotificationMessage(String s){
        Gson gson = new Gson();
        return gson.fromJson(s, NotificationMessage.class);
    }

    /**
     *
     * @return the ID.
     */

    public String getId() {
        return id;
    }

    /**
     *
     * @param id the ID.
     */

    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return the event.
     */

    public String getEvent() {
        return event;
    }

    /**
     *
     * @param event the event.
     */

    public void setEvent(String event) {
        this.event = event;
    }

    /**
     *
     * @return the name of the player.
     */

    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username the name of the player.
     */

    public void setUsername(String username) {
        this.username = username;
    }
}