package it.polimi.ingsw.model.toolcard;

import it.polimi.ingsw.model.Die;
import it.polimi.ingsw.model.WindowPattern;

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
