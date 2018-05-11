package it.polimi.ingsw.toolcard;

import it.polimi.ingsw.DraftPool;
import it.polimi.ingsw.toolcard.ToolCard;

public class GlazingHammer extends ToolCard {

    public GlazingHammer() {
        super();
        this.name = "Glazing Hammer";
    }

    public void effect7(DraftPool draft) {

        for(int i = 0; i < draft.getDraftPoolSize(); i++) {
            draft.getDieFromDraft(i).roll();
        }

    }

}
