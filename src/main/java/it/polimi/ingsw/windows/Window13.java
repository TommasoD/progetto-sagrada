package it.polimi.ingsw.windows;
import it.polimi.ingsw.Slot;
import it.polimi.ingsw.WindowPattern;

public class Window13 extends WindowPattern {

    public Window13(){
        name = "Firelight";
        difficultyToken = 5;
        windowMatrix = new Slot[42];

        for(int i = 0; i < 42; i++) windowMatrix[i] = new Slot("none", "none");

        windowMatrix[8] = new Slot("none", "3");
        windowMatrix[9] = new Slot("none", "4");
        windowMatrix[10] = new Slot("none", "1");
        windowMatrix[11] = new Slot("none", "5");

        windowMatrix[16] = new Slot("none", "6");
        windowMatrix[17] = new Slot("none", "2");
        windowMatrix[19] = new Slot("YELLOW", "none");

        windowMatrix[25] = new Slot("YELLOW", "none");
        windowMatrix[26] = new Slot("RED", "none");

        windowMatrix[29] = new Slot("none", "5");
        windowMatrix[31] = new Slot("YELLOW", "none");
        windowMatrix[32] = new Slot("RED", "none");
        windowMatrix[33] = new Slot("none", "6");
    }

}