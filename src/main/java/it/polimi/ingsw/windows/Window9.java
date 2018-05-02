package it.polimi.ingsw.windows;
import it.polimi.ingsw.Slot;
import it.polimi.ingsw.WindowPattern;

public class Window9 extends WindowPattern {

    public Window9(){
        name = "Firmitas";
        difficultyToken = 5;
        windowMatrix = new Slot[42];

        for(int i = 0; i < 42; i++) windowMatrix[i] = new Slot("none", "none");

        windowMatrix[8] = new Slot("PURPLE", "none");
        windowMatrix[9] = new Slot("none", "6");
        windowMatrix[12] = new Slot("none", "3");

        windowMatrix[15] = new Slot("none", "5");
        windowMatrix[16] = new Slot("PURPLE", "none");
        windowMatrix[17] = new Slot("none", "3");

        windowMatrix[23] = new Slot("none", "2");
        windowMatrix[24] = new Slot("PURPLE", "none");
        windowMatrix[25] = new Slot("none", "1");

        windowMatrix[30] = new Slot("none", "1");
        windowMatrix[31] = new Slot("none", "5");
        windowMatrix[32] = new Slot("PURPLE", "none");
        windowMatrix[33] = new Slot("none", "4");
    }

}