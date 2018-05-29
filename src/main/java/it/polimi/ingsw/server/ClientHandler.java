package it.polimi.ingsw.server;
import it.polimi.ingsw.messages.LoginMessage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler extends  Thread {
    private Socket socket;
    private String username;
    private DataInputStream input;
    private DataOutputStream output;
    private GameManager gameManager;
    protected boolean threadSuspended = true;
    protected String move;


    public ClientHandler(Socket socket, GameManager gameManager) {
        this.socket = socket;
        try {
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        this.gameManager = gameManager;
    }

    public void run() {
        boolean done = false;

        try {
            //input username
            //while need to be synchronized
            output.writeUTF("Connection established");
            output.flush();
            LoginMessage message = new LoginMessage();
            while (!done) {
                message = message.deserialize(input.readUTF());
                String line = message.getUsername();
                if (gameManager.nameUsed(line)) {
                    output.writeUTF("Username " + line + " already used");
                    output.flush();
                } else {
                    done = true;
                    gameManager.playerNames.add(line);
                    username = line;
                    output.writeUTF("Welcome " + username);
                    output.flush();
                }
            }

            //String mex = input.readUTF();

            ///this.send insert move
            ///move = input.readUTF()
            ///


        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }


        try {
            socket.close();
        }
        catch (IOException e ) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void send(String line) {
        try {
            output.writeUTF(line);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public String getUsername() {
        return this.username;
    }

}