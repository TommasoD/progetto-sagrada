package it.polimi.ingsw.model;
import java.util.Random;

/**
 * Represents a die. Each die has a color and a value: values can change during
 * the course of the game, whereas colors are assigned when the die is created.
 */
public class Die {

    private String color;
    private String value;
    private static final String[] values = {
            "1",
            "2",
            "3",
            "4",
            "5",
            "6"
    };

    /**
     * Class constructor specifying the color of the die.
     * @param color the color of the die.
     */

    public Die(String color) {
        this.color = color;
    }

    /**
     * Class constructor specifying the color of the die and a value as well.
     * @param color the color of the die.
     * @param value the value.
     */

    public Die(String color, String value){
        this.color = color;
        this.value = value;
    }

    /**
     *
     * @param color the color of the die.
     */

    public void setColor(String color) {
        this.color = color;
    }

    /**
     *
     * @return the color of the die.
     */

    public String getColor() {
        return color;
    }

    /**
     *
     * @param value the value of the die as a String.
     */

    public void setValue(String value) {
        this.value = value;
    }

    /**
     *
     * @param value the value of the die as an integer.
     */

    public void setValue(int value) {
        if(value == 1) this.value = "1";
        if(value == 2) this.value = "2";
        if(value == 3) this.value = "3";
        if(value == 4) this.value = "4";
        if(value == 5) this.value = "5";
        if(value == 6) this.value = "6";
    }

    /**
     *
     * @return the value of the die as a String.
     */

    public String getValue() {
        return value;
    }

    /**
     * Assigns a random value from 1 to 6.
     */

    public void roll() {
        Random rand = new Random();
        int bound = values.length;
        int i = rand.nextInt(bound);
        this.value = values[i];
    }

    /**
     * Flips a die, i.e. changes the value to the one on the opposite face.
     */

    public void flipDie() {
        this.setValue(7 - this.getValueAsInt());
    }

    /**
     * Increases the value of the die by one.
     * The value is not increased in case it's already 6.
     */

    public void increaseValue() {
        if (this.getValueAsInt()!=6) this.setValue(this.getValueAsInt()+1);
    }

    /**
     * Decreases the value of the die by one.
     * The value is not decreased in case it's already 1.
     */

    public void decreaseValue() {
        if (this.getValueAsInt()!=1) this.setValue(this.getValueAsInt()-1);
    }

    /**
     * Returns the value of the die as an integer.
     * @return the value of the die as an integer.
     */

    public int getValueAsInt() {
        if(value.equals("1")) return 1;
        if(value.equals("2")) return 2;
        if(value.equals("3")) return 3;
        if(value.equals("4")) return 4;
        if(value.equals("5")) return 5;
        if(value.equals("6")) return 6;
        return -1;
    }

    /**
     * Returns the color of the die as an integer:
     * RED as 0, GREEN as 1, BLUE as 2, YELLOW as 3, PURPLE as 4.
     * @return the color of the die as an integer.
     */

    public int getColorAsInt() {
        if(color.equals("RED")) return 0;
        if(color.equals("GREEN")) return 1;
        if(color.equals("BLUE")) return 2;
        if(color.equals("YELLOW")) return 3;
        if(color.equals("PURPLE")) return 4;
        return -1;
    }

    /**
     * Converts the value to a Unicode Character representing the face of the die.
     * @return the Unicode representation of the face of the die.
     */

    private String valueToString(){
        if (value.equals("1")) return "\u2680";
        if (value.equals("2")) return "\u2681";
        if (value.equals("3")) return "\u2682";
        if (value.equals("4")) return "\u2683";
        if (value.equals("5")) return "\u2684";
        if (value.equals("6")) return "\u2685";
        return "\u25a1";
    }

    /**
     * Converts the color to a code that changes the color of the output.
     * @return the code representing the color.
     */

    private String colorToString() {
        if (color.equals("RED")) return "\u001B[31m";
        if (color.equals("GREEN")) return "\u001B[32m";
        if (color.equals("YELLOW")) return "\u001B[33m";
        if (color.equals("BLUE")) return "\u001B[34m";
        if (color.equals("PURPLE")) return "\u001B[35m";
        return "\u001B[0m";
    }

    /**
     * Returns the String representation of the class.
     * @return the String representation of the class.
     */

    @Override
    public String toString() {
        return colorToString() + valueToString();
    }

    /**
     * Prints the class.
     */

    void dump(){
        System.out.println(this);
    }

}