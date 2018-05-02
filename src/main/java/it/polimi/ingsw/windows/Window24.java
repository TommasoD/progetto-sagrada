package it.polimi.ingsw.windows;
import it.polimi.ingsw.Slot;
import it.polimi.ingsw.WindowPattern;

public class Window24 extends WindowPattern {

    public Window24(){
        name = "Sun's Glory";
        difficultyToken = 6;
        windowMatrix = new Slot[42];

        for(int i = 0; i < 42; i++) windowMatrix[i] = new Slot("none", "none");

        windowMatrix[8] = new Slot("none", "1");
        windowMatrix[9] = new Slot("PURPLE", "none");
        windowMatrix[10] = new Slot("YELLOW", "none");
        windowMatrix[12] = new Slot("none", "4");

        windowMatrix[15] = new Slot("PURPLE", "none");
        windowMatrix[16] = new Slot("YELLOW", "none");
        windowMatrix[19] = new Slot("none", "6");

        windowMatrix[22] = new Slot("YELLOW", "none");
        windowMatrix[25] = new Slot("none", "5");
        windowMatrix[26] = new Slot("none", "3");

        windowMatrix[30] = new Slot("none", "5");
        windowMatrix[31] = new Slot("none", "4");
        windowMatrix[32] = new Slot("none", "2");
        windowMatrix[33] = new Slot("none", "1");
    }

}