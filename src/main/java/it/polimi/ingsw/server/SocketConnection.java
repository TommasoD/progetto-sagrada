package it.polimi.ingsw.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketConnection extends Thread {

    private Socket socket;
    private int id;

    private DataInputStream input;
    private DataOutputStream output;
    private GameRoom gameRoom;

    public SocketConnection (GameRoom gameRoom, Socket socket, int index) {
        this.socket = socket;
        this.id = index;
        this.gameRoom = gameRoom;
        try {
            input = new DataInputStream(socket.getInputStream());
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
            output.writeUTF("Waiting for other players...");   ////////DA RIFARE!!!
            output.flush();
            while(!gameRoom.getGameReady()) {
                input.readUTF();
            }
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
