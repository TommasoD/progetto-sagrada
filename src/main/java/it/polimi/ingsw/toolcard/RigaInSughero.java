package it.polimi.ingsw.toolcard;

import it.polimi.ingsw.Die;
import it.polimi.ingsw.WindowPattern;

public class RigaInSughero extends ToolCard {

    public RigaInSughero(){
        super();
        this.name = "Riga in Sughero";
    }

    /*
    *place a die in a slot not adjacent to another die
    */
    public void effect(WindowPattern w, Die d, int x, int y){
        w.getWindowMatrix(x, y).setDie(d);
    }

}
