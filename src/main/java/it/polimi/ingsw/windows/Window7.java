package it.polimi.ingsw.windows;
import it.polimi.ingsw.Slot;
import it.polimi.ingsw.WindowPattern;

public class Window7 extends WindowPattern {

    public Window7(){
        name = "Shadow Thief";
        difficultyToken = 5;
        windowMatrix = new Slot[42];

        for(int i = 0; i < 42; i++) windowMatrix[i] = new Slot("none", "none");

        windowMatrix[8] = new Slot("none", "6");
        windowMatrix[11] = new Slot("PURPLE", "none");
        windowMatrix[12] = new Slot("none", "5");

        windowMatrix[15] = new Slot("none", "5");
        windowMatrix[17] = new Slot("PURPLE", "none");

        windowMatrix[22] = new Slot("RED", "none");
        windowMatrix[23] = new Slot("none", "6");
        windowMatrix[25] = new Slot("PURPLE", "none");

        windowMatrix[29] = new Slot("YELLOW", "none");
        windowMatrix[30] = new Slot("RED", "none");
        windowMatrix[31] = new Slot("none", "5");
        windowMatrix[32] = new Slot("none", "4");
        windowMatrix[33] = new Slot("none", "3");
    }

}