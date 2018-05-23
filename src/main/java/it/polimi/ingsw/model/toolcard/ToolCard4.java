package it.polimi.ingsw.model.toolcard;

import it.polimi.ingsw.model.WindowPattern;

public class ToolCard4 extends ToolCard {

    /*
        constructor
     */

    public ToolCard4() {
        super();
        this.name = "Lathekin";
    }

    /*
        validateMove checks if the player can effectively use the tool card
        it validates a single movement: die in (x,y) to (a,b)
     */

    public boolean validateMove(WindowPattern w, int x, int y, int a, int b) {

        //needs to be implemented

        return true;
    }

}
