package it.polimi.ingsw.publicobjectives;

import it.polimi.ingsw.WindowPattern;

public class BrightShade extends PublicObjective {
    private int points = 2;
    private String name = "Sfumature Chiare";

    public int getPoints() {
        return points;
    }

    public String getName() {
        return name;
    }

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

    @Override
    public String toString() {
        String s = this.name;
        return s + " [" + points + "]";
    }
}
