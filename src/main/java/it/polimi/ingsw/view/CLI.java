package it.polimi.ingsw.view;

import it.polimi.ingsw.messages.client.ShowWindowsMessage;
import it.polimi.ingsw.messages.client.UpdateModelMessage;
import it.polimi.ingsw.model.Die;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.ToolCard;
import it.polimi.ingsw.model.objectives.PrivateObjective;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
    private static final String YOUR_TURN = "It's your turn. Make a move (digit 'help' to see more).";

    private static final String HELP = "'login' to choose a username\n" +
            "'window to choose a window\n" +
            "'place' to place a die on your Window.\n" +
            "'tool card' to use a selected tool card\n" +
            "'show table' to check your private objective, public objectives and tool cards\n" +
            "'end' to end your turn.\n" +
            "'reconnect' to rejoin the game when suspended";

    private static final String INSERT_WINDOW_NAME = "Now insert the name of the desired window: ";
    private static final String DIGIT_WINDOW = "You have to choose a window. Digit 'window' to do so.";
    private static final String DIGIT_LOGIN = "You have to login and choose a username. Digit 'login' to enter setup.";
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
    private static final String ACTION_SUCCESSFUL = "Action successful!";
    private static final String THANKS_FOR_PLAYING = "Thanks for playing!";
    private static final String THE_WINNER_IS = "The winner is ";
    private static final String YES = "yes";
    private static final String NO = "no";


    private ArrayList<String> windowsName = new ArrayList<String>();
    private Scanner stdin = new Scanner(System.in);


    /**
     * Print the message received.
     * @param msg the message.
     */
    private void print (String msg) {
        System.out.println(msg);
    }


    //General write

    /**
     * Return the request written by the player.
     * @return the player request.
     */
    public String writeRequest() {
        return stdin.nextLine();
    }


    //General print

    /**
     * Print the error related to the input id.
     * @param id the error id.
     */

    public void printError(int id) {
        switch (id) {
            case 1: print(USERNAME_ALREADY_USED);
                    break;
            case 2: print(INVALID_MOVE);
                    break;
            case 3: print(UNSUPPORTED_MOVE);
                    break;
            case 4: print(CAN_NOT_USE_TOOL_CARD);
                    break;
            case 5: print(CAN_NOT_RECONNECT);
                    break;
        }
    }




    /**
     * Print the event.
     * @param user the name of the player running the event.
     * @param event the type of event.
     */
    public void printEvent(String user, String event) {
        if (event.equals(RECONNECT)) print(user + IS_RECONNECT);
        else if (event.equals(DISCONNECT)) print(user + IS_DISCONNECTED);
        else if (event.equals(SUSPEND)) print(user + IS_SUSPENDED);
    }

    /**
     * Print the help string.
     */

    public void printHelp() { print(HELP);}

    /**
     * Notify the player that his turn has started.
     */

    public void printYourTurn() {
        print(YOUR_TURN);
    }

    /**
     * Notify the player that his turn has ended.
     */

    public void printEndOfTurn(){
        print(TURN_ENDED);
    }

    /**
     * Notify the player to wait for the start of the match.
     */

    public void printWaitForTheMatch() {
        print(WAIT_FOR_THE_MATCH);
    }

    /**
     * Notify the player to wait for the start of his turn.
     */

    public void printWaitForYourTurn() {
        print(WAIT_FOR_YOUR_TURN);
    }

    /**
     *
     */

    public void printActionSuccessful() {
        print(ACTION_SUCCESSFUL);
    }


    public void printWinner(String winner) {
        print(THE_WINNER_IS + winner );
        print(THANKS_FOR_PLAYING);
    }

    /*
    --------------------------------------------------------------------------------------
     */

    //setup print
    public String printLogin() {
        print(INSERT_USERNAME);
        return stdin.nextLine();
    }

    //Window print

    /**
     *
     * @param message
     */
    public void printWindows(ShowWindowsMessage message){
        print(CHOOSE_WINDOW);
        print(message.getW1().toString());
        print(message.getW2().toString());
        print(message.getW3().toString());
        print(message.getW4().toString());
        printDigitWindow();
        windowsName.add(message.getW1().getName());
        windowsName.add(message.getW2().getName());
        windowsName.add(message.getW3().getName());
        windowsName.add(message.getW4().getName());
    }

    public String printInsertWindow() {
        print(INSERT_WINDOW_NAME);
        return stdin.nextLine();
    }


    public ArrayList<String> getWindowsName() {
        return windowsName;
    }


    public void printUpdate(UpdateModelMessage message){
        print(ROUND +  "#" + message.getRound());
        StringBuilder sb = new StringBuilder();
        for (Die d : message.getDraft()) {
            sb.append(d.toString());
            sb.append(" ");
        }
        print(DRAFT + sb.toString() + "\u001B[0m");
        sb = new StringBuilder();
        for (Die d : message.getRoundTrack()) {
            sb.append(d.toString());
            sb.append(" ");
        }
        print(TRACK  + sb.toString() + "\u001B[0m");
        for(Player p : message.getPlayers()){
            p.dump();
        }
    }

     /*
    ----------------------------------------------------------------------------
     */


    //Player print
    public int printDieFromRoundTrack(int size) {
        System.out.print(CHOOSE_DIE_FROM_ROUND_TRACK + (size-1) );
        return Integer.parseInt(stdin.nextLine());
    }

    public int printDieFromDraftPool(int size) {
        print(CHOOSE_DIE_FROM_DRAFT_POOL  + (size-1) );
        return Integer.parseInt(stdin.nextLine());
    }

    public int printCoordinates(String c) {
        print(INSERT_COORDINATE + c);
        return Integer.parseInt(stdin.nextLine());
    }

    public void printDigitWindow() {
        print(DIGIT_WINDOW);
    }

    public void printDigitLogin() {
        print(DIGIT_LOGIN);
    }

    public void printShowTable(PrivateObjective privateObjective, List<String> publicObjective, List<ToolCard> toolCards) {
        print(YOUR_PRIVATE_OBJECTIVE  + privateObjective.toString());
        print("\n" + PUBLIC_OBJECTIVES);
        for (String p : publicObjective) {
            print(p);
        }
        print("\n" + TOOL_CARDS);
        for (ToolCard t : toolCards) {
            t.dump();
        }
    }

    /*
    ----------------------------------------------------------------------------
     */


    //ToolCard print
    public int printChooseAToolCard() {
        print(CHOOSE_A_TOOL_CARD);
        return Integer.parseInt(stdin.nextLine());
    }


    public int printIncreaseOrDecrease() {
        print(INCREASE_OR_DECREASE);
        return Integer.parseInt(stdin.nextLine());
    }

    public int printChooseDieValue() {
        print(CHOOSE_DIE_VALUE );
        return Integer.parseInt(stdin.nextLine());
    }

    public String printChooseAnotherDie() {
        print(CHOOSE_ANOTHER_DIE);
        String choice = stdin.nextLine();
        while ((!choice.equalsIgnoreCase(YES))&&(!choice.equalsIgnoreCase(NO))) {
            print(INVALID_ENTRY);
            choice = stdin.nextLine();
        }
        return  choice;
    }



    public void printDieColor(String color) {
        print(color);
    }


}
