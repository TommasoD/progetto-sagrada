package it.polimi.ingsw.windows;
import it.polimi.ingsw.Slot;
import it.polimi.ingsw.WindowPattern;

public class Window8 extends WindowPattern {

    public Window8(){
        name = "Aurora Sagradis";
        difficultyToken = 4;
        windowMatrix = new Slot[42];

        for(int i = 0; i < 42; i++) windowMatrix[i] = new Slot("none", "none");

        windowMatrix[8] = new Slot("RED", "none");
        windowMatrix[10] = new Slot("BLUE", "none");
        windowMatrix[12] = new Slot("YELLOW", "none");

        windowMatrix[15] = new Slot("none", "4");
        windowMatrix[16] = new Slot("PURPLE", "none");
        windowMatrix[17] = new Slot("none", "3");
        windowMatrix[18] = new Slot("GREEN", "none");
        windowMatrix[19] = new Slot("none", "2");

        windowMatrix[23] = new Slot("none", "1");
        windowMatrix[25] = new Slot("none", "5");

        windowMatrix[31] = new Slot("none", "6");
    }

}