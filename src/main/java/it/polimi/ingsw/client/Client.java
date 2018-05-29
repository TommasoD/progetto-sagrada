package it.polimi.ingsw.client;

import it.polimi.ingsw.messages.LoginMessage;
import it.polimi.ingsw.model.NetworkParser;
import it.polimi.ingsw.utils.Observer;
import it.polimi.ingsw.view.View;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


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
        Scanner stdin = new Scanner(System.in);

        try {

            String mex = socketIn.readUTF();
            System.out.println(mex);
            if(mex.equals("Too many players")) throw new IOException();

            boolean done = false;
            LoginMessage message;

            //login
            while (!done) {
                System.out.println("Insert username: ");
                String inputLine = stdin.nextLine();
                message = new LoginMessage(inputLine);
                socketOut.writeUTF(message.serialize());
                socketOut.flush();

                String socketLine = socketIn.readUTF();
                System.out.println(socketLine);
                if (socketLine.equals("Welcome " + inputLine)) done = true;

            }

            String s = socketIn.readUTF();
            view.print(s);

            ///fino a fine partita
            while(1 == 1) {
                view.move();
                System.out.println(socketIn.readUTF());
            }




        } catch (IOException e) {
            System.out.println("Connection closed");
        }

        finally {
            socket.close();
            stdin.close();
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
}