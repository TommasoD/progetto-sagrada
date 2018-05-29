package it.polimi.ingsw.view;

import com.google.gson.stream.JsonReader;
import it.polimi.ingsw.messages.SetDieMessage;
import it.polimi.ingsw.messages.ShowWindowsMessage;
import it.polimi.ingsw.utils.Observable;

import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;

public class View extends Observable{

    //View observes Client

    private Scanner stdin = new Scanner(System.in);

    public void print(String message) {

        String id = getIdMessage(message);
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
            notify(line);
        }

    }

    public void move() {
        System.out.println("Choose your move\n1. Place a die\n2... ");
        String line = stdin.nextLine();
        if(line.equals("1")) {
            System.out.print("Choose a die: "); ////stampo draft
            int die = stdin.nextInt();
            System.out.print("Choose a x_place: ");  ////stampa la window
            int x = stdin.nextInt();
            System.out.print("Choose a y_place: ");  ////stampa la window
            int y = stdin.nextInt();
            notify(new SetDieMessage(x, y, die).serialize());
        }

    }

    private String getIdMessage(String s){
        JsonReader jsonReader = new JsonReader(new StringReader(s));
        String action;
        try{
            jsonReader.beginObject();
            jsonReader.nextName();
            action = jsonReader.nextString();
            jsonReader.close();
        } catch(IOException e){
            action = "error";
        }
        return action;
    }
}
