package it.polimi.ingsw;

import java.awt.*;

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
