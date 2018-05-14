package it.polimi.ingsw.toolcard;

public abstract class ToolCard {
    protected String name;
    private boolean alreadyUsed;

    protected ToolCard(){
        alreadyUsed = false;
    }

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