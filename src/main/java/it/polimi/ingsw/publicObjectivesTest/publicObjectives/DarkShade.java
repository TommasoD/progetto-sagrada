package it.polimi.ingsw.publicObjectivesTest.publicObjectives;
import it.polimi.ingsw.WindowPattern;

public class DarkShade extends PublicObjective {
    private int points = 2;
    private String name = "Sfumature Scure";

    public int getPoints() {
        return points;
    }

    public String getName() {
        return name;
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

    @Override
    public String toString() {
        String s = this.name;
        return s + " [" + points + "]";
    }
}
