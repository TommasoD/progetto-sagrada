package it.polimi.ingsw.server;

import it.polimi.ingsw.utils.Observable;
import it.polimi.ingsw.utils.Observer;
import java.util.ArrayList;

public class GameManager extends Observable implements Observer {
    protected ArrayList<ClientHandler> playerList;

    public GameManager() {
        playerList = new ArrayList<ClientHandler>();
    }


    public void update(Object message) {
        for(ClientHandler c : playerList){
            c.send((String)message);
        }
    }

    public void update(Object message, int id) {
        for(ClientHandler c : playerList){
            if(c.getIndex() == id) c.send((String)message);
        }
    }

    public void notifyController(String message, int player) {
        notify(message, player);
    }

}