package it.polimi.ingsw.windows;
import it.polimi.ingsw.Slot;
import it.polimi.ingsw.WindowPattern;

public class Window15 extends WindowPattern {

    public Window15(){
        name = "Gravitas";
        difficultyToken = 5;
        windowMatrix = new Slot[42];

        for(int i = 0; i < 42; i++) windowMatrix[i] = new Slot("none", "none");

        windowMatrix[8] = new Slot("none", "1");
        windowMatrix[10] = new Slot("none", "3");
        windowMatrix[11] = new Slot("BLUE", "none");

        windowMatrix[16] = new Slot("none", "2");
        windowMatrix[17] = new Slot("BLUE", "none");

        windowMatrix[22] = new Slot("none", "6");
        windowMatrix[23] = new Slot("BLUE", "none");
        windowMatrix[25] = new Slot("none", "4");

        windowMatrix[29] = new Slot("BLUE", "none");
        windowMatrix[30] = new Slot("none", "5");
        windowMatrix[31] = new Slot("none", "2");
        windowMatrix[33] = new Slot("none", "1");
    }

}