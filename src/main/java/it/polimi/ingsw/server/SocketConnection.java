package it.polimi.ingsw.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketConnection extends Thread {

    private Socket socket;
    private int id;

    private DataOutputStream output;
    private GameRoom gameRoom;

    public SocketConnection (GameRoom gameRoom, Socket socket, int index) {
        this.socket = socket;
        this.id = index;
        this.gameRoom = gameRoom;
        try {
            output = new DataOutputStream(socket.getOutputStream());
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public int getMyId () {
        return this.id;
    }

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
                    e.printStackTrace();
                }
            }
            /////gameReady -> true
        }
        catch (IOException e) {
            gameRoom.removeSocketConnection(id);
        }
    }

    public DataOutputStream getOutput() {
        return output;
    }

    public Socket getSocket () {
        return this.socket;
    }

}
