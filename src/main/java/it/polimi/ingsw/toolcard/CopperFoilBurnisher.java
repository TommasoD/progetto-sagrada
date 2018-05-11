package it.polimi.ingsw.toolcard;

import it.polimi.ingsw.WindowPattern;
import it.polimi.ingsw.toolcard.ToolCard;

public class CopperFoilBurnisher extends ToolCard {

    public CopperFoilBurnisher() {
        super();
        this.name = "Copper Foil Burnisher";
    }

    public void effect3(WindowPattern window, int x, int y, int a, int b) {

        window.getWindowMatrix(a, b).setDie(window.getWindowMatrix(x, y).getDie());
        window.getWindowMatrix(x, y).setDie(null);

    }

}
