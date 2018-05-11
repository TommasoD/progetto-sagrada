package it.polimi.ingsw.toolcard;

import it.polimi.ingsw.Die;
import it.polimi.ingsw.WindowPattern;

public class TenagliaARotelle extends ToolCard {

    public TenagliaARotelle(){
        super();
        this.name = "Tenaglia a Rotelle";
    }

    /*
    *after your first turn,
    *choose one more die and place it on your window
    *end your round
    */
    public void effect(WindowPattern w, Die d, int x, int y){
        w.getWindowMatrix(x, y).setDie(d);
    }
}
