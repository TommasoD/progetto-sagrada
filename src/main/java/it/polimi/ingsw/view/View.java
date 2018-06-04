package it.polimi.ingsw.view;

import it.polimi.ingsw.messages.client.ShowWindowsMessage;
import it.polimi.ingsw.messages.client.UpdateModelMessage;
import it.polimi.ingsw.model.Die;
import it.polimi.ingsw.model.Player;

public class View {

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
        System.out.println("Choose your Window:\n");
        System.out.println(message.getW1());
        System.out.println(message.getW2());
        System.out.println(message.getW3());
        System.out.println(message.getW4());
        System.out.println("\nInsert the name of the desired Window:");
    }
}
