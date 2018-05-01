package it.polimi.ingsw;

public class Slot {

    private Die die;
    private String colorRule;
    private String valueRule;

    public Slot(String color, String value) {
        colorRule = color;
        valueRule = value;
    }

    public boolean isNotEmpty() {
        return die != null;
    }

    public Die getDie() {
        return die;
    }

    public void setDie(Die die) {
        this.die = die;
    }

    public boolean checkColorRule(Die die) {                  //return true if rules are followed
        if(colorRule.equals(die.getColor()) || colorRule.equals("none")) return true;
        return false;
    }

    public boolean checkValueRule(Die die) {                 //return true if rules are followed
        if(valueRule.equals(die.getValue()) || valueRule.equals("none")) return true;
        return false;
    }

    public boolean isValid(Die die) {
        if(isNotEmpty()) return false;
        return checkColorRule(die) && checkValueRule(die);
    }

    @Override
    public String toString() {
        if(die != null) return die.toString();
        return colorRule + " [" + valueRule + "]";
    }

    public void dump() {
        System.out.println(this);
    }

}