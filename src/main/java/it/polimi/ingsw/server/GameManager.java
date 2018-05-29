package it.polimi.ingsw.server;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.utils.Observer;

import java.util.ArrayList;

public class GameManager extends Thread implements Observer {
    protected ArrayList<ClientHandler> playerList;
    protected ArrayList<String> playerNames;
    protected int nClient = 0;
    private Controller controller;

    public GameManager() {
        playerList = new ArrayList<ClientHandler>();
        playerNames = new ArrayList<String>();
    }

    public void run() {

        while(nClient != 4) {}

        for(int i = 0; i < playerList.size(); i++) {  ////add players
            controller.addPlayer(playerList.get(i).getUsername());
        }

        controller.newMatch();

        //while(!controller.isGameEnded()) {
        //    playerList.get(controller.whoIsNext()).threadSuspended = false;
        //    while(playerList.get(controller.whoIsNext()).threadSuspended == false);
        //}

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