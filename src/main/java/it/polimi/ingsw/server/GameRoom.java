package it.polimi.ingsw.server;

import java.io.*;
import java.util.ArrayList;


public class GameRoom {

    private ArrayList<SocketConnection> connections = new ArrayList<SocketConnection>();
    private Countdown timer = new Countdown();
    private boolean gameReady;

    public GameRoom() {
        super();
        timer.start();
        gameReady = false;
    }

    public boolean getGameReady() {
        return gameReady;
    }

    public int getSize() {
        return connections.size();
    }


    public synchronized void addSocketConnection(SocketConnection playerSocket) {
        for (SocketConnection socketConnection : connections) {
            try {
                socketConnection.getOutput().writeUTF("New player connected");
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        connections.add(playerSocket);
        playerSocket.start();

        if (connections.size() >= 2) timer.reset();
        if (connections.size() == 4) {
            timer.setDone();
            gameReady = true;
        }

    }

    public synchronized void removeSocketConnection(int id) {
        for (int i = 0; i < connections.size(); i++)
            if (connections.get(i).getMyId() == id )
        connections.remove(connections.get(i));

        for (SocketConnection socketConnection : connections) {
            try {
                socketConnection.getOutput().writeUTF("A player disconnected");
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (connections.size() >= 2) timer.reset();
         else timer.resetAndStop();
    }

    public ArrayList<SocketConnection> getConnections() {
        return connections;
    }
}

