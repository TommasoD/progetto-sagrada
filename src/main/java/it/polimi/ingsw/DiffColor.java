package it.polimi.ingsw;

public class DiffColor extends PublicObjective {

    private int points = 4;
    private String name = "Varietà di colori";

    public int getPoints() {
        return points;
    }

    public String getName() {
        return name;
    }


    public int checkPoints (WindowPattern window) {
        int sum;
        int[] colors = new int[5];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if(window.windowMatrix[j][i].isNotEmpty()) {
                    if (window.windowMatrix[j][i].getDie().getColor().equals("RED")) colors[0]++;
                    if (window.windowMatrix[j][i].getDie().getColor().equals("GREEN")) colors[1]++;
                    if (window.windowMatrix[j][i].getDie().getColor().equals("BLUE")) colors[2]++;
                    if (window.windowMatrix[j][i].getDie().getColor().equals("YELLOW")) colors[3]++;
                    if (window.windowMatrix[j][i].getDie().getColor().equals("PURPLE")) colors[4]++;
                }
            }
        }

        sum = colors[0];
        for (int i = 1; i < 5; i++) {
            sum = Math.min(sum,colors[i]);
        }
        return sum*points;
    }
    @Override
    public String toString() {
        String s = this.name;
        return s + " [" + points + "]";
    }

}
