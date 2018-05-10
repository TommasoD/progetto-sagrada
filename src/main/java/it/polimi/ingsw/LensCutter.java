package it.polimi.ingsw;

public class LensCutter extends ToolCard {

    public void effect(ToolCardParameters p) {
        Die die1 = p.getDieFromDraft();
        Die die2 = p.getDieFromTrack();
        p.removeDieFromDraft();
        p.removeDieFromTrack();
        p.setDieInTrack(die1);
        p.setDieInDraftPool(die2);
    }

}
