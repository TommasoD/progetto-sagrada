package it.polimi.ingsw.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import java.net.UnknownHostException;
import java.net.InetAddress;

public class Server {
    private final int port = 7777;
    private int i;
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
        for (i = 0; i < 4; i++) {
            try {
                Socket socket = serverSocket.accept();
                playerList.add(new ClientHandler(socket,this));
                System.out.println("Client " + (i+1) + " connected");
                playerList.get(i).start();

            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

        while(i == 4) {
            try {
                Socket socket = serverSocket.accept();
                DataOutputStream output = new DataOutputStream(socket.getOutputStream());
                output.writeUTF("Too many players");
                output.flush();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

    }

    public boolean nameUsed(String string) {
        for (int i = 0; i < playerList.size()-1; i++) {
            String s = playerList.get(i).getUsername();
            if (s != null){
                if(s.equals(string))return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Server echoServer = new Server();
        echoServer.startServer();
    }
}