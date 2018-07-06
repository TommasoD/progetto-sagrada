package it.polimi.ingsw.server;

import it.polimi.ingsw.messages.controller.LogoutMessage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Represents the clientHandler, so it receives and sends message
 * from and to a client. A reference to GameManager is essential
 * to notify the input string from a client to the controller.
 */
public class ClientHandler extends Thread {
    private Socket socket;
    private int index;
    private DataInputStream input;
    private DataOutputStream output;
    private GameManager gameManager;

    /**
     * Constructor specifying socket, gameManager and index of the player.
     * @param socket a socket.
     * @param gameManager an existing GameManager.
     * @param index index of the player.
     */

    public ClientHandler(Socket socket, GameManager gameManager, int index) {
        this.socket = socket;
        this.index = index;
        try {
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
        }
        catch (IOException e) {
            System.out.println("Exception when creating Data Stream");
            System.exit(1);
        }
        this.gameManager = gameManager;
    }

    /**
     * Firstly send to a client the string "\nGame ready\n",
     * then wait an input string from the same client.
     * When this class receives the input string,
     * notifies it to the controller
     */

    public void run() {

        boolean done = false;

        try {
            output.writeUTF("\nGame ready\n");
            output.flush();
            while(!done) {
                String s = input.readUTF();
                gameManager.notifyController(s, index);
            }
        }
        catch (IOException e) {
            //send mex to controller to set offline the player
            gameManager.notifyController(new LogoutMessage().serialize(), index);
            //delete client handler from game manager
            System.out.println("player " + index + " disconnected");
        }

        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("Exception when closing the socket");
            System.exit(1);
        }

    }

    /**
     * Sends to a client the parameter line.
     * @param line a string.
     */

    public void send(String line) {
        try {
            output.writeUTF(line);
            output.flush();
        } catch (IOException e) {
            System.out.println("client " + index + " still disconnected");
        }
    }

    /**
     *
     * @return index of the player.
     */

    public int getIndex() {
        return index;
    }
}