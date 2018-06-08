package it.polimi.ingsw.view;

import it.polimi.ingsw.messages.client.ShowWindowsMessage;
import it.polimi.ingsw.messages.client.UpdateModelMessage;
import it.polimi.ingsw.model.Die;
import it.polimi.ingsw.model.Player;

import java.util.Scanner;

public class View {

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
    }

    public void printEndOfTurn(){
        System.out.println("Turn ended. Please wait until your next turn.");
    }

    public int printDieChoice() {
        System.out.println("Choose a die and insert its position: ");
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
        System.out.println("'place' to place a die on your Window.\n" +
                "'end' to end your turn.");
    }




}
