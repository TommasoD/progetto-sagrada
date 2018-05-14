package it.polimi.ingsw.toolcard;

import it.polimi.ingsw.Die;
import it.polimi.ingsw.DraftPool;
import it.polimi.ingsw.toolcard.ToolCard;

public class GrozingPliers extends ToolCard {

    public GrozingPliers() {
        super();
        this.name = "Grozing Pliers";
    }

    public void effect1(DraftPool draft, int i, String move) {

            if(move.equals("increase")) {
                Die die = draft.getDieFromDraft(i);
                draft.getDieFromDraft(i).setValue(die.getValueAsInt() + 1);
            }
            if(move.equals("decrease")) {
                Die die = draft.getDieFromDraft(i);
                draft.getDieFromDraft(i).setValue(die.getValueAsInt() - 1);
            }

    }

}