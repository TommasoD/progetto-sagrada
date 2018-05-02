package it.polimi.ingsw.windows;
import it.polimi.ingsw.Slot;
import it.polimi.ingsw.WindowPattern;

public class Window11 extends WindowPattern {

    public Window11(){
        name = "Industria";
        difficultyToken = 5;
        windowMatrix = new Slot[42];

        for(int i = 0; i < 42; i++) windowMatrix[i] = new Slot("none", "none");

        windowMatrix[8] = new Slot("none", "1");
        windowMatrix[9] = new Slot("RED", "none");
        windowMatrix[10] = new Slot("none", "3");
        windowMatrix[12] = new Slot("none", "6");

        windowMatrix[15] = new Slot("none", "5");
        windowMatrix[16] = new Slot("none", "4");
        windowMatrix[17] = new Slot("RED", "none");
        windowMatrix[18] = new Slot("none", "2");

        windowMatrix[24] = new Slot("none", "5");
        windowMatrix[25] = new Slot("RED", "none");
        windowMatrix[26] = new Slot("none", "1");

        windowMatrix[32] = new Slot("none", "3");
        windowMatrix[33] = new Slot("RED", "none");
    }

}