package it.polimi.ingsw.server;

import it.polimi.ingsw.utils.Observable;
import it.polimi.ingsw.utils.Observer;
import java.util.ArrayList;

public class GameManager extends Observable<String> implements Observer<String> {
    protected ArrayList<ClientHandler> playerList;

    public GameManager() {
        playerList = new ArrayList<ClientHandler>();
    }


    public void update(String message) {
        for(ClientHandler c : playerList){
            c.send(message);
        }
    }

    public void update(String message, int id) {
        for(ClientHandler c : playerList){
            if(c.getIndex() == id) c.send(message);
        }
    }

    public void notifyController(String message, int player) {
        notify(message, player);
    }

}