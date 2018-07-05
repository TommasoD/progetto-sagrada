package it.polimi.ingsw.model.objectives.publicobjectives;

import it.polimi.ingsw.model.WindowPattern;

public class MediumShade extends PublicObjective {

    public MediumShade(){
        points = 2;
        name = "Medium Shades";
        description = "Sets of 3 & 4";
    }

    public int checkPoints(WindowPattern window) {
        int three = 0;
        int four = 0;

        for (int i = 0; i < 4; i++) {
            for(int j = 0; j < 5; j++) {
                if(window.getWindowMatrix(j, i).isNotEmpty()) {
                    if(window.getWindowMatrix(j, i).getDie().getValue().equals("3"))
                        three++;
                    if(window.getWindowMatrix(j, i).getDie().getValue().equals("4"))
                        four++;
                }
            }
        }

        return (Math.min(three,four)*points);
    }
}