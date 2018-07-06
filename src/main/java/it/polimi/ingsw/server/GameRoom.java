package it.polimi.ingsw.server;

import java.io.*;
import java.util.ArrayList;

/**
 * Public class that handles the connections and
 * disconnections of the clients before the game starts.
 * When a client has successfully connected, he is placed in a lobby.
 * When a client has disconnected, he is remove from the lobby.
 * When the number of players is sufficient, a timer is started.
 * In case there is only one player connected the timer is stopped.
 * At the end of the timer, a sufficient number was then reached
 * to play and the game starts.
 */

public class GameRoom {

    private ArrayList<SocketConnection> connections = new ArrayList<SocketConnection>();
    private Countdown timer = new Countdown();
    private boolean gameReady;
    private static final String NEW_PLAYER_CONNECTED = "\nNew player connected\n";
    private static final String PLAYER_DISCONNECTED = "\nA player disconnected\n";

    /**
     * Class Constructor.
     * Starts the timer and set to false the gameReady
     * variable: the game has not started yet.
     */

    public GameRoom() {
        super();
        timer.start();
        gameReady = false;
    }

    /**
     *
     * @return the timer.
     */

    public Countdown getTimer() {
        return timer;
    }

    /**
     *
     * @return gameReady.
     */

    public boolean getGameReady() {
        return gameReady;
    }

    /**
     *
     * @return the number of active connection.
     */

    public int getSize() {
        return connections.size();
    }

    /**
     * Adds a socket to the active connection list.
     * If the number of connected clients is greater than
     * or equal to 2, starts the countdown.
     * If the number of connected clients is equal to 4,
     * starts the game.
     * @param playerSocket the player socket to add
     *                     to the list.
     */

    public synchronized void addSocketConnection(SocketConnection playerSocket) {
        for (SocketConnection socketConnection : connections) {
            try {
                socketConnection.getOutput().writeUTF(NEW_PLAYER_CONNECTED);
            }
            catch (IOException e) {
                System.out.println("Exception while sending a message from Game Room");
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

    /**
     * Remove from the list of active connections
     * the socket of the clients that has disconnected
     * before the start of the game.
     * If the number of connected clients is greater than
     * or equal to 2, starts the countdown.
     * @param id the id of the socket to remove.
     */

    public synchronized void removeSocketConnection(int id) {
        for (int i = 0; i < connections.size(); i++)
            if (connections.get(i).getMyId() == id )
        connections.remove(connections.get(i));

        for (SocketConnection socketConnection : connections) {
            try {
                socketConnection.getOutput().writeUTF(PLAYER_DISCONNECTED);
            }
            catch (IOException e) {
                System.out.println("Exception while sending a message from Game Room");
            }
        }
        if (connections.size() >= 2) timer.reset();
         else timer.resetAndStop();
    }

    /**
     *
     * @return the list active connections
     */

    public ArrayList<SocketConnection> getConnections() {
        return connections;
    }
}

