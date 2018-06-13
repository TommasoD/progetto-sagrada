package it.polimi.ingsw.view;

import it.polimi.ingsw.messages.client.ShowWindowsMessage;
import it.polimi.ingsw.messages.client.UpdateModelMessage;
import it.polimi.ingsw.model.Die;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.ToolCard;
import it.polimi.ingsw.model.objectives.PrivateObjective;
import it.polimi.ingsw.model.objectives.publicobjectives.PublicObjective;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


//client rimangono il controllo sia prima sia quando devo mandare il messaggio per verificare che è il suo turno

///spostare i controlli nella view

//rendere tutte el stringhe come costanti

//scritta reconnect quando viene sospeso

public class CLI {

    private static final String INSERT_USERNAME = "Insert username: ";
    private static final String USERNAME_ALREADY_USED = "Username already used. Try login again.";
    private static final String INVALID_MOVE = "Invalid move";
    private static final String UNSUPPORTED_MOVE = "Unsupported move. Digit 'help' to see the supported actions.";
    private static final String CAN_NOT_USE_TOOL_CARD = "You can not use the selected tool card";
    private static final String CAN_NOT_RECONNECT = "'reconnect' command is not supported. you are already online";

    private static final String IS_RECONNECT =  " is reconnected";
    private static final String RECONNECT = "reconnect";
    private static final String IS_DISCONNECTED = " is disconnected";
    private static final String DISCONNECT = "disconnect";
    private static final String IS_SUSPENDED = " is suspended";
    private static final String SUSPEND = "suspend";

    private static final String WAIT_FOR_YOUR_TURN = "Wait for your turn.";
    private static final String WAIT_FOR_THE_MATCH = "Wait for the start of the match.";
    private static final String TURN_ENDED = "Turn ended. Please wait until your next turn.";

    private static final String HELP = "'login' to choose a username\n" +
            "'window to choose a window\n" +
            "'place' to place a die on your Window.\n" +
            "'tool card' to use a selected tool card\n" +
            "'show table' to check your private objective, public objectives and tool cards\n" +
            "'end' to end your turn.\n" +
            "'reconnect' to rejoin the game when suspended";

    private static final String INSERT_WINDOW_NAME = "Now insert the name of the desired window: ";
    private static final String DIGIT_WINDOW = "You have to choose a window. Digit 'window' to do so.";
    private static final String DIGIT_LOGIN = "You have to login first. Digit 'login'.";
    private static final String CHOOSE_WINDOW = "You have to choose one of the following windows:\n";
    private static final String CHOOSE_DIE_FROM_DRAFT_POOL = "Choose a die from the DraftPool and insert its position.\nInsert a number from 0 to ";
    private static final String CHOOSE_DIE_FROM_ROUND_TRACK = "Choose a die from the RoundTrack and insert its position\nInsert a number from 0 to ";
    private static final String INSERT_COORDINATE = "Insert the coordinate for: ";

    private static final String CHOOSE_A_TOOL_CARD = "Enter the number of the card you want to use (1 - 12)";
    private static final String INCREASE_OR_DECREASE = "Insert 0 if you want to decrease the value of the die by one\nInsert 1 to increase by one";
    private static final String CHOOSE_DIE_VALUE = "Select a value for the new die ( 1 - 6 )";
    private static final String CHOOSE_ANOTHER_DIE = "Do you want to choose another die? 'yes' or 'no'";

    private static final String ROUND = "Round ";
    private static final String DRAFT = "Draft: ";
    private static final String TRACK = "Track: ";
    private static final String YOUR_PRIVATE_OBJECTIVE = "YOUR PRIVATE OBJECTIVE IS: ";
    private static final String PUBLIC_OBJECTIVES = "PUBLIC OBJECTIVES";
    private static final String TOOL_CARDS = "TOOL CARDS:";

    private static final String INVALID_ENTRY = "Invalid entry";
    private static final String THANKS_FOR_PLAYING = "Thanks for playing!";
    private static final String THE_WINNER_IS = "The winner is ";


    private ArrayList<String> windowsName = new ArrayList<String>();
    private Scanner stdin = new Scanner(System.in);


    //General write
    public String writeRequest() {
        return stdin.nextLine();
    }


    //General print
    public void printError(int id) {
        switch (id) {
            case 1: System.out.println(USERNAME_ALREADY_USED);
            case 2: System.out.println(INVALID_MOVE);
            case 3: System.out.println(UNSUPPORTED_MOVE);
            case 4: System.out.println(CAN_NOT_USE_TOOL_CARD);
            case 5: System.out.println(CAN_NOT_RECONNECT);
        }
    }

