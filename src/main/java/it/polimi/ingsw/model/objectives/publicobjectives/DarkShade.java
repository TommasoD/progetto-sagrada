package it.polimi.ingsw.model.objectives.publicobjectives;

import it.polimi.ingsw.model.WindowPattern;

public class DarkShade extends PublicObjective {

    public DarkShade(){
        points = 2;
        name = "Deep Shades";
    }

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
