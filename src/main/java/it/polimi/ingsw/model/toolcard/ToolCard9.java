package it.polimi.ingsw.model.toolcard;

import it.polimi.ingsw.model.Die;
import it.polimi.ingsw.model.WindowPattern;

public class ToolCard9 extends ToolCard {

    /*
        constructor
     */

    public ToolCard9(){
        super();
        this.name = "Cork-backed Straightedge";
    }

    /*
        validateMove checks if the player can effectively use the tool card
        valid only if die is placed not adjacent to another die
     */

    public boolean validateMove(WindowPattern w, Die d, int x, int y) {

        //needs to be implemented

        return true;
    }

}
