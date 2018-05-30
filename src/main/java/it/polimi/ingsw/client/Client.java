package it.polimi.ingsw.client;

import it.polimi.ingsw.model.parsers.NetworkParser;
import it.polimi.ingsw.server.TooManyPlayersException;
import it.polimi.ingsw.utils.Observer;
import it.polimi.ingsw.view.View;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client implements Observer {
    private String ip;
    private int port;
    NetworkParser reader = new NetworkParser();
    private View view;
    private DataInputStream socketIn;
    private DataOutputStream socketOut;
    private Socket socket;

    public Client() {
        reader.readNetworkSetup();
        this.port = reader.getPort();
        this.ip = reader.getIp();

        view = new View();
        view.register(this);
    }

    public static void main(String[] args) {
        Client client = new Client();
        try {
            client.startClient();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void startClient() throws IOException {
        socket = new Socket(ip, port);
        socketIn = new DataInputStream(socket.getInputStream());
        socketOut = new DataOutputStream(socket.getOutputStream());

        try {

            //first message is "connection established, insert username"
            String mex = socketIn.readUTF();
            view.firstPrint(mex);

            boolean done = false;

            ///until the end of the game
            while (!done) {
                String s = socketIn.readUTF();
                try {
                    view.print(s);
                }
                catch (TooManyPlayersException e) {
                    done = true;
                }
            }

        } catch (IOException e ) {
            System.out.println("Connection closed");
        }

        finally {
            socket.close();
            socketIn.close();
            socketOut.close();
        }
    }

    public void update(Object message) {
        try {
            socketOut.writeUTF((String) message);
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