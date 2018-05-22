package it.polimi.ingsw.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import java.net.UnknownHostException;
import java.net.InetAddress;

public class Server {
    private final int port = 7777;
    protected ArrayList<ClientHandler> playerList = new ArrayList<ClientHandler>();
    ServerSocket serverSocket;

    public Server() {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("Server ready");
    }

    public void startServer() {

        InetAddress ip;
        try {
            ip = InetAddress.getLocalHost();
            System.out.println("Indirizzo ip: " + ip);
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
            System.exit(1);
        }


        //when the second player is connected the server must wait N seconds before starting the game
        for (int i = 0; i< 4; i++) {
            try {
                Socket socket = serverSocket.accept();
                playerList.add(new ClientHandler(socket,this));
                playerList.get(i).start();

            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

    }

    public boolean nameUsed(String string) {
        for (int i = 0; i < playerList.size()-1; i++) {
            if (playerList.get(i).getUsername().equals(string)) return true;
        }
        return false;
    }



    public static void main(String[] args) {
        Server echoServer = new Server();
        echoServer.startServer();
    }
}