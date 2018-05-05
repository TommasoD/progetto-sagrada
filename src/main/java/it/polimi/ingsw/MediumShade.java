package it.polimi.ingsw;

public class MediumShade extends PublicObjective {

    private int points = 2;
    private String name = "Sfumature Medie";

    public int getPoints() {
        return points;
    }

    public String getName() {
        return name;
    }

    public int checkPoints(WindowPattern window) {
        int three = 0;
        int four = 0;

        for (int i = 0; i < 4; i++) {
            for(int j = 0; j < 5; j++) {
                if(window.windowMatrix[j][i].isNotEmpty()) {
                    if(window.windowMatrix[j][i].getDie().getValue().equals("3"))
                        three++;
                    if(window.windowMatrix[j][i].getDie().getValue().equals("4"))
                        four++;
                }
            }
        }

        return (Math.min(three,four)*points);
    }

    @Override
    public String toString() {
        String s = this.name;
        return s + " [" + points + "]";
    }
}