package it.polimi.ingsw.model.objectives.publicobjectives;

import it.polimi.ingsw.model.WindowPattern;

/**
 * Represents the public objective card "Row Color Variety".
 */
public class DiffColorRow extends PublicObjective {

    /**
     * Class constructor.
     */

    public DiffColorRow(){
        points = 6;
        name = "Row Color Variety";
        description = "Rows with no repeated colors";
    }

    /**
     *
     * @param window the window of a player.
     * @return public objective points given from this card.
     */

    public int checkPoints(WindowPattern window) {
        int sum = 0;
        int red = 0;
        int green = 0;
        int blue = 0;
        int yellow = 0;
        int purple = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (window.getWindowMatrix(j, i).isNotEmpty()) {
                    if (window.getWindowMatrix(j, i).getDie().getColor().equals("RED")) red++;
                    if (window.getWindowMatrix(j, i).getDie().getColor().equals("GREEN")) green++;
                    if (window.getWindowMatrix(j, i).getDie().getColor().equals("BLUE")) blue++;
                    if (window.getWindowMatrix(j, i).getDie().getColor().equals("YELLOW")) yellow++;
                    if (window.getWindowMatrix(j, i).getDie().getColor().equals("PURPLE")) purple++;
                }
                if (red == 1 && green == 1 && blue == 1 && yellow == 1 && purple == 1) sum = sum + points;
            }
            red = 0;
            green = 0;
            blue = 0;
            yellow = 0;
            purple = 0;
        }
        return sum;
    }
}