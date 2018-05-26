package it.polimi.ingsw.model;

public class WindowReaderTest {

    public static void main(String args[]) {
        Player p = new Player("Luigi");
        String windowName = "window1";

        //read the window
        XMLParserSAX reader = new XMLParserSAX();
        p.setPlayerWindow(reader.readWindowPattern(windowName));

        //show the window
        System.out.println("token: " + p.getPlayerWindow().difficultyToken);
        p.getPlayerWindow().dump();
    }
}
