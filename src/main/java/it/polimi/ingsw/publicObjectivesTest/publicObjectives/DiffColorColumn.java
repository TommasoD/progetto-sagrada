package it.polimi.ingsw.publicObjectivesTest.publicObjectives;
import it.polimi.ingsw.WindowPattern;

public class DiffColorColumn extends PublicObjective {
    private int points = 5;
    private String name = "Colori diversi - Colonna";

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
        int occupiedSlot = 0; //serve per sapere se effettivamente tutte gli slot sono occupati da un dado

        //primo indice sono le colonne, secondo indice sono le righe
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

    @Override
    public String toString () {
            String s = this.name;
            return s + " [" + points + "]";
    }


}