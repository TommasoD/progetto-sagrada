package it.polimi.ingsw.model.toolcard;

import it.polimi.ingsw.model.WindowPattern;

public class ToolCard2 extends ToolCard {

    /*
        constructor
     */

    public ToolCard2() {
        super();
        this.name = "Eglomise Brush";
    }

    /*
        validateMove checks if the player can effectively use the tool card
        ignores color restriction
     */

    public boolean validateMove(WindowPattern w, int x, int y, int a, int b) {

        //needs to be implemented

        return true;
    }

}
