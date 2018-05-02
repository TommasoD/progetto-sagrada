package it.polimi.ingsw.windows;
import it.polimi.ingsw.Slot;
import it.polimi.ingsw.WindowPattern;

public class Window2 extends WindowPattern {

    public Window2(){
        name = "Aurorae Magnificus";
        difficultyToken = 5;
        windowMatrix = new Slot[42];

        for(int i = 0; i < 42; i++) windowMatrix[i] = new Slot("none", "none");

        windowMatrix[8] = new Slot("none", "5");
        windowMatrix[9] = new Slot("GREEN", "none");
        windowMatrix[10] = new Slot("BLUE", "none");
        windowMatrix[11] = new Slot("PURPLE", "none");
        windowMatrix[12] = new Slot("none", "2");

        windowMatrix[15] = new Slot("PURPLE", "none");
        windowMatrix[19] = new Slot("YELLOW", "none");

        windowMatrix[22] = new Slot("YELLOW", "none");
        windowMatrix[24] = new Slot("none", "6");
        windowMatrix[26] = new Slot("PURPLE", "none");

        windowMatrix[29] = new Slot("none", "1");
        windowMatrix[32] = new Slot("GREEN", "none");
        windowMatrix[33] = new Slot("none", "4");
    }

}