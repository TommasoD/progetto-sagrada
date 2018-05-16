package it.polimi.ingsw.model;

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

    private String valueRuleToString(){
        if(valueRule.equals("none")) return "-";
        return valueRule;
    }

    private String colorRuleToString(){
        if(colorRule.equals("RED")) return "\u001B[31m";
        if(colorRule.equals("GREEN")) return "\u001B[32m";
        if(colorRule.equals("YELLOW")) return "\u001B[33m";
        if(colorRule.equals("BLUE")) return "\u001B[34m";
        if(colorRule.equals("PURPLE")) return "\u001B[35m";
        return "\u001B[0m";
    }

    @Override
    public String toString() {
        if(die != null) return die.toString();
        return colorRuleToString() + " [" + valueRuleToString() + "]";
    }

    public void dump() {
        System.out.println(this);
    }

}