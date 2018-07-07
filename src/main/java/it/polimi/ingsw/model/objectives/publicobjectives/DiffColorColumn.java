package it.polimi.ingsw.model.objectives.publicobjectives;

import it.polimi.ingsw.model.WindowPattern;

/**
 * Represents the public objective card "Column Color Variety".
 */
public class DiffColorColumn extends PublicObjective {

    /**
     * Class constructor.
     */

    public DiffColorColumn(){
        points = 5;
        name = "Column Color Variety";
        description = "Columns with no repeated colors";
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
        int occupiedSlot = 0;

        //the first index is the columns, the second the rows
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                if (window.getWindowMatrix(i, j).isNotEmpty()) {
                    if (window.getWindowMatrix(i, j).getDie().getColor().equals("RED")) red++;
                    if (window.getWindowMatrix(i, j).getDie().getColor().equals("GREEN")) green++;
                    if (window.getWindowMatrix(i, j).getDie().getColor().equals("BLUE")) blue++;
                    if (window.getWindowMatrix(i, j).getDie().getColor().equals("YELLOW")) yellow++;
                    if (window.getWindowMatrix(i, j).getDie().getColor().equals("PURPLE")) purple++;
                    occupiedSlot++;
                }
            }

            if (red < 2 && green < 2 && blue < 2 && yellow < 2 && purple < 2 && occupiedSlot == 4) sum = sum + points;
            red = 0;
            green = 0;
            blue = 0;
            yellow = 0;
            purple = 0;
            occupiedSlot = 0;
        }
        return sum;
    }
}