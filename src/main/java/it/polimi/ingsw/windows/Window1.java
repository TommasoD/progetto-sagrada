package it.polimi.ingsw.windows;
import it.polimi.ingsw.Slot;
import it.polimi.ingsw.WindowPattern;

public class Window1 extends WindowPattern {

    public Window1(){
        name = "Kaleidoscopic Dream";
        difficultyToken = 4;
        windowMatrix = new Slot[42];

        for(int i = 0; i < 42; i++) windowMatrix[i] = new Slot("none", "none");

        windowMatrix[8] = new Slot("YELLOW", "none");
        windowMatrix[9] = new Slot("BLUE", "none");
        windowMatrix[12] = new Slot("none", "1");

        windowMatrix[15] = new Slot("GREEN", "none");
        windowMatrix[17] = new Slot("none", "5");
        windowMatrix[19] = new Slot("none", "4");

        windowMatrix[22] = new Slot("none", "3");
        windowMatrix[24] = new Slot("RED", "none");
        windowMatrix[26] = new Slot("GREEN", "none");

        windowMatrix[29] = new Slot("none", "2");
        windowMatrix[32] = new Slot("BLUE", "none");
        windowMatrix[33] = new Slot("YELLOW", "none");
    }

}