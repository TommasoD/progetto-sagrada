package it.polimi.ingsw.windows;
import it.polimi.ingsw.Slot;
import it.polimi.ingsw.WindowPattern;

public class Window4 extends WindowPattern {

    public Window4(){
        name = "Virtus";
        difficultyToken = 5;
        windowMatrix = new Slot[42];

        for(int i = 0; i < 42; i++) windowMatrix[i] = new Slot("none", "none");

        windowMatrix[8] = new Slot("none", "4");
        windowMatrix[10] = new Slot("none", "2");
        windowMatrix[11] = new Slot("none", "5");
        windowMatrix[12] = new Slot("GREEN", "none");

        windowMatrix[17] = new Slot("none", "6");
        windowMatrix[18] = new Slot("GREEN", "none");
        windowMatrix[19] = new Slot("none", "2");

        windowMatrix[23] = new Slot("none", "3");
        windowMatrix[24] = new Slot("GREEN", "none");
        windowMatrix[25] = new Slot("none", "4");

        windowMatrix[29] = new Slot("none", "5");
        windowMatrix[30] = new Slot("GREEN", "none");
        windowMatrix[31] = new Slot("none", "1");
    }

}