package it.polimi.ingsw.model.objectives.publicobjectives;
import it.polimi.ingsw.model.objectives.Objective;

public abstract class PublicObjective implements Objective{

    protected int points;
    protected String name;
    protected String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString(){
        return name + " [" + points + "] - " + description;
    }

    public void dump(){
        System.out.println(this);
    }

}
