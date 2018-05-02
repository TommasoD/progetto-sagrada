package it.polimi.ingsw.windows;
import it.polimi.ingsw.Slot;
import it.polimi.ingsw.WindowPattern;

public class Window10 extends WindowPattern {

    public Window10(){
        name = "Battlo";
        difficultyToken = 5;
        windowMatrix = new Slot[42];

        for(int i = 0; i < 42; i++) windowMatrix[i] = new Slot("none", "none");

        windowMatrix[10] = new Slot("none", "6");

        windowMatrix[16] = new Slot("none", "5");
        windowMatrix[17] = new Slot("BLUE", "none");
        windowMatrix[18] = new Slot("none", "4");

        windowMatrix[22] = new Slot("none", "3");
        windowMatrix[23] = new Slot("GREEN", "none");
        windowMatrix[24] = new Slot("YELLOW", "none");
        windowMatrix[25] = new Slot("PURPLE", "none");
        windowMatrix[26] = new Slot("none", "2");

        windowMatrix[29] = new Slot("none", "1");
        windowMatrix[30] = new Slot("none", "4");
        windowMatrix[31] = new Slot("RED", "none");
        windowMatrix[32] = new Slot("none", "5");
        windowMatrix[33] = new Slot("none", "3");
    }

}