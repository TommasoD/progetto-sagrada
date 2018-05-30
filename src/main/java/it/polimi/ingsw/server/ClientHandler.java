package it.polimi.ingsw.server;
import it.polimi.ingsw.messages.LoginMessage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class ClientHandler extends Thread {
    private Socket socket;
    private int index;
    protected DataInputStream input;
    protected DataOutputStream output;
    private GameManager gameManager;


    public ClientHandler(Socket socket, GameManager gameManager, int index) {
        this.socket = socket;
        this.index = index;
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
            output.writeUTF("Connection established");
            output.flush();
            while(!done) {
                //gameManager.notify(input.readUTF(), index);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            socket.close();
        } catch (IOException e) {
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

}