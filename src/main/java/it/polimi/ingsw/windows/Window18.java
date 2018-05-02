package it.polimi.ingsw.windows;
import it.polimi.ingsw.Slot;
import it.polimi.ingsw.WindowPattern;

public class Window18 extends WindowPattern {

    public Window18(){
        name = "Fractal Drops";
        difficultyToken = 3;
        windowMatrix = new Slot[42];

        for(int i = 0; i < 42; i++) windowMatrix[i] = new Slot("none", "none");

        windowMatrix[9] = new Slot("none", "4");
        windowMatrix[11] = new Slot("YELLOW", "none");
        windowMatrix[12] = new Slot("none", "6");

        windowMatrix[15] = new Slot("RED", "none");
        windowMatrix[17] = new Slot("none", "2");

        windowMatrix[24] = new Slot("RED", "none");
        windowMatrix[25] = new Slot("PURPLE", "none");
        windowMatrix[26] = new Slot("none", "1");

        windowMatrix[29] = new Slot("BLUE", "none");
        windowMatrix[30] = new Slot("YELLOW", "none");
    }

}