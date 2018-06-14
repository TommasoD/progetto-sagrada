package it.polimi.ingsw.client;

import it.polimi.ingsw.messages.client.*;
import it.polimi.ingsw.messages.controller.*;
import it.polimi.ingsw.parsers.GsonParser;
import it.polimi.ingsw.parsers.SetupParser;
import it.polimi.ingsw.utils.Observer;
import it.polimi.ingsw.view.CLI;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;


public class ClientManager implements Observer<String> {

    private Scanner stdin;
    private GsonParser parser;
    private CLI CLI;
    private Socket socket;
    private ClientConnection network;
    private boolean clientTurn;
    private boolean gameEnded;
    private int roundTrackSize;
    private int draftPoolSize;
    private int stage;              //0 -> before match, 1 -> login, 2 -> choose window, 3 -> client ready
    private  ValidateInput validateInput;

    private static final String HELP = "help";
    private static final String LOGIN = "login";
    private static final String WINDOW = "window";
    private static final String RECONNECT = "reconnect";
    private static final String PLACE = "place";
    private static final String TOOL_CARD = "tool card";
    private static final String SHOW_TABLE = "show table";
    private static final String END = "end";

    /*
        constructor
     */

    private ClientManager(){
        stdin = new Scanner(System.in);
        parser = new GsonParser();
        CLI = new CLI();
        clientTurn = false;
        gameEnded = false;
        stage = 0;
        validateInput = new ValidateInput();
    }

