package it.polimi.ingsw;
import java.util.ArrayList;
import java.util.Random;

public class DiceBag {

    private ArrayList <Die> diceBag;
    private static final String[] colors = {
            "RED",
            "BLUE",
            "YELLOW",
            "GREEN",
            "PURPLE"
    };

    public DiceBag() {
        diceBag = new ArrayList <Die>();
        for(String s: colors){
            for(int i = 0; i < 18; i++) {
                Die d = new Die(s);
                diceBag.add(d);
            }
        }
    }

    public Die getDie() {
        int bound = diceBag.size();
        if(bound == 0) return null;

        Random rand = new Random();
        int i = rand.nextInt(bound);
        Die d = diceBag.get(i);
        diceBag.remove(d);
        return d;
    }

    public int getSize(){
        return diceBag.size();
    }

    public void dump() {
        int size = diceBag.size();
        System.out.println("Dice remaining: " + size);

        for(Die d : diceBag) {
            System.out.println(d);
        }
    }

}