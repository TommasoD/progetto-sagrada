package it.polimi.ingsw.server;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.Game;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import java.net.UnknownHostException;
import java.net.InetAddress;
import java.util.ArrayList;

public class Server {

    private final int port = 7777;

    private ServerSocket serverSocket;
    private GameManager gameManager;
    private Game model;
    private Controller controller;
    private ArrayList<String> disconnectedPlayers;
    private int nClient = 0;

    public Server() {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        disconnectedPlayers = new ArrayList<String>();
        this.gameManager = new GameManager();
        System.out.println("GameManager ready");
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


        //when the second player is connected the gameManager must wait N seconds before starting the game
        for (int i = 0; i < 4; i++) {
            try {
                Socket socket = serverSocket.accept();
                gameManager.playerList.add(new ClientHandler(socket, gameManager));
                nClient++;
                System.out.println("Client " + (i+1) + " connected");
                gameManager.playerList.get(i).start();

            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

        model = new Game();
        controller = new Controller(model);
        model.register(gameManager);
        gameManager.setController(controller);

        System.out.println(nClient);
        while(gameManager.playerNames.size() < nClient){
        }

        gameManager.start();  ///run on another Thread

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
        Server acceptor = new Server();
        acceptor.startServer();
    }

}
