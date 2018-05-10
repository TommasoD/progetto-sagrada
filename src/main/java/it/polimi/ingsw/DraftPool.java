package it.polimi.ingsw;
import java.util.ArrayList;

public class DraftPool {


    private ArrayList<Die> draftPool;

    public DraftPool() {
        draftPool = new ArrayList <Die>();
    }

    public void setDieInDraftPool (Die die) {
        draftPool.add(die);
    }

    public int getDraftPoolSize () {
        return draftPool.size();
    }

    public Die getDieFromDraft(int index) {
        Die die;
        die = draftPool.get(index);
        draftPool.remove(die);
        return die;
    }
}
