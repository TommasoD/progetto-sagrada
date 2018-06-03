package it.polimi.ingsw.server;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.parsers.NetworkParser;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import java.net.UnknownHostException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Random;

public class Server {

    private int port;
    NetworkParser reader = new NetworkParser();

    private ServerSocket serverSocket;
    private GameManager gameManager;
    private Game model;
    private Controller controller;
    private Countdown countdown;

    public Server() {

        //possibly try-catch. SAXException
        reader.readNetworkSetup();
        port = reader.getPort();

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }


        countdown = new Countdown(this);
        gameManager = new GameManager();
        model = new Game();
        controller = new Controller(model);
        model.register(gameManager);
        gameManager.register(controller);
        System.out.println("Server ready");
    }

    public void startServer() {

        try {
            System.out.println("My ip address: " + InetAddress.getLocalHost());
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
            System.exit(1);
        }

        //when the second player is connected the gameManager must wait N seconds before starting the game
        //////verified time
//        long startTime = System.currentTimeMillis();
        int i = 0;
        int num = 0;  ////usare la dimensione di un array di giocatori

        while(i < 4) {
            try {
                Socket socket = serverSocket.accept();
                num++;
                gameManager.playerList.add(new ClientHandler(socket, gameManager, i));
                System.out.println("Client " + (i + 1) + " connected");
                gameManager.playerList.get(i).start();
                if(num == 2) countdown.start();
                if(num > 2) countdown.reset();
                i++;

            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

        while(1 == 1) {
            try {
                Socket socket = serverSocket.accept();
                gameManager.playerList.add(new ClientHandler(socket, gameManager, i));
                System.out.println("Client " + (i + 1) + " connected");
                gameManager.playerList.get(i).start();
                i++;
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

    }

    public static void main(String[] args) {
        Server server= new Server();
        server.startServer();
    }

}
