package it.polimi.ingsw.model.toolcard;

import it.polimi.ingsw.model.Die;
import it.polimi.ingsw.model.WindowPattern;

public class ToolCard11 extends ToolCard {

    /*
        constructor
     */

    public ToolCard11(){
        super();
        this.name = "Flux Remover";
    }

    /*
        validateMove checks if the player can effectively use the tool card
        basic positioning validation once the player has chosen the value of the die
     */

    public boolean validateMove(WindowPattern w, Die d, int x, int y) {

        //needs to be implemented

        return true;
    }

}
