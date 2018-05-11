package it.polimi.ingsw;

import java.util.ArrayList;

public class LensCutter extends ToolCard {

    public LensCutter() {
        super();
        this.name = "Lens Cutter";
    }

    public void effect5(DraftPool draft, ArrayList<Die> roundTrack, int i, int j) {
        Die die1 = draft.removeDieFromDraft(i);
        Die die2 = roundTrack.remove(j);
        roundTrack.add(die1);
        draft.setDieInDraftPool(die2);
    }

}
