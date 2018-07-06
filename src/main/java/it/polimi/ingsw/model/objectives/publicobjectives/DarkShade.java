package it.polimi.ingsw.model.objectives.publicobjectives;

import it.polimi.ingsw.model.WindowPattern;

/**
 * Represents the public objective card "Deep Shades".
 */
public class DarkShade extends PublicObjective {

    /**
     * Class constructor.
     */

    public DarkShade(){
        points = 2;
        name = "Deep Shades";
        description = "Sets of 5 & 6";
    }

    /**
     *
     * @param window the window of a player.
     * @return public objective points given from this card.
     */

    public int checkPoints(WindowPattern window) {
        int five = 0;
        int six = 0;
        for (int i = 0; i < 4; i++)  {
            for (int j = 0; j < 5; j++) {
                if (window.getWindowMatrix(j, i).isNotEmpty()) {
                    if(window.getWindowMatrix(j, i).getDie().getValue().equals("5"))
                        five++;
                    if(window.getWindowMatrix(j, i).getDie().getValue().equals("6"))
                        six++;
                }
            }
        }
        return (Math.min(five,six)*points);
    }
}
