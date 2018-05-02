package it.polimi.ingsw.windows;
import it.polimi.ingsw.Slot;
import it.polimi.ingsw.WindowPattern;

public class Window23 extends WindowPattern {

    public Window23(){
        name = "Lux Mundi";
        difficultyToken = 6;
        windowMatrix = new Slot[42];

        for(int i = 0; i < 42; i++) windowMatrix[i] = new Slot("none", "none");

        windowMatrix[10] = new Slot("none", "1");

        windowMatrix[15] = new Slot("none", "1");
        windowMatrix[16] = new Slot("GREEN", "none");
        windowMatrix[17] = new Slot("none", "3");
        windowMatrix[18] = new Slot("BLUE", "none");
        windowMatrix[19] = new Slot("none", "2");

        windowMatrix[22] = new Slot("BLUE", "none");
        windowMatrix[23] = new Slot("none", "5");
        windowMatrix[24] = new Slot("none", "4");
        windowMatrix[25] = new Slot("none", "6");
        windowMatrix[26] = new Slot("GREEN", "none");

        windowMatrix[30] = new Slot("BLUE", "none");
        windowMatrix[31] = new Slot("none", "5");
        windowMatrix[32] = new Slot("GREEN", "none");
    }

}