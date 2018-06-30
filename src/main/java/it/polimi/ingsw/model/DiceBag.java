package it.polimi.ingsw.model;
import java.util.ArrayList;
import java.util.Random;

/**
 * Represents the bag of all the dice in the game. There are 90 dice in total,
 * of 5 different colors: red, blue, yellow, green and purple.
 * Every round a number of dice equal to the number of player multiplied by two, plus one,
 * are taken from the bag to be placed in the draft pool.
 */
public class DiceBag {

    private ArrayList <Die> bag;
    private static final int DICE_OF_EACH_COLOR = 18;
    private static final String[] colors = {
            "RED",
            "BLUE",
            "YELLOW",
            "GREEN",
            "PURPLE"
    };

    /**
     * Class constructor.
     */

    public DiceBag() {
        bag = new ArrayList <>();
        for(String s: colors){
            for(int i = 0; i < DICE_OF_EACH_COLOR; i++) {
                Die d = new Die(s);
                bag.add(d);
            }
        }
    }

    /**
     * Returns a random die from the list of all the dice in the game,
     * with a random value assigned. Values goes from 1 to 6.
     * Once selected, the die is removed from the list, then returned.
     * @return a random die with an assigned value.
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

    /**
     * Adds a die to the list of all dice.
     * @param die a die.
     */

    public void addDie(Die die){
        bag.add(die);
    }

    /**
     * Returns the number of dice left in the bag.
     * @return the number of dice left as an integer.
     */

    public int getSize(){
        return bag.size();
    }

    /**
     * Prints all the dice in the bag, with a default value.
     */

    public void dump() {
        System.out.println("Dice remaining: " + bag.size());
        for(Die die : bag) {
            die.setValue(1);
            System.out.println(die);
        }
    }

}