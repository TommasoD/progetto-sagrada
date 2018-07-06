package it.polimi.ingsw.model.objectives;

import it.polimi.ingsw.model.WindowPattern;

/**
 * Represents a private objective.
 * There are five private objective and each private objective has a color.
 */
public class PrivateObjective implements Objective {

    private String color;

    /**
     * Class constructor.
     * @param color the color of the private objective
     */

    public PrivateObjective(String color) {
        this.color = color;
    }

    /**
     *
     * @return the color of the private objective.
     */

    public String getColor() {
        return color;
    }

    /**
     * Calculates points given from the private objective.
     * @param window the window of the player with this private objective.
     * @return points from the private objective.
     */

    public int checkPoints(WindowPattern window) {
        int sum = 0;
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 5; j++){
                if(window.getWindowMatrix(j, i).isNotEmpty()) {
                    if(window.getWindowMatrix(j, i).getDie().getColor().equals(this.color)) {
                        sum = sum + window.getWindowMatrix(j, i).getDie().getValueAsInt();
                    }
                }
            }
        }
        return sum;
    }

    /**
     *
     * @return the color of this private objective.
     */

    @Override
    public String toString() {
        String s = this.color;
        return s;
    }

}
