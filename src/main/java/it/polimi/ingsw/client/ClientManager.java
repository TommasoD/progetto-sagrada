package it.polimi.ingsw.client;

import it.polimi.ingsw.messages.client.*;
import it.polimi.ingsw.messages.controller.*;
import it.polimi.ingsw.model.ToolCard;
import it.polimi.ingsw.parsers.GsonParser;
import it.polimi.ingsw.parsers.NetworkParser;
import it.polimi.ingsw.utils.Observer;
import it.polimi.ingsw.view.View;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;


public class ClientManager implements Observer<String> {

    private Scanner stdin;
    private GsonParser parser;
    private View view;
    private Socket socket;
    private ClientConnection network;
    private boolean clientTurn;
    private boolean gameEnded;
    private int stage;              //0 -> before match, 1 -> login, 2 -> choose window, 3 -> client ready
    private  ValidateInput validateInput;
    /*
        constructor
     */

    private ClientManager(){
        stdin = new Scanner(System.in);
        parser = new GsonParser();
        view = new View();
        clientTurn = false;
        gameEnded = false;
        stage = 0;
        validateInput = new ValidateInput();
    }

    private void startClient(){
        NetworkParser reader = new NetworkParser();
        reader.readNetworkSetup();
        int port = reader.getPort();


        System.out.print("Insert a valid IP address: ");
        String ip = stdin.nextLine();

        socket = new Socket();
        try{
            System.out.print("Connecting...\n");
            socket.connect(new InetSocketAddress(ip, port), 5000);
        } catch (Exception e) {
            System.out.println("IP address is not valid\nA default IP address is used");
            try {
                socket.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            try {
                ip = reader.getIp();
                socket = new Socket(ip, port);
            } catch (IOException e2) {
                try {
                    socket.close();
                } catch (Exception e1) {
                    System.out.println("Error in socket\nConnection closed");
                }
                System.exit(1);
            }

        }

        network = new ClientConnection(socket);
        network.register(this);
        new Thread(network).start();

        while(!gameEnded){
            String request = stdin.nextLine();
            handleRequest(request);
        }
    }

    /*
        handles the player's requests
     */

    private void handleRequest(String request){
        if(stage == 0){
            view.printWaitForTheStart();
            return;
        }
        if(stage == 1){
            if(request.equals("login")){
                LoginMessage gson = new LoginMessage(view.printLogin());
                network.send(gson.serialize());
                return;
            }
            view.printDigit(1); //this print: You have to login first. Digit 'login'.
            return;
        }
        if(stage == 2){
            if(request.equals("window")){
                String line = view.printInsertWindow();
                while(!validateInput.checkWindowName(view.getWindowsName(), line)) line = view.printInsertWindow();
                view.printWaitForTheStart();
                ChooseWindowMessage m = new ChooseWindowMessage(line);
                network.send(m.serialize());
                stage = 3;
                return;
            }

            view.printDigit(2); //this print: "You have to choose a window. Digit 'window' to do so.
            return;
        }
        if(!clientTurn){
            if(request.equals("reconnect")){

                    //metodo per gestire la riconnessione
                    //manca la classe messaggio per la riconnessione

                return;
            }
            view.print("Wait for your turn.");
            return;
        }
        playerAction(request);
    }

       /*
        handles the player's turn
     */

    private void playerAction(String move){
        if(move.equals("place")){

            int die = view.printDieChoice("DraftPool");
            /*
            we must need a reference to DraftPool size
            while (!validateInput.checkDieInArray(die,size)) die = view.printDieChoice();
             */

            int x = view.printCoordinates("x");
            while(!validateInput.checkColumnIndex(x)) x = view.printCoordinates("x");

            int y = view.printCoordinates("y");
            while(!validateInput.checkRowIndex(y)) y = view.printCoordinates("y");

            network.send(new SetDieMessage(x, y, die).serialize());
        }

        else if(move.equals("use tool card")) {

            //REMEMBER 1<=nToolCard<=12 so the toolCard 1 is the element 0 in the arrayList.
            int nToolCard = view.printToolCardChoice();
            while (!validateInput.checkToolCardInArray(nToolCard-1)) nToolCard = view.printToolCardChoice();

            //num in message is nToolCard
            int dieIndex; // index of die in draft pool
            int action = 0;


            // Tool Card 1, 5, 6, 10 and 11
            if ((nToolCard  == 1) || (nToolCard  == 5) || (nToolCard   == 6) || (nToolCard  == 10) || (nToolCard == 11)) {

                //index of the die from draft pool
                dieIndex = view.printDieChoice("DraftPool");
                /*
            we must need a reference to DraftPool size
            while (!validateInput.checkDieInArray(die,size)) die = view.printDieChoice();
             */
                if (nToolCard == 1) {
                    //action to chose if increase or decrease
                    action = view.printIncreaseOrDecrease();
                    while (!validateInput.increaseOrDecreaseChoice(action)) action = view.printIncreaseOrDecrease();
                    network.send(new ToolCardAMessage(nToolCard, dieIndex, action).serialize());
                }
                else if(nToolCard == 10) {
                    network.send(new ToolCardAMessage(nToolCard, dieIndex, action).serialize());
                }

                else {
                    action = view.printDieChoice("RoundTrack");
                    /*
                    we must need a reference to roundTrack size
                     while (!validateInput.checkDieInArray(die,size)) die = view.printDieChoice();
                    */
                    network.send(new ToolCardAMessage(nToolCard, dieIndex, action).serialize());
                }
            }

            // Tool Card 2 and 3
            else if ((nToolCard == 2) || (nToolCard == 3)) {

                ////we need to control if the new coordinates are the same as the old ones??

                //old coordinates
                int x = view.printCoordinates("x");
                while(!validateInput.checkColumnIndex(x)) x = view.printCoordinates("x");
                int y = view.printCoordinates("y");
                while(!validateInput.checkRowIndex(y)) y = view.printCoordinates("y");

                //new coordinates
                int a = view.printCoordinates("x");
                while(!validateInput.checkColumnIndex(a)) a = view.printCoordinates("x");
                int b = view.printCoordinates("y");
                while(!validateInput.checkRowIndex(b)) b = view.printCoordinates("y");

                network.send(new ToolCardBMessage(nToolCard, x, y , a , b).serialize());
            }

            else if ((nToolCard == 4) || (nToolCard == 12)) {

                /*
                bisogna considerare il caso in cui la toolcard sia la 12
                 */

                //DIE 1
                //old coordinates
                int x = view.printCoordinates("x");
                while(!validateInput.checkColumnIndex(x)) x = view.printCoordinates("x");
                int y = view.printCoordinates("y");
                while(!validateInput.checkRowIndex(y)) y = view.printCoordinates("y");

                //new coordinates
                int a = view.printCoordinates("x");
                while(!validateInput.checkColumnIndex(a)) a = view.printCoordinates("x");
                int b = view.printCoordinates("y");
                while(!validateInput.checkRowIndex(b)) b = view.printCoordinates("y");

                //DIE 2
                //old coordinates
                int x2 = view.printCoordinates("x");
                while(!validateInput.checkColumnIndex(x2)) x2 = view.printCoordinates("x");
                int y2 = view.printCoordinates("y");
                while(!validateInput.checkRowIndex(y2)) y2 = view.printCoordinates("y");

                //new coordinates
                int a2 = view.printCoordinates("x");
                while(!validateInput.checkColumnIndex(a2)) a2 = view.printCoordinates("x");
                int b2 = view.printCoordinates("y");
                while(!validateInput.checkRowIndex(b2)) b2 = view.printCoordinates("y");

                network.send(new ToolCardCMessage(nToolCard, x, y , a , b, x2, y2, a2, b2).serialize());
            }

            else if (nToolCard == 7) {
                network.send(new ToolCardEMessage(nToolCard).serialize());
            }

            else  if ((nToolCard == 8) || (nToolCard == 9)) {
                dieIndex = view.printDieChoice("DraftPool");
                 /*
            we must need a reference to DraftPool size
            while (!validateInput.checkDieInArray(die,size)) die = view.printDieChoice();
             */
                 
                int x = view.printCoordinates("x");
                while(!validateInput.checkColumnIndex(x)) x = view.printCoordinates("x");
                int y = view.printCoordinates("y");
                while(!validateInput.checkRowIndex(y)) y = view.printCoordinates("y");
                network.send(new ToolCardDMessage(nToolCard, dieIndex, x, y).serialize());

            }


        }


        else if(move.equals("end")){
            network.send(new PassMessage().serialize());
        }
        else if(move.equals("help")){
            view.printHelp();
        }
        else{
            view.printUnsupportedMove();
        }
    }

    /*
        calls accept, which calls the visitor pattern methods below
     */

    public void update(String message) {
        ClientMessage gson = parser.parseClient(message);
        gson.accept(this);
    }

    /*
        when such a method is called, the player need to ask for login and then insert a username
     */

    public void visit(LoginRequestMessage message){
        stage = 1;
        view.print("You now have to login and choose a username. Digit 'login' to enter setup.");
    }

    /*
        when such a method is called, the player's already chosen a correct username
     */

    public void visit(ShowWindowsMessage message){
        stage = 2;
        view.printWindows(message);
    }

    public void visit(NewTurnMessage message){
        clientTurn = true;
        view.print("It's your turn. Make a move (digit 'help' to see more).");
    }

    public void visit(ErrorMessage message) {
        if (message.getType() == 0) {
            view.print("A match is being played, try again later.");
            System.exit(1);
        }
        if (message.getType() == 1) {
            view.print("Username already used. Try login again.");
        }
        if (message.getType() == 2) {
            view.printUnsupportedMove();
        }
    }

    public void visit(OkMessage message){
        view.print("Action successful!");
    }

    public void visit(EndTurnMessage message){
        clientTurn = false;
        view.printEndOfTurn();
    }

    public void visit(UpdateModelMessage message){
        view.printUpdate(message);
    }

    public void visit(ShowTableMessage message){
        // TODO
    }

    public void visit(NotificationMessage message){
        // TODO
    }

    /*
        unsupported method
     */

    public void update(String message, int id) {
        throw new UnsupportedOperationException();
    }

    /*
        creates an instance of Client and calls startClient on it
     */

    public static void main(String[] args) {
        ClientManager client = new ClientManager();
        client.startClient();
    }

}
