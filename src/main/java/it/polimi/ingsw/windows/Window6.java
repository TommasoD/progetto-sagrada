package it.polimi.ingsw.windows;
import it.polimi.ingsw.Slot;
import it.polimi.ingsw.WindowPattern;

public class Window6 extends WindowPattern {

    public Window6(){
        name = "Bellesguard";
        difficultyToken = 3;
        windowMatrix = new Slot[42];

        for(int i = 0; i < 42; i++) windowMatrix[i] = new Slot("none", "none");

        windowMatrix[8] = new Slot("BLUE", "none");
        windowMatrix[9] = new Slot("none", "6");
        windowMatrix[12] = new Slot("YELLOW", "none");

        windowMatrix[16] = new Slot("none", "3");
        windowMatrix[17] = new Slot("BLUE", "none");

        windowMatrix[23] = new Slot("none", "5");
        windowMatrix[24] = new Slot("none", "6");
        windowMatrix[25] = new Slot("none", "2");

        windowMatrix[30] = new Slot("none", "4");
        windowMatrix[32] = new Slot("none", "1");
        windowMatrix[33] = new Slot("GREEN", "none");
    }

}