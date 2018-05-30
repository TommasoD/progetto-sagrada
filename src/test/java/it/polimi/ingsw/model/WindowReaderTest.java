package it.polimi.ingsw.model;

import it.polimi.ingsw.model.parsers.WindowParser;

public class WindowReaderTest {

    public static void main(String args[]) {
        Player p = new Player("Luigi");
        String windowName = "window1";

        //read the window
        WindowParser reader = new WindowParser();
        p.setPlayerWindow(reader.readWindowPattern(windowName));

        //show the window
        System.out.println("token: " + p.getPlayerWindow().difficultyToken);
        p.getPlayerWindow().dump();
    }
}
