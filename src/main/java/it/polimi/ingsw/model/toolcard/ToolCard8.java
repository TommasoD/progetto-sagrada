package it.polimi.ingsw.model.toolcard;

import it.polimi.ingsw.model.Die;
import it.polimi.ingsw.model.WindowPattern;

public class ToolCard8 extends ToolCard {

    /*
        constructor
     */

    public ToolCard8(){
        super();
        this.name = "Running Pliers";
    }

    /*
        validateMove checks if the player can effectively use the tool card
        basic positioning validation
     */

    public boolean validateMove(WindowPattern w, Die d, int x, int y) {

        //needs to be implemented

        return true;
    }

}
