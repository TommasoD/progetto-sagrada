package it.polimi.ingsw.model;
import java.util.ArrayList;
import java.util.Random;

public class DiceBag {

    private ArrayList <Die> bag;
    private static final String[] colors = {
            "RED",
            "BLUE",
            "YELLOW",
            "GREEN",
            "PURPLE"
    };

    /*
        constructor
     */

    public DiceBag() {
        bag = new ArrayList <Die>();
        for(String s: colors){
            for(int i = 0; i < 18; i++) {
                Die d = new Die(s);
                bag.add(d);
            }
        }
    }

    /*
        return random die from the bag, with a random value assigned
     */

    public Die getDie() {
        int bound = bag.size();
        if(bound == 0) return null;

        Random rand = new Random();
        int i = rand.nextInt(bound);
        Die d = bag.get(i);
        d.roll();
        bag.remove(d);
        return d;
    }

    public void addDie(Die d){
        bag.add(d);
    }

    public int getSize(){
        return bag.size();
    }

    /*
        print the class
     */

    public void dump() {
        int size = bag.size();
        System.out.println("Dice remaining: " + size);

        for(Die d : bag) {
            d.setValue(1);
            System.out.println(d);
        }
    }

}