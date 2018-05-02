package it.polimi.ingsw.windows;
import it.polimi.ingsw.Slot;
import it.polimi.ingsw.WindowPattern;

public class Window14 extends WindowPattern {

    public Window14(){
        name = "Lux Astram";
        difficultyToken = 5;
        windowMatrix = new Slot[42];

        for(int i = 0; i < 42; i++) windowMatrix[i] = new Slot("none", "none");

        windowMatrix[9] = new Slot("none", "1");
        windowMatrix[10] = new Slot("GREEN", "none");
        windowMatrix[11] = new Slot("PURPLE", "none");
        windowMatrix[12] = new Slot("none", "4");

        windowMatrix[15] = new Slot("none", "6");
        windowMatrix[16] = new Slot("PURPLE", "none");
        windowMatrix[17] = new Slot("none", "2");
        windowMatrix[18] = new Slot("none", "5");
        windowMatrix[19] = new Slot("GREEN", "none");

        windowMatrix[22] = new Slot("none", "1");
        windowMatrix[23] = new Slot("GREEN", "none");
        windowMatrix[24] = new Slot("none", "5");
        windowMatrix[25] = new Slot("none", "3");
        windowMatrix[26] = new Slot("PURPLE", "none");
    }

}