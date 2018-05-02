package it.polimi.ingsw;

public class PrivateObjective implements Objective {

    private String color;

    public PrivateObjective(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public int checkPoints(WindowPattern window) {
        int sum = 0;
        for(int i = 0; i < window.windowMatrix.length; i++) {
            if(window.windowMatrix[i].isNotEmpty()) {
                if(window.windowMatrix[i].getDie().getColor().equals(this.color)) {
                    sum = sum + window.windowMatrix[i].getDie().getValueAsInt();
                }
            }
        }
        return sum;
    }

    @Override
    public String toString() {
        String s = this.color;
        return s;
    }

}
