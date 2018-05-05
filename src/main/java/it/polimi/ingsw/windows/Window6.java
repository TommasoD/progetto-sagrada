package it.polimi.ingsw.windows;
import it.polimi.ingsw.Slot;
import it.polimi.ingsw.WindowPattern;

public class Window6 extends WindowPattern {

    public Window6(){
        name = "Bellesguard";
        difficultyToken = 3;
        windowMatrix = new Slot[5][4];

        windowMatrix[0][0] = new Slot("BLUE", "none");
        windowMatrix[1][0] = new Slot("none", "6");
        windowMatrix[2][0] = new Slot("none", "none");
        windowMatrix[3][0] = new Slot("none", "none");
        windowMatrix[4][0] = new Slot("YELLOW", "none");

        windowMatrix[0][1] = new Slot("none", "none");
        windowMatrix[1][1] = new Slot("none", "3");
        windowMatrix[2][1] = new Slot("BLUE", "none");
        windowMatrix[3][1] = new Slot("none", "none");
        windowMatrix[4][1] = new Slot("none", "none");

        windowMatrix[0][2] = new Slot("none", "none");
        windowMatrix[1][2] = new Slot("none", "5");
        windowMatrix[2][2] = new Slot("none", "6");
        windowMatrix[3][2] = new Slot("none", "2");
        windowMatrix[4][2] = new Slot("none", "none");

        windowMatrix[0][3] = new Slot("none", "none");
        windowMatrix[1][3] = new Slot("none", "4");
        windowMatrix[2][3] = new Slot("none", "none");
        windowMatrix[3][3] = new Slot("none", "1");
        windowMatrix[4][3] = new Slot("GREEN", "none");
    }

}