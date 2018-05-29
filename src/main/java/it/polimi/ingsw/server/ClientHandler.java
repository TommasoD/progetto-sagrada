package it.polimi.ingsw.server;
import it.polimi.ingsw.messages.LoginMessage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class ClientHandler extends  Thread {
    private Socket socket;
    private String username;
    private int index;
    private DataInputStream input;
    private DataOutputStream output;
    private GameManager gameManager;
    protected boolean threadSuspended = true;
    protected String move;


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
        String mex;

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
                    gameManager.nClient++;
                    username = line;
                    output.writeUTF("Welcome " + username);
                    output.flush();
                }
            }

            this.send(gameManager.getController().showWindows());
            mex = input.readUTF();
            gameManager.getController().setWindowPattern(index, mex);

            /////finchè non finisce la partita
            while(1 == 1) {

                //while (threadSuspended) {}
                String s = gameManager.getController().handleMove(index, input.readUTF());
                System.out.println(s);
                output.writeUTF(s);
                output.flush();
                threadSuspended = true;

            }

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