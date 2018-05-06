package it.polimi.ingsw;

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

    public abstract void effect();

}
