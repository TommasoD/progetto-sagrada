package it.polimi.ingsw.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class ClientHandler extends Thread {
    private Socket socket;
    private int index;
    private DataInputStream input;
    private DataOutputStream output;
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
            output.writeUTF("Game ready");
            output.flush();
            while(!done) {
                String s = input.readUTF();
                gameManager.notifyController(s, index);
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

    public int getIndex() {
        return index;
    }
}