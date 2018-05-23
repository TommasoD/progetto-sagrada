package it.polimi.ingsw.model.toolcard;

public abstract class ToolCard {

    protected String name;
    private boolean alreadyUsed;

    /*
        constructor
     */

    protected ToolCard(){
        alreadyUsed = false;
    }

    /*
        getter and setter
     */

    public String getName() {
        return name;
    }

    public boolean isAlreadyUsed() {
        return alreadyUsed;
    }

    public void setAsAlreadyUsed(){
        alreadyUsed = true;
    }

}
