package it.polimi.ingsw.model.toolcard;

public class ToolCard5 extends ToolCard {

    /*
        constructor
     */

    public ToolCard5() {
        super();
        this.name = "Lens Cutter";
    }

    /*
        validateMove checks if the player can effectively use the tool card
        always valid if indexes are correct
     */

    public boolean validateMove(int draftIndex, int trackIndex) {

        //needs to be implemented

        return true;
    }

}
