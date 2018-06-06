package it.polimi.ingsw.client;

import it.polimi.ingsw.utils.Observer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientConnection extends Thread implements Observer {

    private ClientManager clientManager;
    private DataInputStream socketIn;
    private DataOutputStream socketOut;

    public ClientConnection(Socket socket, ClientManager clientManager) {

        try {
            socketIn = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socketOut = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.clientManager = clientManager;
        clientManager.register(this);
    }

    @Override
    public void run(){
        try {
            boolean done = false;

            //first message is "connection established, insert username"
            while (!done) {
                String mex = socketIn.readUTF();
                if(!mex.equals("0")) System.out.println(mex);
                if(mex.equals("Game ready")) done = true;
            }

            //login message
            clientManager.loginPrint();
            done = false;

            ///until the end of the game
            while (!done) {
                String s = socketIn.readUTF();
                clientManager.handleMove(s);
            }

        } catch (IOException e ) {
            System.out.println("Connection closed");
        }

        finally {
            try {
                socketIn.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                socketOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(Object message) {
        try {
            socketOut.writeUTF((String)message);
            socketOut.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void update(Object message, int id) {
        //empty body
    }

}