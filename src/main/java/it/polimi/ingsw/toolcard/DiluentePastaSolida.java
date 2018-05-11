package it.polimi.ingsw.toolcard;

import it.polimi.ingsw.DiceBag;
import it.polimi.ingsw.Die;
import it.polimi.ingsw.WindowPattern;

public class DiluentePastaSolida extends ToolCard {

    public DiluentePastaSolida(){
        super();
        this.name = "Diluente per Pasta Solida";
    }

    /*
    *put a die back in the DiceBag
    */
    public Die backInBag(Die d, DiceBag db){
        db.addDie(d);
        return db.getDie();
    }

    /*
    *get die from bag and set his value
    */
    public void getDieAndSetValue(WindowPattern w, int val, int x, int y, Die d){
        d.setValue(val);
        w.getWindowMatrix(x, y).setDie(d);
    }

}
