package it.polimi.ingsw.windows;
import it.polimi.ingsw.Slot;
import it.polimi.ingsw.WindowPattern;

public class Window21 extends WindowPattern {

    public Window21(){
        name = "Fulgor del Ciel";
        difficultyToken = 5;
        windowMatrix = new Slot[42];

        for(int i = 0; i < 42; i++) windowMatrix[i] = new Slot("none", "none");

        windowMatrix[9] = new Slot("BLUE", "none");
        windowMatrix[10] = new Slot("RED", "none");

        windowMatrix[16] = new Slot("none", "4");
        windowMatrix[17] = new Slot("none", "5");
        windowMatrix[19] = new Slot("BLUE", "none");

        windowMatrix[22] = new Slot("BLUE", "none");
        windowMatrix[23] = new Slot("none", "2");
        windowMatrix[25] = new Slot("RED", "none");
        windowMatrix[26] = new Slot("none", "5");

        windowMatrix[29] = new Slot("none", "6");
        windowMatrix[30] = new Slot("RED", "none");
        windowMatrix[31] = new Slot("none", "3");
        windowMatrix[32] = new Slot("none", "1");
    }

}