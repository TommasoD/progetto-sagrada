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
    private Server server;
    protected boolean threadSuspended = true;


    public ClientHandler(Socket socket, Server server) {
        this.socket = socket;
        try {
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        this.server = server;
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
                if (server.nameUsed(line)) {
                    output.writeUTF("Username " + line + " already used");
                    output.flush();
                } else {
                    done = true;
                    username = line;
                    output.writeUTF("Welcome " + username);
                    output.flush();
                }
            }

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


    public String getUsername() {
        return this.username;
    }

}