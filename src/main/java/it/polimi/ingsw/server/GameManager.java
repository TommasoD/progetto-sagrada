package it.polimi.ingsw.server;

import java.util.ArrayList;

public class Server extends Thread {
    protected ArrayList<ClientHandler> playerList = new ArrayList<ClientHandler>();

    public void run() {

        System.out.println("Run...");


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
}