package it.polimi.ingsw.model.toolcard;

import it.polimi.ingsw.model.DraftPool;
import it.polimi.ingsw.model.toolcard.ToolCard;

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
