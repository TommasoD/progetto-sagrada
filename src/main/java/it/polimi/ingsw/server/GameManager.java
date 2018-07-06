package it.polimi.ingsw.server;

import it.polimi.ingsw.utils.Observable;
import it.polimi.ingsw.utils.Observer;
import java.util.ArrayList;

/**
 *
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
     *
     * @param message the message depicting the event.
     */

    public void update(String message) {
        for(ClientHandler c : playerList){
            c.send(message);
        }
    }

    /**
     *
     * @param message the message depicting the event.
     * @param id the id of the player associated with the event.
     */

    public void update(String message, int id) {
        for(ClientHandler c : playerList){
            if(c.getIndex() == id) c.send(message);
        }
    }

    /**
     *
     * @param message
     * @param player
     */

    public void notifyController(String message, int player) {
        notify(message, player);
    }

}