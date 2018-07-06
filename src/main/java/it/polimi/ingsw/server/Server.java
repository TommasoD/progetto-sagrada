package it.polimi.ingsw.server;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.parsers.SetupParser;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.Enumeration;

/**
 * Represents the server, so it accepts or declines new client connections.
 * Contains the port number and references to gameManager, gameRoom, Game and Controller.
 * These references are essential to create a new match.
 */
public class Server {

    private int port;
    private SetupParser reader = new SetupParser();
    private ServerSocket serverSocket;
    private GameManager gameManager;
    private GameRoom gameRoom;
    private Game model;
    private Controller controller;
    private static final String NEW_GAME = "A new game is being played. Try connecting later.";

    /**
     * Class constructor.
     */

    public Server() {

        reader.readSetup();
        port = reader.getPort();

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("Exception in creating the server Socket");
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

    /**
     * Accepts or declines new client connections.
     * 1. In the first while, this class accepts up to four new client connections.
     * 2. Creates a new match with the relative controller method.
     * 3. Creates a new client handler for each client.
     * 4. Calls the controller method newLoginRequest.
     * 5. In the second while, this class declines new client connections.
     */

    public void startServer() {

        this.printIPAddress();

        int socketId = 0;
        while(gameRoom.getSize() < 4) {
            try {
                Socket socket = serverSocket.accept();
                if(gameRoom.getTimer().read() >= gameRoom.getTimer().getMaxTime()) {
                    socket.close();
                    break;
                }
                socketId++;
                gameRoom.addSocketConnection(new SocketConnection(gameRoom, socket, socketId));
                if(socketId <= 2) System.out.println("Client " + socketId + " connected");
                else System.out.println("\n" + "Client " + socketId + " connected");

            } catch (IOException e) {
                System.out.println("Client " + (socketId) + ": connection failed");
            }
        }

        controller.newMatch(gameRoom.getSize());


        int i = 0;
        for (SocketConnection s : gameRoom.getConnections()) {
            gameManager.playerList.add(new ClientHandler(s.getSocket(), gameManager, i));
            gameManager.playerList.get(i).start();
            i++;
        }

        controller.newLoginRequest();

        while(!model.isGameEnded()) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("Client " + (i + 1) + " connected");
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                out.writeUTF(NEW_GAME);
                out.flush();
                out.close();
                socket.close();
                i++;
            } catch (IOException e) {
                System.out.println("Client " + (i + 1) + ": connection failed");
            }
       }

    }

    /**
     * Prints IPv4 Address of this machine.
     */

    private void printIPAddress() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();
                // filters out 127.0.0.1 and inactive interfaces
                if (iface.isLoopback() || !iface.isUp())
                    continue;

                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while(addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();

                    if (addr.getHostAddress().contains(":")) continue;
                    System.out.println("My IP address: " + addr.getHostAddress());
                }
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates an instance of this class and calls the server method startServer.
     */

    public static void main(String[] args) {
        Server server = new Server();
        server.startServer();
    }

}
