package it.polimi.ingsw.model.toolcard;

import it.polimi.ingsw.model.WindowPattern;

public class ToolCard12 extends ToolCard {

    /*
        constructor
     */

    public ToolCard12() {
        super();
        this.name = "Tap Wheel";
    }

    /*
        validateMove checks if the player can effectively use the tool card
        checks the single movement (not both)
     */

    public boolean validateMove(WindowPattern w, int x, int y, int a, int b) {

        //needs to be implemented

        return true;
    }

}
