package it.polimi.ingsw.windows;
import it.polimi.ingsw.Slot;
import it.polimi.ingsw.WindowPattern;

public class Window5 extends WindowPattern {

    public Window5(){
        name = "Via Lux";
        difficultyToken = 4;
        windowMatrix = new Slot[42];

        for(int i = 0; i < 42; i++) windowMatrix[i] = new Slot("none", "none");

        windowMatrix[8] = new Slot("YELLOW", "none");
        windowMatrix[10] = new Slot("none", "6");

        windowMatrix[16] = new Slot("none", "1");
        windowMatrix[17] = new Slot("none", "5");
        windowMatrix[19] = new Slot("none", "2");

        windowMatrix[22] = new Slot("none", "3");
        windowMatrix[23] = new Slot("YELLOW", "none");
        windowMatrix[24] = new Slot("RED", "none");
        windowMatrix[25] = new Slot("PURPLE", "none");

        windowMatrix[31] = new Slot("none", "4");
        windowMatrix[32] = new Slot("none", "3");
        windowMatrix[33] = new Slot("RED", "none");
    }

}