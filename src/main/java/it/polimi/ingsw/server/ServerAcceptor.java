package it.polimi.ingsw.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import java.net.UnknownHostException;
import java.net.InetAddress;

public class ServerAcceptor {

    private final int port = 7777;

    ServerSocket serverSocket;
    Server server;

    public ServerAcceptor() {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        this.server = new Server();
        System.out.println("Server ready");
    }

    public void startServerAcceptor() {

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
        for (int i = 0; i < 4; i++) {
            try {
                Socket socket = serverSocket.accept();
                server.playerList.add(new ClientHandler(socket, server));
                System.out.println("Client " + (i+1) + " connected");
                server.playerList.get(i).start();

            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

        server.start();  ///run on another Thread

        while(1 == 1) {
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

    public static void main(String[] args) {
        ServerAcceptor acceptor = new ServerAcceptor();
        acceptor.startServerAcceptor();
    }

}
