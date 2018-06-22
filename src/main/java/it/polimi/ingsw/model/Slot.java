package it.polimi.ingsw.model;

public class Slot {

    private Die die;
    private String colorRule;
    private String valueRule;

    protected static final String RED = "RED";
    protected static final String GREEN = "GREEN";
    private static final String YELLOW = "YELLOW";
    private static final String BLUE = "BLUE";
    private static final String PURPLE = "PURPLE";

    /*
        constructor
     */

    public Slot(String color, String value) {
        colorRule = color;
        valueRule = value;
    }

    /*
        check if the slot is occupied by a Die
     */

    public boolean isNotEmpty() {
        return die != null;
    }

    /*
        getter and setter for attribute of Die type
     */

    public Die getDie() {
        return die;
    }

    public void setDie(Die die) {
        this.die = die;
    }

    public Die removeDie() {
        Die d = this.die;
        this.die = null;
        return d;
    }

    /*
        validation methods:
            checkColorRule() check the rules related to colors
            checkValueRule() check the rules related to values
            isValid() check both color and value rules
     */

    public boolean checkColorRule(Die die) {
        if(colorRule.equals(die.getColor()) || colorRule.equals("none")) return true;
        return false;
    }

    public boolean checkValueRule(Die die) {
        if(valueRule.equals(die.getValue()) || valueRule.equals("none")) return true;
        return false;
    }

    public boolean isValid(Die die) {
        if(isNotEmpty()) return false;
        return checkColorRule(die) && checkValueRule(die);
    }

    /*
        toString methods:
            valueRuleToString() handles values
            colorRuleToString() handles colors returning the corresponding color code
            toString() combines the two methods above
     */

    private String valueRuleToString(){
        if (valueRule.equals("1")) return "\u2680";
        if (valueRule.equals("2")) return "\u2681";
        if (valueRule.equals("3")) return "\u2682";
        if (valueRule.equals("4")) return "\u2683";
        if (valueRule.equals("5")) return "\u2684";
        if (valueRule.equals("6")) return "\u2685";
        if (colorRule.equals("none")) return "\u25a1";
        return "\u25a0";
    }

    private String colorRuleToString(){
        if(colorRule.equals(RED)) return "\u001B[31m";
        if(colorRule.equals(GREEN)) return "\u001B[32m";
        if(colorRule.equals(YELLOW)) return "\u001B[33m";
        if(colorRule.equals(BLUE)) return "\u001B[34m";
        if(colorRule.equals(PURPLE)) return "\u001B[35m";
        return "\u001B[0m";
    }

    @Override
    public String toString() {
        if(die != null) return die.toString();
        return colorRuleToString() + valueRuleToString();
    }

    /*
        dump() print the class
     */

    public void dump() {
        System.out.println(this);
    }

    public String getColorRule() {
        return colorRule;
    }

    public String getValueRule() {
        return valueRule;
    }
}