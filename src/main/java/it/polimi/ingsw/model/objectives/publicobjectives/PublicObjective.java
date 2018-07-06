package it.polimi.ingsw.model.objectives.publicobjectives;
import it.polimi.ingsw.model.objectives.Objective;

/**
 * Public objectives are selected at the start of the match and every player gets
 * some point from each objective based on the status of its window pattern
 * at the end of the game. Among with private objectives, they determine the score of every player.
 */
public abstract class PublicObjective implements Objective{

    protected int points;
    protected String name;
    protected String description;

    /**
     *
     * @return the points given by the objective.
     */

    public int getPoints() {
        return points;
    }

    /**
     *
     * @param points the points given by the objective.
     */

    public void setPoints(int points) {
        this.points = points;
    }

    /**
     *
     * @return the name of the objective.
     */

    public String getName() {
        return name;
    }

    /**
     *
     * @param name the name of the objective.
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return the description of the card.
     */

    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description the description of the card.
     */

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the String representation of the class.
     * @return the String representation of the card.
     */

    public String toString(){
        return name + " [" + points + "] - " + description;
    }

    /**
     *  Prints the class.
     */

    public void dump(){
        System.out.println(this);
    }

}
