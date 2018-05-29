package it.polimi.ingsw.server;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.utils.Observer;

import java.util.ArrayList;

public class GameManager extends Thread implements Observer {
    protected ArrayList<ClientHandler> playerList;
    protected ArrayList<String> playerNames;
    private Controller controller;

    public GameManager() {
        playerList = new ArrayList<ClientHandler>();
        playerNames = new ArrayList<String>();
    }

    public void run() {

        for(int i = 0; i < playerList.size(); i++) {  ////add players
            controller.addPlayer(playerList.get(i).getUsername());
        }

        controller.newMatch();   /////create a new match

        for(int i = 0; i < playerList.size(); i++) {
            playerList.get(i).send(controller.showWindows());
        }

    }



    public void update(Object message) {

    }

    public synchronized boolean nameUsed(String string) {
        for (int i = 0; i < playerList.size()-1; i++) {
            String s = playerList.get(i).getUsername();
            if (s != null){
                if(s.equals(string))return true;
            }
        }
        return false;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public Controller getController() {
        return controller;
    }

    public String chooseWindow() {
        return controller.showWindows();
    }

}