package it.polimi.ingsw.model.windows;
import it.polimi.ingsw.model.Slot;
import it.polimi.ingsw.model.WindowPattern;

public class Window7 extends WindowPattern {

    public Window7(){
        name = "Shadow Thief";
        difficultyToken = 5;
        windowMatrix = new Slot[5][4];

        windowMatrix[0][0] = new Slot("none", "6");
        windowMatrix[1][0] = new Slot("PURPLE", "none");
        windowMatrix[2][0] = new Slot("none", "none");
        windowMatrix[3][0] = new Slot("none", "none");
        windowMatrix[4][0] = new Slot("none", "5");

        windowMatrix[0][1] = new Slot("none", "5");
        windowMatrix[1][1] = new Slot("none", "none");
        windowMatrix[2][1] = new Slot("PURPLE", "none");
        windowMatrix[3][1] = new Slot("none", "none");
        windowMatrix[4][1] = new Slot("none", "none");

        windowMatrix[0][2] = new Slot("RED", "none");
        windowMatrix[1][2] = new Slot("none", "6");
        windowMatrix[2][2] = new Slot("none", "none");
        windowMatrix[3][2] = new Slot("PURPLE", "none");
        windowMatrix[4][2] = new Slot("none", "none");

        windowMatrix[0][3] = new Slot("YELLOW", "none");
        windowMatrix[1][3] = new Slot("RED", "none");
        windowMatrix[2][3] = new Slot("none", "5");
        windowMatrix[3][3] = new Slot("none", "4");
        windowMatrix[4][3] = new Slot("none", "3");
    }

}