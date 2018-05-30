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
/*
    public void login(int i) {
        try {
            boolean done = false;

            LoginMessage message = new LoginMessage();
            while (!done) {
                message = message.deserialize(playerList.get(i).input.readUTF());
                String line = message.getUsername();
                if (this.nameUsed(line)) {
                    playerList.get(i).output.writeUTF("Username " + line + " already used");
                    playerList.get(i).output.flush();
                } else {
                    done = true;
                    playerNames.add(line);
                    playerList.get(i).username = line;
                    controller.addPlayer(line);
                    playerList.get(i).output.writeUTF("Welcome " + line);
                    playerList.get(i).output.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }*/


}