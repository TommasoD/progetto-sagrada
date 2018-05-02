package it.polimi.ingsw.windows;
import it.polimi.ingsw.Slot;
import it.polimi.ingsw.WindowPattern;

public class Window3 extends WindowPattern {

    public Window3(){
        name = "Sun Catcher";
        difficultyToken = 3;
        windowMatrix = new Slot[42];

        for(int i = 0; i < 42; i++) windowMatrix[i] = new Slot("none", "none");

        windowMatrix[9] = new Slot("BLUE", "none");
        windowMatrix[10] = new Slot("none", "2");
        windowMatrix[12] = new Slot("YELLOW", "none");

        windowMatrix[16] = new Slot("none", "4");
        windowMatrix[18] = new Slot("RED", "none");

        windowMatrix[24] = new Slot("none", "5");
        windowMatrix[25] = new Slot("YELLOW", "none");

        windowMatrix[29] = new Slot("GREEN", "none");
        windowMatrix[30] = new Slot("none", "3");
        windowMatrix[33] = new Slot("PURPLE", "none");
    }

}