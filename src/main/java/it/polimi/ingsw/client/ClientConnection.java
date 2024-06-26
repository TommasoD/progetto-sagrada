package it.polimi.ingsw.client;

import it.polimi.ingsw.utils.Observable;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Public class that manages the client connection.
 * Waits to read a message throughout the game,
 * calling the notify method for each message.
 */

public class ClientConnection extends Observable<String> implements Runnable {

    private DataInputStream socketIn;
    private DataOutputStream socketOut;
    private static final String GAME_READY = "\nGame ready\n" ;
    private static final String CONNECTION_CLOSED = "\nConnection closed";

    /**
     * Creates an input socket and an output socket for data.
     * @param socket the connection socket.
     */

    public ClientConnection(Socket socket) {

        try {
            socketIn = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.out.println("IOException on DataInputStream");
        }
        try {
            socketOut = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println("IOException on DataOutputStream");
        }

    }

    /**
     * Starts ClientConnection and waits to read
     * an input socket until the end of the game.
     */

    public void run(){
        try {
            boolean done = false;

            while (!done) {
                String mex = socketIn.readUTF();
                System.out.print(mex);
                if(mex.equals(GAME_READY)) done = true;
            }

            done = false;

            while (!done) {                         //until the end of the game
                String s = socketIn.readUTF();
                notify(s);
            }

        } catch (IOException e ) {
            System.out.println(CONNECTION_CLOSED);
            System.exit(1);
        }

        finally {
            try {
                socketIn.close();
            } catch (IOException e) {
                System.out.println("Exception when closing input");
            }
            try {
                socketOut.close();
            } catch (IOException e) {
                System.out.println("Exception when closing output");
            }
        }
    }

    /**
     * Writes a message on output socket.
     * @param message the message sent.
     */

    public void send(String message) {
        try {
            socketOut.writeUTF(message);
            socketOut.flush();
        }
        catch (IOException e) {
            System.out.println("Exception while sending message through the network");
        }
    }

}