package it.polimi.ingsw;
import java.util.ArrayList;
import java.util.Random;

public class DiceBag {

    private ArrayList <Die> diceBag;

    public DiceBag() {
        diceBag = new ArrayList <Die>();
        for(int i = 0; i < 18; i++) {
            Die d = new Die("RED");
            diceBag.add(d);
        }
        for(int i = 0; i < 18; i++) {
            Die d = new Die("BLUE");
            diceBag.add(d);
        }
        for(int i = 0; i < 18; i++) {
            Die d = new Die("PURPLE");
            diceBag.add(d);
        }
        for(int i = 0; i < 18; i++) {
            Die d = new Die("YELLOW");
            diceBag.add(d);
        }
        for(int i = 0; i < 18; i++) {
            Die d = new Die("GREEN");
            diceBag.add(d);
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