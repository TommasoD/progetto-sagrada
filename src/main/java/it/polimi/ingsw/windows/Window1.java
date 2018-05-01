package it.polimi.ingsw.windows;
import it.polimi.ingsw.Slot;
import it.polimi.ingsw.WindowPattern;

public class Window1 extends WindowPattern {

    public Window1(){
        name = "Kaleidoscopic Dream";
        difficultyToken = 4;
        windowMatrix = new Slot[42];

        for(int i = 0; i < 7; i++) windowMatrix[i] = new Slot("none", "none");
        for(int i = 7; i < 35; i+=7) windowMatrix[i] = new Slot("none", "none");
        for(int i = 13; i < 41; i+=7) windowMatrix[i] = new Slot("none", "none");
        for(int i = 35; i < 42; i++) windowMatrix[i] = new Slot("none", "none");

        windowMatrix[8] = new Slot("YELLOW", "none");
        windowMatrix[9] = new Slot("BLUE", "none");
        windowMatrix[10] = new Slot("none", "none");
        windowMatrix[11] = new Slot("none", "none");
        windowMatrix[12] = new Slot("none", "1");

        windowMatrix[15] = new Slot("GREEN", "none");
        windowMatrix[16] = new Slot("none", "none");
        windowMatrix[17] = new Slot("none", "5");
        windowMatrix[18] = new Slot("none", "none");
        windowMatrix[19] = new Slot("none", "4");

        windowMatrix[22] = new Slot("none", "3");
        windowMatrix[23] = new Slot("none", "none");
        windowMatrix[24] = new Slot("RED", "none");
        windowMatrix[25] = new Slot("none", "none");
        windowMatrix[26] = new Slot("none", "GREEN");

        windowMatrix[29] = new Slot("none", "2");
        windowMatrix[30] = new Slot("none", "none");
        windowMatrix[31] = new Slot("none", "none");
        windowMatrix[32] = new Slot("BLUE", "none");
        windowMatrix[33] = new Slot("YELLOW", "none");
    }

}