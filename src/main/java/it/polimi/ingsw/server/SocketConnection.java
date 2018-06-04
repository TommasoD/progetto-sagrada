package it.polimi.ingsw.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class SocketConnection extends Thread {

    private Socket socket;
    private int id;

    private Scanner stdin = new Scanner(System.in);
    private DataOutputStream output;
    private GameRoom gameRoom;

    public SocketConnection (GameRoom gameRoom, Socket socket, int index) {
        this.socket = socket;
        this.id = index;
        this.gameRoom = gameRoom;
        try {
            stdin = new Scanner(System.in);
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
                //stdin.nextLine();
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
