package it.polimi.ingsw.publicObjective;

import it.polimi.ingsw.WindowPattern;

public class DiffColorRow extends PublicObjective {

    private int points = 6;
    private String name = "Colori diversi - Riga";

    public int getPoints() {
        return points;
    }

    public String getName() {
        return name;
    }

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

    @Override
    public String toString() {
        String s = this.name;
        return s + " [" + points + "]";
    }

}