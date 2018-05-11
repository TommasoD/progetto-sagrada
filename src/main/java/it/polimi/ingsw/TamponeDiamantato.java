package it.polimi.ingsw;

public class TamponeDiamantato extends ToolCard {

    public TamponeDiamantato() {
        super();
        this.name = "Tampone Diamantato";
    }

    public void effect(DraftPool draftPool, int i) {
        int newValue;
        newValue = 7 - draftPool.getDieFromDraft(i).getValueAsInt();
        draftPool.getDieFromDraft(i).setValue(newValue);
    }
}
