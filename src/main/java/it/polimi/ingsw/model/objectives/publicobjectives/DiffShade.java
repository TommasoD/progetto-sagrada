package it.polimi.ingsw.model.objectives.publicobjectives;

import it.polimi.ingsw.model.WindowPattern;

/**
 * Represents the public objective card "Shade Variety".
 */
public class DiffShade extends PublicObjective {

    /**
     * Class constructor.
     */

    public DiffShade(){
        points = 5;
        name = "Shade Variety";
        description = "Sets of one of each shade";
    }

    /**
     *
     * @param window the window of a player.
     * @return public objective points given from this card.
     */

    public int checkPoints(WindowPattern window) {
        int sum;
        int[] values = new int[6];
        for (int i = 0; i < 4; i++) {
            for(int j = 0; j < 5; j++) {
                if (window.getWindowMatrix(j, i).isNotEmpty()) {
                    if (window.getWindowMatrix(j, i).getDie().getValue().equals("1"))
                        values[0]++;
                    if (window.getWindowMatrix(j, i).getDie().getValue().equals("2"))
                        values[1]++;
                    if (window.getWindowMatrix(j, i).getDie().getValue().equals("3"))
                        values[2]++;
                    if (window.getWindowMatrix(j, i).getDie().getValue().equals("4"))
                        values[3]++;
                    if (window.getWindowMatrix(j, i).getDie().getValue().equals("5"))
                        values[4]++;
                    if (window.getWindowMatrix(j, i).getDie().getValue().equals("6"))
                        values[5]++;
                }
            }
        }
        sum = values[0];
        for(int i = 1; i < 6; i++) {
            sum = Math.min(sum, values[i]);
        }
        return (points*sum);
    }
}
