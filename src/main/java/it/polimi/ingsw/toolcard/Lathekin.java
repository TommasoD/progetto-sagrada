package it.polimi.ingsw.toolcard;

import it.polimi.ingsw.WindowPattern;

public class Lathekin extends ToolCard {

    public Lathekin() {
        super();
        this.name = "Lathekin";
    }

    //La funzione va eseguita due volte
    public void effect (WindowPattern window, int x, int y, int a, int b) {
                window.getWindowMatrix(a, b).setDie(window.getWindowMatrix(x, y).getDie());
                window.getWindowMatrix(x, y).setDie(null);
    }
}
