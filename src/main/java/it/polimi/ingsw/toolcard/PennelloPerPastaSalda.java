package it.polimi.ingsw.toolcard;


import it.polimi.ingsw.DraftPool;
import it.polimi.ingsw.WindowPattern;

public class PennelloPerPastaSalda extends ToolCard {

    public PennelloPerPastaSalda() {
        super();
        this.name = "Pennello per Pasta Salda";
    }

    public void effect(DraftPool draftPool, WindowPattern window, int x, int y) {
            window.getWindowMatrix(x, y).getDie().roll();
    }

}
