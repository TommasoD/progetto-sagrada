package it.polimi.ingsw.model.objectives.publicobjectives;
import it.polimi.ingsw.model.WindowPattern;
import it.polimi.ingsw.model.objectives.Objective;

public abstract class PublicObjective implements Objective{

    protected int points;
    protected String name;

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        return name + " [" + points + "]";
    }

    public void dump(){
        System.out.println(this);
    }

}
