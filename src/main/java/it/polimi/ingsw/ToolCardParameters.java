package it.polimi.ingsw;

public class ToolCardParameters {

    private WindowPattern window;   /////per CopperFoilBurnisher
    private DraftPool draft;       /////per GrozingPliers
    private int x;         /////per CopperFoilBurnisher
    private int y;          /////per CopperFoilBurnisher
    private int a;         /////per CopperFoilBurnisher
    private int b;         /////per CopperFoilBurnisher
    private int i;         /////per GrozingPliers
    private String move;      /////per GrozingPliers

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

    public String getMove() {
        return this.move;
    }



}
