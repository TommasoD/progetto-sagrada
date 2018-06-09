package it.polimi.ingsw.view;

import it.polimi.ingsw.messages.client.ShowWindowsMessage;
import it.polimi.ingsw.messages.client.UpdateModelMessage;
import it.polimi.ingsw.model.Die;
import it.polimi.ingsw.model.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class View {

    private ArrayList<String> windowsName = new ArrayList<String>();

    private Scanner stdin = new Scanner(System.in);

    public void print(String s){
        System.out.println(s);
    }

    public void printUpdate(UpdateModelMessage message){
        StringBuilder sb = new StringBuilder();
        for (Die d : message.getDraft()) {
            sb.append(d.toString());
            sb.append(" ");
        }
        System.out.println("Draft: " + sb.toString() + "\u001B[0m");
        sb = new StringBuilder();
        for (Die d : message.getRoundTrack()) {
            sb.append(d.toString());
            sb.append(" ");
        }
        System.out.println("Track: " + sb.toString() + "\u001B[0m");
        for(Player p : message.getPlayers()){
            p.dump();
        }
    }

    public void printWindows(ShowWindowsMessage message){
        System.out.println("You have to choose one of the following windows:\n");
        System.out.println(message.getW1());
        System.out.println(message.getW2());
        System.out.println(message.getW3());
        System.out.println(message.getW4());
        System.out.println("Digit 'window' to enter setup.");
        windowsName.add(message.getW1().getName());
        windowsName.add(message.getW2().getName());
        windowsName.add(message.getW3().getName());
        windowsName.add(message.getW4().getName());
    }

    public ArrayList<String> getWindowsName() {
        return windowsName;
    }

    public String printInsertWindow() {
        System.out.println("Now insert the name of the desired window: ");
        return stdin.nextLine();
    }

    public void printEndOfTurn(){
        System.out.println("Turn ended. Please wait until your next turn.");
    }

    public int printDieChoice(String s) {
        System.out.println("Choose a die from " + s + " and insert its position: ");
        return Integer.parseInt(stdin.nextLine());
    }

    public int printCoordinates(String c) {
        System.out.println("Insert the " + c + " coordinate: ");
        return Integer.parseInt(stdin.nextLine());
    }


    //General print
    public void printUnsupportedMove() {
        System.out.println("Unsupported move. Digit 'help' to see the supported actions.");
    }

        public void printHelp() {
        System.out.println("'place' to place a die on your Window.\n" + "'use tool card' to use a selected tool card\n" +
                "'end' to end your turn.");
    }

    public String printLogin() {
        System.out.println("Insert username: ");
        return stdin.nextLine();
    }

    public void printWaitForTheStart() {
        System.out.println("Wait for the start of the match.");
    }

    public void printDigit(int id) {
        if (id == 1) System.out.println("You have to login first. Digit 'login'.");
            else if (id == 2) System.out.println("You have to choose a window. Digit 'window' to do so.");
             else System.out.println("Id not found");
    }


    //ToolCard print
    public int printToolCardChoice() {
        System.out.println("Enter the number of the card you want to use");
        return Integer.parseInt(stdin.nextLine());
    }

    public int printIncreaseOrDecrease() {
        System.out.println("Insert 0 if you want to decrease the value of the die by one\nInsert 1 to increase by one");
        int c = Integer.parseInt(stdin.nextLine());
        return c;
    }

    public int printDieValue() {
        System.out.println("Select a value for the new die ( 0 - 6 )");
        int v = Integer.parseInt(stdin.nextLine());
        return v;
    }

    public String printChoiceAnotherDie() {
        System.out.println("Do you want to choose another die? 'yes' or 'no'");
        String c = stdin.nextLine();
        while ((!c.equalsIgnoreCase("yes"))&&(!c.equalsIgnoreCase("no"))) {
            System.out.println("Invalid entry");
            c = stdin.nextLine();
        }
        return  c;
    }




}