    private void startClient(){
        SetupParser reader = new SetupParser();
        reader.readSetup();
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
            handleRequest(CLI.writeRequest());
        }
    }

    /*
        handles the player's requests
     */

    private void handleRequest(String request){

        if (request.equalsIgnoreCase(HELP)) {
            CLI.printHelp();
            return;
        }

        if(stage == 0){
            CLI.printWaitForTheMatch();
            return;
        }
        if(stage == 1){
            if(request.equalsIgnoreCase(LOGIN)){
                LoginMessage gson = new LoginMessage(CLI.printLogin());
                network.send(gson.serialize());
                return;
            }
            CLI.printDigitLogin();
            return;
        }
        if(stage == 2){
            if(request.equalsIgnoreCase(WINDOW)){
                String line = CLI.printInsertWindow();
                if (!validateInput.checkWindowName(CLI.getWindowsName(), line))  return;
                    else {
                    CLI.printWaitForTheMatch();
                    stage = 3;
                    ChooseWindowMessage m = new ChooseWindowMessage(line);
                    network.send(m.serialize());
                    return;
                }
            }

            CLI.printDigitWindow();
            return;
        }

        if(!clientTurn){
            if(request.equalsIgnoreCase(RECONNECT)){
                network.send(new ReconnectMessage().serialize());
                return;
            }
            CLI.printWaitForYourTurn();
            return;
        }
        playerAction(request);
    }

       /*
        handles the player's turn
     */

    private void playerAction(String move){
        if(move.equalsIgnoreCase(PLACE)){

            int die = CLI.printDieFromDraftPool(draftPoolSize);
            while (!validateInput.checkDieInArray(die,draftPoolSize)) die = CLI.printDieFromDraftPool(draftPoolSize);

            int x = CLI.printCoordinates("x");
            while(!validateInput.checkColumnIndex(x)) x = CLI.printCoordinates("x");

            int y = CLI.printCoordinates("y");
            while(!validateInput.checkRowIndex(y)) y = CLI.printCoordinates("y");

            if (clientTurn) network.send(new SetDieMessage(x, y, die).serialize());
        }

        else if(move.equalsIgnoreCase(TOOL_CARD)) {

            //REMEMBER 1<=nToolCard<=12 so the toolCard 1 is the element 0 in the arrayList.
            int nToolCard = CLI.printChooseAToolCard();
            while (!validateInput.checkToolCardInArray(nToolCard-1)) nToolCard = CLI.printChooseAToolCard();

            if ((nToolCard  == 1) || (nToolCard  == 5) || (nToolCard   == 6) || (nToolCard  == 10) || (nToolCard == 11)) {
               if(clientTurn) network.send(useToolCardA(nToolCard).serialize());
            }

            else if ((nToolCard == 2) || (nToolCard == 3)) {
                if(clientTurn) network.send(useToolCardB(nToolCard).serialize());
            }

            else if ((nToolCard == 4) || (nToolCard == 12)) {
                if (clientTurn) network.send(useToolCardC(nToolCard).serialize());
                }

            else  if ((nToolCard == 8) || (nToolCard == 9)) {
                if (clientTurn) network.send(useToolCardD(nToolCard).serialize());
            }

            //toolCard 7
            else {
                if (clientTurn) network.send(new ToolCardEMessage(nToolCard).serialize());
            }

        }


        else if (move.equalsIgnoreCase(SHOW_TABLE)) {
            network.send(new ShowTableMessage().serialize());
        }

        else if(move.equalsIgnoreCase(END)){
            if (clientTurn) network.send(new PassMessage().serialize());
        }
       /* else if(move.equalsIgnoreCase("help")){
            CLI.printHelp();
        }*/
        else{
            CLI.printError(3);
        }
    }


    private ToolCardAMessage useToolCardA(int nToolCard) {
        int dieIndex;
        int action = 0;
        dieIndex = CLI.printDieFromDraftPool(draftPoolSize);
        while (!validateInput.checkDieInArray(dieIndex,draftPoolSize)) dieIndex = CLI.printDieFromDraftPool(draftPoolSize);
        if (nToolCard == 1) {
            action = CLI.printIncreaseOrDecrease();
            while (!validateInput.increaseOrDecreaseChoice(action)) action = CLI.printIncreaseOrDecrease();
            return new ToolCardAMessage(nToolCard, dieIndex, action);
        }
        if(nToolCard == 5){
            action = CLI.printDieFromRoundTrack(roundTrackSize);
            while (!validateInput.checkDieInArray(action,roundTrackSize)) action = CLI.printDieFromRoundTrack(roundTrackSize);
            return new ToolCardAMessage(nToolCard, dieIndex, action);
        }

        if(nToolCard == 11) {
            network.send(new ToolCardAMessage(nToolCard, dieIndex, action).serialize());
            int newValue = CLI.printChooseDieValue();
            while (!validateInput.checkDieValue(newValue)) newValue = CLI.printChooseDieValue();
            return new ToolCardAMessage(nToolCard, 0, newValue);
        }
        return new ToolCardAMessage(nToolCard, dieIndex, action);
    }

    private ToolCardBMessage useToolCardB(int nToolCard) {
        //old coordinates
        int x = CLI.printCoordinates("x");
        while(!validateInput.checkColumnIndex(x)) x = CLI.printCoordinates("x");
        int y = CLI.printCoordinates("y");
        while(!validateInput.checkRowIndex(y)) y = CLI.printCoordinates("y");

        //new coordinates
        int a = CLI.printCoordinates("x");
        while(!validateInput.checkColumnIndex(a)) a = CLI.printCoordinates("x");
        int b = CLI.printCoordinates("y");
        while(!validateInput.checkRowIndex(b)) b = CLI.printCoordinates("y");

        return new ToolCardBMessage(nToolCard, x, y , a , b);

    }

    private ToolCardCMessage useToolCardC(int nToolCard) {
        //DIE 1
        //old coordinates
        int x = CLI.printCoordinates("x");
        while(!validateInput.checkColumnIndex(x)) x = CLI.printCoordinates("x");
        int y = CLI.printCoordinates("y");
        while(!validateInput.checkRowIndex(y)) y = CLI.printCoordinates("y");

        //new coordinates
        int a = CLI.printCoordinates("x");
        while(!validateInput.checkColumnIndex(a)) a = CLI.printCoordinates("x");
        int b = CLI.printCoordinates("y");
        while(!validateInput.checkRowIndex(b)) b = CLI.printCoordinates("y");

        String choice = "";
        if (nToolCard == 12) choice = CLI.printChooseAnotherDie();
        if ((nToolCard == 4) || (choice.equalsIgnoreCase("yes"))) {
            //DIE 2
            //old coordinates
            int x2 = CLI.printCoordinates("x");
            while (!validateInput.checkColumnIndex(x2)) x2 = CLI.printCoordinates("x");
            int y2 = CLI.printCoordinates("y");
            while (!validateInput.checkRowIndex(y2)) y2 = CLI.printCoordinates("y");

            //new coordinates
            int a2 = CLI.printCoordinates("x");
            while (!validateInput.checkColumnIndex(a2)) a2 = CLI.printCoordinates("x");
            int b2 = CLI.printCoordinates("y");
            while (!validateInput.checkRowIndex(b2)) b2 = CLI.printCoordinates("y");

            return new ToolCardCMessage(nToolCard, x, y, a, b, x2, y2, a2, b2);
        }
        return new ToolCardCMessage(nToolCard, x, y, a, b, -1, -1, -1, -1);
    }

    private ToolCardDMessage useToolCardD(int nToolCard) {
        int dieIndex = CLI.printDieFromDraftPool(draftPoolSize);
        while (!validateInput.checkDieInArray(dieIndex,draftPoolSize)) dieIndex = CLI.printDieFromDraftPool(draftPoolSize);
        int x = CLI.printCoordinates("x");
        while(!validateInput.checkColumnIndex(x)) x = CLI.printCoordinates("x");
        int y = CLI.printCoordinates("y");
        while(!validateInput.checkRowIndex(y)) y = CLI.printCoordinates("y");
        if (clientTurn) network.send(new ToolCardDMessage(nToolCard, dieIndex, x, y).serialize());
        return new ToolCardDMessage(nToolCard, dieIndex, x, y);

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
        CLI.printDigitLogin();
    }

    /*
        when such a method is called, the player's already chosen a correct username
     */

    public void visit(ShowWindowsMessage message){
        stage = 2;
        CLI.printWindows(message);
    }

    public void visit(NewTurnMessage message){
        clientTurn = true;
        CLI.printYourTurn();
    }

    public void visit(ErrorMessage message) {
        CLI.printError(message.getType());
    }

    public void visit(OkMessage message){
        CLI.printActionSuccessful();
    }

    public void visit(EndTurnMessage message){
        clientTurn = false;
        CLI.printEndOfTurn();
    }

    public void visit(UpdateModelMessage message){
        stage = 3;
        roundTrackSize = message.getRoundTrack().size();
        draftPoolSize = message.getDraft().size();
        CLI.printUpdate(message);
    }

    public void visit(ShowTableMessage message){
        CLI.printShowTable(message.getPrivateObjective(),message.getPublicObjective(),message.getToolCards());
    }

    public void visit(GameOverMessage message){
       CLI.printWinner(message.getWinner());
        this.gameEnded = true;
    }

    public void visit(DieColorMessage message){
        CLI.printDieColor(message.getColor());
    }

    public void visit(NotificationMessage message){
       CLI.printEvent(message.getUsername(),message.getEvent());
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
