package it.polimi.ingsw.server;

import it.polimi.ingsw.utils.Observable;
import it.polimi.ingsw.utils.Observer;
import java.util.ArrayList;

/**
 * Contains a reference to all the client connections; when someone sends a message
 * to the server side, the class notifies the controller. Moreover, whenever the model
 * changes and a message is created as an answer, it forwards the message to the correct
 * connection thread, so that it can be sent across the network to the right client.
 */

public class GameManager extends Observable<String> implements Observer<String> {

    protected ArrayList<ClientHandler> playerList;

    /**
     * Class constructor.
     */

    public GameManager() {
        playerList = new ArrayList<ClientHandler>();
    }

    /**
     * Sends a message to all the players in the game, when notified by the model.
     * @param message the message depicting the event.
     */

    public void update(String message) {
        for(ClientHandler c : playerList){
            c.send(message);
        }
    }

    /**
     * Sends a message to a player, when notified by the model.
     * @param message the message depicting the event.
     * @param id the id of the player associated with the event.
     */

    public void update(String message, int id) {
        for(ClientHandler c : playerList){
            if(c.getIndex() == id) c.send(message);
        }
    }

    /**
     * Notifies the controller of a certain event.
     * @param message the message depicting the event.
     * @param player the id of the player associated with the event.
     */

    public void notifyController(String message, int player) {
        notify(message, player);
    }

}