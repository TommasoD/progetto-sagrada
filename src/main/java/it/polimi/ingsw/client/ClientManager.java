package it.polimi.ingsw.client;

import it.polimi.ingsw.messages.client.*;
import it.polimi.ingsw.messages.controller.ChooseWindowMessage;
import it.polimi.ingsw.messages.controller.LoginMessage;
import it.polimi.ingsw.messages.controller.PassMessage;
import it.polimi.ingsw.messages.controller.SetDieMessage;
import it.polimi.ingsw.parsers.GsonParser;
import it.polimi.ingsw.parsers.NetworkParser;
import it.polimi.ingsw.utils.Observable;
import it.polimi.ingsw.view.View;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


public class ClientManager extends Observable{

    private Scanner stdin;
    private GsonParser parser;
    private View view;
    private Socket socket;
    private ClientConnection network;

    /*
        constructor
     */

    private ClientManager(){
        stdin = new Scanner(System.in);
        parser = new GsonParser();
        view = new View();
    }

    private void startClient(){
        NetworkParser reader = new NetworkParser();
        reader.readNetworkSetup();
        String ip = reader.getIp();
        int port = reader.getPort();

        try {
            socket = new Socket(ip, port);
        } catch (IOException e) {
            try {
                socket.close();
            } catch (Exception e1) {
                System.out.println("Error in socket\nConnection closed");
            }
            System.exit(1);
        }

        network = new ClientConnection(socket, this);
        network.start();
    }

    /*
        called right after the start of the match
     */

    public void loginPrint() {

        this.cleanInputStream();

        view.print("Insert username: ");
        String inputLine = stdin.nextLine();
        LoginMessage gson = new LoginMessage(inputLine);
        notify(gson.serialize());
    }

    public void handleMove(String gson){
        ClientMessage message = parser.parseClient((String) gson);
        message.accept(this);
    }

    public void visit(NewTurnMessage message){
        view.print("It's your turn.");
        cleanInputStream();
        playerAction();
    }

    public void visit(ErrorMessage message) {
        if (message.getType() == 0) {
            view.print("Not a player. A match is being played, try again later.");
            System.exit(1);
        }
        if (message.getType() == 1) {
            view.print("Username already used. Insert a new one:");
            String inputLine = stdin.nextLine();
            LoginMessage mex = new LoginMessage(inputLine);
            notify(mex.serialize());
        }
        if (message.getType() == 2) {
            view.print("Invalid move.");
            playerAction();
        }
    }

    public void visit(ShowWindowsMessage message){
        view.printWindows(message);
        String line = stdin.nextLine();

        //controlla la validità della finestra scelta

        view.print("Wait for the start of the match.");
        ChooseWindowMessage m = new ChooseWindowMessage(line);
        notify(m.serialize());
    }

    public void visit(UpdateModelMessage message){
        view.printUpdate(message);
    }

    public void visit(OkMessage message){
        view.print("Action successful!");
    }

    public void visit(EndTurnMessage message){
        view.printEndOfTurn();
    }

    /*
        clean System.in
     */

    private void cleanInputStream() {
        try {
            int length = System.in.available();
            while(length > 0) {
                length -= stdin.nextLine().length() + 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
        handle the player's turn
     */

    private void playerAction(){
        view.print("Make a move ('help' to see more)");
        String move = stdin.nextLine();
        if(move.equals("place")){
            view.print("Choose a die and insert its position: ");
            int die = Integer.parseInt(stdin.nextLine());
            view.print("Choose the column (from left to right): ");
            int x = Integer.parseInt(stdin.nextLine());
            view.print("Choose the row (from top to bottom): ");
            int y = Integer.parseInt(stdin.nextLine());
            notify(new SetDieMessage(x, y, die).serialize());
        }
        else if(move.equals("end")){
            notify(new PassMessage().serialize());
        }
        else if(move.equals("help")){
            view.print("'place' -> place a die on your Window.\n" +
                    "'end' -> end your turn.");
            playerAction();
        }
        else{
            view.print("Unsupported move.");
            playerAction();
        }
    }

    public static void main(String[] args) {
        ClientManager client = new ClientManager();
        client.startClient();
    }
}
