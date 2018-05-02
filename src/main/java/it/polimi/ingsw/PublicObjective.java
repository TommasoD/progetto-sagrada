package it.polimi.ingsw;

public abstract class PublicObjective implements Objective{
    private int points;
    private String name;
    public abstract int checkPoints(WindowPattern window);
}
