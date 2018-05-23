package it.polimi.ingsw.model.toolcard;

import it.polimi.ingsw.model.Die;

public class ToolCard1 extends ToolCard {

    /*
        constructor
     */

    public ToolCard1() {
        super();
        this.name = "Grozing Pliers";
    }

    /*
        validateMove checks if the player can effectively use the tool card
        invalid move if action is 6 to 1, or 1 to 6
     */

    public boolean validateMove(Die die, String action) {

        //needs to be implemented

        return true;
    }

}
