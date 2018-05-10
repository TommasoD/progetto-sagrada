package it.polimi.ingsw;

import java.util.ArrayList;

public class ToolCardParameters {

    private WindowPattern window;   /////per CopperFoilBurnisher
    private DraftPool draft;       /////per GrozingPliers e LensCutter
    private int x;         /////per CopperFoilBurnisher
    private int y;          /////per CopperFoilBurnisher
    private int a;         /////per CopperFoilBurnisher
    private int b;         /////per CopperFoilBurnisher
    private int i;         /////per GrozingPliers
    private String move;      /////per GrozingPliers
    private ArrayList<Die> roundTrack;   //////LensCutter

    public ToolCardParameters(WindowPattern window, int x, int y, int a, int b) {
        this.window = window;
        this.x = x;
        this.y = y;
        this.a = a;
        this.b = b;
    }

    public Slot getInputSlot() {
        return window.getWindowMatrix(x, y);
    }

    public Slot getOutputSlot() {
        return window.getWindowMatrix(a, b);
    }

    public ToolCardParameters(DraftPool draft, int i, String move) {
        this.draft = draft;
        this.i = i;
        this.move = move;
    }

    public Die getDieFromDraft() {
        return draft.getDieFromDraft(i);
    }

    public Die getDieFromDraft(int index) {
        return draft.getDieFromDraft(index);
    }

    public String getMove() {
        return this.move;
    }

    public ToolCardParameters(DraftPool draft, ArrayList<Die> roundTrack, int i, int y) {
        this.draft = draft;
        this.roundTrack = roundTrack;
        this.i = i;
        this.y = y;
    }

    public void removeDieFromDraft() {
        draft.removeDieFromDraft(i);
    }

    public void setDieInDraftPool(Die die) {
        draft.setDieInDraftPool(die);
    }

    public Die getDieFromTrack() {
        return roundTrack.get(y);
    }

    public void removeDieFromTrack() {
        roundTrack.remove(y);
    }

    public void setDieInTrack(Die die) {
        roundTrack.add(die);
    }

}
