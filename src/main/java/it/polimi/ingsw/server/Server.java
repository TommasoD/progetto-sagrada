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
    private GameRoom gameRoom;
    private Game model;
    private Controller controller;

    public Server() {

        reader.readNetworkSetup();
        port = reader.getPort();

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        gameManager = new GameManager();
        model = new Game();
        controller = new Controller(model);
        model.register(gameManager);
        gameManager.register(controller);
        gameRoom = new GameRoom();

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

        int socketId = 0;
        while(gameRoom.getSize()<4) {
            try {
                Socket socket = serverSocket.accept();
                socketId++;
                gameRoom.addSocketConnection(new SocketConnection(gameRoom, socket, socketId));
                System.out.println("Client " + socketId + " connected");

            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

        controller.newMatch(gameRoom.getSize());

        //create client handler
        for (int i = 0; i < gameRoom.getSize(); i ++) {
            gameManager.playerList.add(new ClientHandler(gameRoom.getConnections().get(i).getSocket(), gameManager, i));
            gameManager.playerList.get(i).start();
        }

/*
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
        }*/

    }

    public static void main(String[] args) {
        Server server= new Server();
        server.startServer();
    }

}
