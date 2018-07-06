package it.polimi.ingsw.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Public class that allows to manage the connection
 * and the disconnection of a client before the game.
 */

public class SocketConnection extends Thread {

    private Socket socket;
    private int id;

    private DataOutputStream output;
    private GameRoom gameRoom;

    /**
     * Class constructor.
     * @param gameRoom lobby to which to add the socket.
     * @param socket this socket.
     * @param index the socket index.
     */

    public SocketConnection (GameRoom gameRoom, Socket socket, int index) {
        this.socket = socket;
        this.id = index;
        this.gameRoom = gameRoom;
        try {
            output = new DataOutputStream(socket.getOutputStream());
        }
        catch (IOException e) {
            System.out.println("Exception in creating Data Output Stream");
            System.exit(1);
        }
    }

    /**
     *
     * @return the socket index.
     */

    public int getMyId () {
        return this.id;
    }

    /**
     * Starts the SocketConnection.
     * Allows the connected client to see the timer
     * value at that time.
     * If the client disconnects, it calls the method that remove
     * the socket from the list of active connections.
     */

    public void run() {

        try {
            output.writeUTF("Waiting for other players\n");
            output.flush();
            String s;
            while(!gameRoom.getGameReady() && gameRoom.getTimer().read() < gameRoom.getTimer().getMaxTime()) {
                output.writeUTF("\r" + gameRoom.getTimer().toString());
                output.flush();
                try {
                    Thread.sleep(100);
                } catch(InterruptedException e) {
                    System.out.println("Exception when sending a message from SocketConnection");
                    Thread.currentThread().interrupt();
                }
            }
        }
        catch (IOException e) {
            gameRoom.removeSocketConnection(id);
        }
    }

    /**
     *
     * @return the DataOutputStream.
     */

    public DataOutputStream getOutput() {
        return output;
    }

    /**
     *
     * @return the socket.
     */

    public Socket getSocket () {
        return this.socket;
    }

}
