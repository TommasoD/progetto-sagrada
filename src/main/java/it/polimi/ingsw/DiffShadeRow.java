package it.polimi.ingsw;

public class DiffShadeRow extends PublicObjective {
    private int points = 5;
    private String name = "Sfumature diverse - Riga";

    public int getPoints() {
        return points;
    }

    public String getName() {
        return name;
    }

    public int checkPoints(WindowPattern window) {
        int sum = 0;
        int one = 0;
        int two = 0;
        int three = 0;
        int four = 0;
        int five = 0;
        int six = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (window.windowMatrix[j][i].isNotEmpty()) {
                    if (window.windowMatrix[j][i].getDie().getValue().equals("1")) one++;
                    if (window.windowMatrix[j][i].getDie().getValue().equals("2")) two++;
                    if (window.windowMatrix[j][i].getDie().getValue().equals("3")) three++;
                    if (window.windowMatrix[j][i].getDie().getValue().equals("4")) four++;
                    if (window.windowMatrix[j][i].getDie().getValue().equals("5")) five++;
                    if (window.windowMatrix[j][i].getDie().getValue().equals("6")) six++;
                }
                if (one < 2 && two < 2 && three <2 && four < 2 && five < 2 && six < 2) sum = sum + points;
            }
            one = 0;
            two = 0;
            three = 0;
            four = 0;
            five = 0;
            six = 0;
        }
        return sum;
    }

    @Override
    public String toString() {
        String s = this.name;
        return s + " [" + points + "]";
    }
}
