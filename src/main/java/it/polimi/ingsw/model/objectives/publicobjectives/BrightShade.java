package it.polimi.ingsw.model.objectives.publicobjectives;

import it.polimi.ingsw.model.WindowPattern;

/**
 * Represents the public objective card "Lights Shades".
 */
public class BrightShade extends PublicObjective {

    /**
     * Class constructor.
     */

    public BrightShade(){
        points = 2;
        name = "Light Shades";
        description = "Sets of 1 & 2";
    }

    /**
     *
     * @param window the window of a player.
     * @return public objective points given from this card.
     */

    public int checkPoints(WindowPattern window) {
        int one = 0;
        int two = 0;
        for (int i = 0; i < 4; i++) {
            for(int j = 0; j < 5; j++) {
                if(window.getWindowMatrix(j, i).isNotEmpty()) {
                    if(window.getWindowMatrix(j, i).getDie().getValue().equals("1"))
                        one++;
                    if(window.getWindowMatrix(j, i).getDie().getValue().equals("2"))
                        two++;
                }
            }
        }
        return (Math.min(one, two)*points);
    }
}
