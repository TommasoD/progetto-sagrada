package it.polimi.ingsw.model.objectives;

import it.polimi.ingsw.model.WindowPattern;

/**
 * Contains a method to calculate the score achieved with a certain window pattern.
 */
public interface Objective {

    /**
     * Calculates the score achieved with a certain window pattern.
     * @param window the window pattern.
     * @return the score.
     */

    int checkPoints(WindowPattern window);

}
