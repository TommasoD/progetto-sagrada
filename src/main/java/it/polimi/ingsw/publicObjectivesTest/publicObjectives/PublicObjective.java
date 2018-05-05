package it.polimi.ingsw.publicObjectivesTest.publicObjectives;
import it.polimi.ingsw.WindowPattern;
import it.polimi.ingsw.Objective;

public abstract class PublicObjective implements Objective{
    private int points;
    private String name;
    public abstract int checkPoints(WindowPattern window);
}
