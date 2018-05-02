package it.polimi.ingsw.windows;
import it.polimi.ingsw.Slot;
import it.polimi.ingsw.WindowPattern;

public class Window20 extends WindowPattern {

    public Window20(){
        name = "Comitas";
        difficultyToken = 5;
        windowMatrix = new Slot[42];

        for(int i = 0; i < 42; i++) windowMatrix[i] = new Slot("none", "none");

        windowMatrix[8] = new Slot("YELLOW", "none");
        windowMatrix[10] = new Slot("none", "2");
        windowMatrix[12] = new Slot("none", "6");

        windowMatrix[16] = new Slot("none", "4");
        windowMatrix[18] = new Slot("none", "5");
        windowMatrix[19] = new Slot("YELLOW", "none");

        windowMatrix[25] = new Slot("YELLOW", "none");
        windowMatrix[26] = new Slot("none", "5");

        windowMatrix[29] = new Slot("none", "1");
        windowMatrix[30] = new Slot("none", "2");
        windowMatrix[31] = new Slot("YELLOW", "none");
        windowMatrix[32] = new Slot("none", "3");
    }

}