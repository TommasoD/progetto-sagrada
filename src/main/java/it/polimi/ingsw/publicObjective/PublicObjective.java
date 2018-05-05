package it.polimi.ingsw.publicObjective;
import it.polimi.ingsw.WindowPattern;
import it.polimi.ingsw.Objective;

public abstract class PublicObjective implements Objective{
    private int points;
    private String name;
    public abstract int checkPoints(WindowPattern window);
}
