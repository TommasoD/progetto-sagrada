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

    private static final String INSERT_VALID_IP = "Insert a valid IP address: ";
    private static final String CONNECTING = "Connecting...\n";
    private static final String IP_NOT_VALID = "IP address is not valid\nA default IP address is used";
    private static final String CONNECTION_CLOSED = "Error in socket\nConnection closed";
    private static final int SOCKET_TIMEOUT = 5000;

    private static final String X = "x";
    private static final String Y = "y";
    private static final String YES = "yes";


    /**
     * Class constructor.
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

    /**
     * Start the client.
     * Allows the client to enter the IP address of the server to connect to.
     * It reads from the xml file the port and also the ip address
     * in case the inserted ip address was not correct.
     * Connects the client to the server via sockets according to
     * the specified parameters
     */

    private void startClient(){
        SetupParser reader = new SetupParser();
        reader.readSetup();
        int port = reader.getPort();


        System.out.print(INSERT_VALID_IP);
        String ip = stdin.nextLine();

        socket = new Socket();
        try{
            System.out.print(CONNECTING);
            socket.connect(new InetSocketAddress(ip, port), SOCKET_TIMEOUT);
        } catch (Exception e) {
            System.out.println(IP_NOT_VALID);
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
                    System.out.println(CONNECTION_CLOSED);
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

    /**
     * Handles the player's requests based on the stage of the game
     * in which the player is at the moment.
     * Valid requests are:
     * - help;
     * - login;
     * - window;
     * - reconnect;
     * @param request the player request.
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

    /**
     * Handles the player's move.
     * Handles the client's requests in case they are to
     * place a die or use a tool card.
     * Calls the specific method according to the chosen tool card.
     * Manages the client inputs, also calling the class
     * to verify if they are correct.
     *
     * Valid moves are:
     * - place;
     * - tool card;
     * - show table;
     * - end;
     * @param move
     */

    private void playerAction(String move){
        if(move.equalsIgnoreCase(PLACE)){

            int die = CLI.printDieFromDraftPool(draftPoolSize);
            while (!validateInput.checkDieInArray(die,draftPoolSize)) die = CLI.printDieFromDraftPool(draftPoolSize);

            int x = CLI.printCoordinates(X);
            while(!validateInput.checkColumnIndex(x)) x = CLI.printCoordinates(X);

            int y = CLI.printCoordinates(Y);
            while(!validateInput.checkRowIndex(y)) y = CLI.printCoordinates(Y);

            if (clientTurn) network.send(new SetDieMessage(x, y, die).serialize());
        }

        else if(move.equalsIgnoreCase(TOOL_CARD)) {

            // 1<=nToolCard<=12 so the toolCard 1 is the element 0 in the arrayList.
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

        else{
            CLI.printError(3);
        }
    }


    /**
     * Manages the use of tool cards number 1, 5, 6, 10 and 11.
     * Different input parameters are required depending
     * on the chosen tool card.
     * ToolCardA is the set of tool cards whose function is restricted
     * to the manipulation of a die.
     * @param nToolCard the number or the chosen tool card.
     * @return a ToolCardAMessage with the relative parameters.
     */

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


    /**
     * Manages the use of tool cards number 2 and 3.
     * Different input parameters are required depending
     * on the chosen tool card.
     * ToolCardB is the set of tool cards whose function
     * allows to place a die ignoring some restriction rules.
     * @param nToolCard the number or the chosen tool card.
     * @return a ToolCardBMessage with the relative parameters.
     */
    private ToolCardBMessage useToolCardB(int nToolCard) {

        //old coordinates
        int x = CLI.printCoordinates(X);
        while(!validateInput.checkColumnIndex(x)) x = CLI.printCoordinates(X);
        int y = CLI.printCoordinates(Y);
        while(!validateInput.checkRowIndex(y)) y = CLI.printCoordinates(Y);

        //new coordinates
        int a = CLI.printCoordinates(X);
        while(!validateInput.checkColumnIndex(a)) a = CLI.printCoordinates(X);
        int b = CLI.printCoordinates(Y);
        while(!validateInput.checkRowIndex(b)) b = CLI.printCoordinates(Y);

        return new ToolCardBMessage(nToolCard, x, y , a , b);

    }

    /**
     * Manages the use of tool cards number 4 and 12.
     * Different input parameters are required depending
     * on the chosen tool card.
     * ToolCardC is the set of tool cards whose function
     * allows to place two dice ignoring some restriction
     * rules.
     * @param nToolCard the number or the chosen tool card.
     * @return a ToolCardCMessage with the relative parameters.
     */

    private ToolCardCMessage useToolCardC(int nToolCard) {
        //DIE 1
        //old coordinates
        int x = CLI.printCoordinates(X);
        while(!validateInput.checkColumnIndex(x)) x = CLI.printCoordinates(X);
        int y = CLI.printCoordinates(Y);
        while(!validateInput.checkRowIndex(y)) y = CLI.printCoordinates(Y);

        //new coordinates
        int a = CLI.printCoordinates(X);
        while(!validateInput.checkColumnIndex(a)) a = CLI.printCoordinates(X);
        int b = CLI.printCoordinates(Y);
        while(!validateInput.checkRowIndex(b)) b = CLI.printCoordinates(Y);

        String choice = "";
        if (nToolCard == 12) choice = CLI.printChooseAnotherDie();
        if ((nToolCard == 4) || (choice.equalsIgnoreCase(YES))) {
            //DIE 2
            //old coordinates
            int x2 = CLI.printCoordinates(X);
            while (!validateInput.checkColumnIndex(x2)) x2 = CLI.printCoordinates(X);
            int y2 = CLI.printCoordinates(Y);
            while (!validateInput.checkRowIndex(y2)) y2 = CLI.printCoordinates(Y);

            //new coordinates
            int a2 = CLI.printCoordinates(X);
            while (!validateInput.checkColumnIndex(a2)) a2 = CLI.printCoordinates(X);
            int b2 = CLI.printCoordinates(Y);
            while (!validateInput.checkRowIndex(b2)) b2 = CLI.printCoordinates(Y);

            return new ToolCardCMessage(nToolCard, x, y, a, b, x2, y2, a2, b2);
        }
        return new ToolCardCMessage(nToolCard, x, y, a, b, -1, -1, -1, -1);
    }

    /**
     * Manages the use of tool cards number 8 and 9.
     * Different input parameters are required depending
     * on the chosen tool card.
     * @param nToolCard the number or the chosen tool card.
     * @return  a ToolCardDMessage with the relative parameters.
     */

    private ToolCardDMessage useToolCardD(int nToolCard) {
        int dieIndex = CLI.printDieFromDraftPool(draftPoolSize);
        while (!validateInput.checkDieInArray(dieIndex,draftPoolSize)) dieIndex = CLI.printDieFromDraftPool(draftPoolSize);
        int x = CLI.printCoordinates(X);
        while(!validateInput.checkColumnIndex(x)) x = CLI.printCoordinates(X);
        int y = CLI.printCoordinates(Y);
        while(!validateInput.checkRowIndex(y)) y = CLI.printCoordinates(Y);
        if (clientTurn) network.send(new ToolCardDMessage(nToolCard, dieIndex, x, y).serialize());
        return new ToolCardDMessage(nToolCard, dieIndex, x, y);

    }

    /**
     * Handles a message from the network.
     * @param message the message depicting the event.
     */
    public void update(String message) {
        ClientMessage gson = parser.parseClient(message);
        gson.accept(this);
    }

    /**
     * When the method is called, the player need to ask
     * for login and then insert a valid username.
     * @param message the LoginRequestMessage.
     */

    public void visit(LoginRequestMessage message){
        stage = 1;
        CLI.printDigitLogin();
    }

    /**
     * When the method is called the player's
     * already chosen a correct username.
     * Allows to show the windows to be chosen by the player.
     * @param message the ShowWindowsMessage.
     */

    public void visit(ShowWindowsMessage message){
        stage = 2;
        CLI.printWindows(message);
    }

    /**
     * Allows you to manage a player's new turn.
     * @param message the NewTurnMessage.
     */

    public void visit(NewTurnMessage message){
        clientTurn = true;
        CLI.printYourTurn();
    }

    /**
     * Print the error message received.
     * @param message the ErrorMessage.
     */
    public void visit(ErrorMessage message) {
        CLI.printError(message.getType());
    }

    /**
     * Print that the action was performed correctly.
     * @param message the OkMessage.
     */

    public void visit(OkMessage message){
        CLI.printActionSuccessful();
    }

    /**
     * Allows you to manage the end of a player's turn.
     * Print that the turn is ended.
     * @param message the EndTurnMessage.
     */

    public void visit(EndTurnMessage message){
        clientTurn = false;
        CLI.printEndOfTurn();
    }

    /**
     * Update the size of round track and the
     * size of the draft pool.
     * @param message the UpdateModelMessage.
     */
    public void visit(UpdateModelMessage message){
        stage = 3;
        roundTrackSize = message.getRoundTrack().size();
        draftPoolSize = message.getDraft().size();
        CLI.printUpdate(message);
    }


    /**
     * Handles the message send by the client to see his
     * private objective, the public objectives and the tool cards.
     * @param message the ShowTableMessage.
     */
    public void visit(ShowTableMessage message){
        CLI.printShowTable(message.getPrivateObjective(),message.getPublicObjective(),message.getToolCards());
    }

    /**
     *
     * @param message the GameOverMessage.
     */
    public void visit(GameOverMessage message){
       CLI.printWinner(message.getWinner());
        this.gameEnded = true;
    }

    /**
     *
     * @param message the DieColorMessage.
     */
    public void visit(DieColorMessage message){
        CLI.printDieColor(message.getColor());
    }

    /**
     *
     * @param message the NotificationMessage.
     */
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
