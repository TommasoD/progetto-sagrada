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
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 5; j++){
                if(window.windowMatrix[j][i].isNotEmpty()) {
                    if(window.windowMatrix[j][i].getDie().getColor().equals(this.color)) {
                        sum = sum + window.windowMatrix[j][i].getDie().getValueAsInt();
                    }
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
