package it.polimi.ingsw.windows;
import it.polimi.ingsw.Slot;
import it.polimi.ingsw.WindowPattern;

public class Window16 extends WindowPattern {

    public Window16(){
        name = "Luz Celestial";
        difficultyToken = 3;
        windowMatrix = new Slot[42];

        for(int i = 0; i < 42; i++) windowMatrix[i] = new Slot("none", "none");

        windowMatrix[10] = new Slot("RED", "none");
        windowMatrix[11] = new Slot("none", "5");

        windowMatrix[15] = new Slot("PURPLE", "none");
        windowMatrix[16] = new Slot("none", "4");
        windowMatrix[18] = new Slot("GREEN", "none");
        windowMatrix[19] = new Slot("none", "3");

        windowMatrix[22] = new Slot("none", "6");
        windowMatrix[25] = new Slot("BLUE", "none");

        windowMatrix[30] = new Slot("YELLOW", "none");
        windowMatrix[31] = new Slot("none", "2");
    }

}