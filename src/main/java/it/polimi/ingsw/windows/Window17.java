package it.polimi.ingsw.windows;
import it.polimi.ingsw.Slot;
import it.polimi.ingsw.WindowPattern;

public class Window17 extends WindowPattern {

    public Window17(){
        name = "Chromatic Splendor";
        difficultyToken = 4;
        windowMatrix = new Slot[42];

        for(int i = 0; i < 42; i++) windowMatrix[i] = new Slot("none", "none");

        windowMatrix[10] = new Slot("GREEN", "none");

        windowMatrix[15] = new Slot("none", "2");
        windowMatrix[16] = new Slot("YELLOW", "none");
        windowMatrix[17] = new Slot("none", "5");
        windowMatrix[18] = new Slot("BLUE", "none");
        windowMatrix[19] = new Slot("none", "1");

        windowMatrix[23] = new Slot("RED", "none");
        windowMatrix[24] = new Slot("none", "3");
        windowMatrix[25] = new Slot("PURPLE", "none");

        windowMatrix[29] = new Slot("none", "1");
        windowMatrix[31] = new Slot("none", "6");
        windowMatrix[33] = new Slot("none", "4");
    }

}