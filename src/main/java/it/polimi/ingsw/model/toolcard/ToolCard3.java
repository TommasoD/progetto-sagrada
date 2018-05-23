package it.polimi.ingsw.model.toolcard;

import it.polimi.ingsw.model.WindowPattern;

public class ToolCard3 extends ToolCard {

    /*
        constructor
     */

    public ToolCard3() {
        super();
        this.name = "Copper Foil Burnisher";
    }

    /*
        validateMove checks if the player can effectively use the tool card
        ignores value restrictions
     */

    public boolean validateMove(WindowPattern w, int x, int y, int a, int b) {

        //needs to be implemented

        return true;
    }

}