    public void printEvent(String user, String event) {
        if (event.equals(RECONNECT)) System.out.println(user + IS_RECONNECT);
        else if (event.equals(DISCONNECT)) System.out.println(user + IS_DISCONNECTED);
        else if (event.equals(SUSPEND)) System.out.println(user + IS_SUSPENDED);
    }

    public void printHelp() { System.out.println(HELP);}

    public void printEndOfTurn(){
        System.out.println(TURN_ENDED);
    }

    public void printWaitForTheMatch() {
        System.out.println(WAIT_FOR_THE_MATCH);
    }

    public void printWaitForYourTurn() {
        System.out.println(WAIT_FOR_YOUR_TURN);
    }


    public void printWinner(String winner) {
        System.out.println(THE_WINNER_IS + winner );
        System.out.println(THANKS_FOR_PLAYING);
    }

    /*
    --------------------------------------------------------------------------------------
     */

    //da eliminare
    public void print(String s){
        System.out.println(s);
    }
    //setup print
    public String printLogin() {
        System.out.println(INSERT_USERNAME);
        return stdin.nextLine();
    }

    //Window print
    public void printWindows(ShowWindowsMessage message){
        System.out.println(CHOOSE_WINDOW);
        System.out.println(message.getW1());
        System.out.println(message.getW2());
        System.out.println(message.getW3());
        System.out.println(message.getW4());
        printDigitWindow();
        windowsName.add(message.getW1().getName());
        windowsName.add(message.getW2().getName());
        windowsName.add(message.getW3().getName());
        windowsName.add(message.getW4().getName());
    }

    public String printInsertWindow() {
        System.out.println(INSERT_WINDOW_NAME);
        return stdin.nextLine();
    }

    //SI PUO RENDERE PRIVATE SE SPOSTO IN CONTROLLI
    public ArrayList<String> getWindowsName() {
        return windowsName;
    }


    public void printUpdate(UpdateModelMessage message){
        System.out.println(ROUND +  "#" + message.getRound());
        StringBuilder sb = new StringBuilder();
        for (Die d : message.getDraft()) {
            sb.append(d.toString());
            sb.append(" ");
        }
        System.out.println(DRAFT + sb.toString() + "\u001B[0m");
        sb = new StringBuilder();
        for (Die d : message.getRoundTrack()) {
            sb.append(d.toString());
            sb.append(" ");
        }
        System.out.println(TRACK  + sb.toString() + "\u001B[0m");
        for(Player p : message.getPlayers()){
            p.dump();
        }
    }

     /*
    ----------------------------------------------------------------------------
     */


    //Player print
    public int printDieFromRoundTrack(int size) {
        System.out.print(CHOOSE_DIE_FROM_ROUND_TRACK + size );
        return Integer.parseInt(stdin.nextLine());
    }

    public int printDieFromDraftPool(int size) {
        System.out.println(CHOOSE_DIE_FROM_DRAFT_POOL  + size );
        return Integer.parseInt(stdin.nextLine());
    }

    public int printCoordinates(String c) {
        System.out.println(INSERT_COORDINATE + c);
        return Integer.parseInt(stdin.nextLine());
    }

    public void printDigitWindow() {
        System.out.println(DIGIT_WINDOW);
    }

    public void printDigitLogin() {
        System.out.println(DIGIT_LOGIN);
    }

    public void printShowTable(PrivateObjective privateObjective, List<String> publicObjective, List<ToolCard> toolCards) {
        System.out.println(YOUR_PRIVATE_OBJECTIVE  + privateObjective.toString());
        System.out.println("\n" + PUBLIC_OBJECTIVES);
        for (String p : publicObjective) {
            System.out.println(p);
        }
        System.out.println("\n" + TOOL_CARDS);
        for (ToolCard t : toolCards) {
            t.dump();
        }
    }

    /*
    ----------------------------------------------------------------------------
     */


    //ToolCard print
    public int printChooseAToolCard() {
        System.out.println(CHOOSE_A_TOOL_CARD);
        return Integer.parseInt(stdin.nextLine());
    }


    public int printIncreaseOrDecrease() {
        System.out.println(INCREASE_OR_DECREASE);
        return Integer.parseInt(stdin.nextLine());
    }

    public int printChooseDieValue() {
        System.out.println(CHOOSE_DIE_VALUE );
        return Integer.parseInt(stdin.nextLine());
    }

    public String printChooseAnotherDie() {
        System.out.println(CHOOSE_ANOTHER_DIE);
        String choice = stdin.nextLine();
        while ((!choice.equalsIgnoreCase("yes"))&&(!choice.equalsIgnoreCase("no"))) {
            System.out.println(INVALID_ENTRY);
            choice = stdin.nextLine();
        }
        return  choice;
    }



    public void printDieColor(String color) {
        System.out.println(color);
    }


}
