package it.polimi.ingsw.toolcard;

import it.polimi.ingsw.WindowPattern;

public class TaglierinaManuale extends ToolCard {

    public TaglierinaManuale() {
        super();
        this.name = "Taglierina Manuale";
    }

    public void effect (WindowPattern window, int x, int y, int a, int b) {
        window.getWindowMatrix(a, b).setDie(window.getWindowMatrix(x, y).getDie());
        window.getWindowMatrix(x, y).setDie(null);
    }
}