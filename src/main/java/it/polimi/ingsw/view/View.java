package it.polimi.ingsw.view;

import com.google.gson.stream.JsonReader;
import it.polimi.ingsw.messages.client.ErrorMessage;
import it.polimi.ingsw.messages.controller.LoginMessage;
import it.polimi.ingsw.messages.controller.SetDieMessage;
import it.polimi.ingsw.messages.client.ShowWindowsMessage;
import it.polimi.ingsw.utils.Observable;
import it.polimi.ingsw.server.TooManyPlayersException;

import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;


public class View extends Observable{

    //View observes Client

    private Scanner stdin = new Scanner(System.in);

    public void firstPrint(String message) {
        System.out.println(message + "\nInsert username: ");
        String inputLine = stdin.nextLine();
        LoginMessage gson = new LoginMessage(inputLine);
        notify(gson.serialize());
    }


    public void print(String message) throws TooManyPlayersException {

        String id = getIdMessage(message);

        //errors
        if(id.equals("ok")){
            System.out.println("Welcome to the game.");
        }
        if(id.equals("error")) {
            ErrorMessage gson = new ErrorMessage();
            gson = gson.deserialize(message);
            if (gson.getType() == 3) {
                System.out.println("Game already started!\nConnection closed");
                throw new TooManyPlayersException();
            }
            if (gson.getType() == 1) {
                System.out.println("Username already used. Insert a new one:");
                String inputLine = stdin.nextLine();
                LoginMessage mex = new LoginMessage(inputLine);
                notify(mex.serialize());
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
            notify(line);
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
                /*System.out.println("Choose a Tool Card: ");
                int toolCardId = stdin.nextInt();
                notify(toolCardId);*/
            }
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
