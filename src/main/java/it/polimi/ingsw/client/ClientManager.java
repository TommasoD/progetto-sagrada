package it.polimi.ingsw.client;

import com.google.gson.stream.JsonReader;
import it.polimi.ingsw.messages.client.*;
import it.polimi.ingsw.messages.controller.ChooseWindowMessage;
import it.polimi.ingsw.messages.controller.LoginMessage;
import it.polimi.ingsw.messages.controller.SetDieMessage;
import it.polimi.ingsw.parsers.GsonParser;
import it.polimi.ingsw.utils.Observable;
import it.polimi.ingsw.server.TooManyPlayersException;
import it.polimi.ingsw.view.View;

import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;


public class ClientManager extends Observable{

    private Scanner stdin;
    private GsonParser parser;
    private View view;

    /*
        constructor
     */

    public ClientManager(){
        stdin = new Scanner(System.in);
        parser = new GsonParser();
        view = new View();
    }

    /*
        called right after the start of the match
     */

    public void loginPrint() {
        view.print("Insert username: ");
        String inputLine = stdin.nextLine();
        LoginMessage gson = new LoginMessage(inputLine);
        notify(gson.serialize());
    }

    public void print(String gson){

        ClientMessage message = parser.parseClient((String) gson);
        message.accept(this);

        /*
        if(id.equals("ok")){
            System.out.println("Welcome to the game.");
        }
        if(id.equals("error")) {
            ErrorMessage gson = new ErrorMessage();
            gson = gson.deserialize(message);
            if (gson.getType() == 1) {
                System.out.println("Username already used. Insert a new one:");
                String inputLine = stdin.nextLine();
                LoginMessage mex = new LoginMessage(inputLine);
                notify(mex.serialize());
            }
            if (gson.getType() == 0) {
                System.out.println("Not a player. A match is being played, try again later.");
                throw new TooManyPlayersException();
            }

            //type = 2 is invalidMove
        }

        if(id.equals("windows")) {
            ShowWindowsMessage gson = new ShowWindowsMessage();
            gson = gson.deserialize(message);
            System.out.println("\nChoose a Window\n");
            System.out.println(gson.getW1());
            System.out.println(gson.getW2());
            System.out.println(gson.getW3());
            System.out.println(gson.getW4());
            System.out.println("\nInsert the name of desired Window:");
            String line = stdin.nextLine();
            /////controlla la validità della finestra scelta

            ChooseWindowMessage m = new ChooseWindowMessage(line);
            notify(m.serialize());
        }

        if (id.equals("place")) {
            System.out.println("Choose your move\n1. Place a die\n2. Use Tool Card");
            String line = stdin.nextLine();
            if(line.equals("1")) {
                System.out.print("Choose a die: "); ////stampo draft
                int die = stdin.nextInt();
                System.out.print("Choose a x_place: ");  ////stampa la window
                int x = stdin.nextInt();
                System.out.print("Choose a y_place: ");  ////stampa la window
                int y = stdin.nextInt();
                notify(new SetDieMessage(x, y, die).serialize());
            } else if(line.equals("2")) {
                *//*System.out.println("Choose a Tool Card: ");
                int toolCardId = stdin.nextInt();
                notify(toolCardId);*//*
            }
        }*/

    }

    public void visit(NewTurnMessage message){
        view.print("Make a move: \n1. Place a die\n2. Use a tool card");
        int move = stdin.nextInt();
        if(move == 1){
            view.print("Choose a die and insert its position: ");
            int die = stdin.nextInt();
            System.out.print("Choose the column (from left to right): ");
            int x = stdin.nextInt();
            System.out.print("Choose the row (from top to bottom): ");
            int y = stdin.nextInt();
            notify(new SetDieMessage(x, y, die).serialize());
        }
        else view.print("Invalid move");
    }

    public void visit(ErrorMessage message) {
        if (message.getType() == 0) {
            view.print("Not a player. A match is being played, try again later.");
            //throw new TooManyPlayersException();
            //System.exit(1) ?
        }
        if (message.getType() == 1) {
            view.print("Username already used. Insert a new one:");
            String inputLine = stdin.nextLine();
            LoginMessage mex = new LoginMessage(inputLine);
            notify(mex.serialize());
        }
    }

    public void visit(ShowWindowsMessage message){
        view.printWindows(message);
        String line = stdin.nextLine();

        //controlla la validità della finestra scelta

        view.print("Wait for the start of the match");
        ChooseWindowMessage m = new ChooseWindowMessage(line);
        notify(m.serialize());
    }

    public void visit(UpdateModelMessage message){
        view.printUpdate(message);
        view.print("Wait for your turn");
    }

    public void visit(OkMessage message){
        view.print("Action successful");
    }

}
