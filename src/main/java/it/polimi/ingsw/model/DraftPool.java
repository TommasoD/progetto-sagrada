package it.polimi.ingsw.model;
import java.util.ArrayList;

public class DraftPool {

    private ArrayList<Die> draft;

    public DraftPool() {
        draft = new ArrayList <Die>();
    }

    public void setDieInDraftPool (Die die) {
        draft.add(die);
    }

    public int getDraftPoolSize () {
        return draft.size();
    }

    public Die getDieFromDraft(int index) {
        return draft.get(index);
    }

    public Die removeDieFromDraft(int index) {
        Die die;
        die = draft.get(index);
        draft.remove(die);
        return die;
    }

    public void dump() {
        int size = draft.size();
        System.out.println("Dice remaining: " + size);

        for(Die d : draft) {
            d.roll();
            System.out.println(d);
        }
    }
}
