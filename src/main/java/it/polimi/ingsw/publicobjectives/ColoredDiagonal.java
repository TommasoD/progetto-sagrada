package it.polimi.ingsw.publicobjectives;

import it.polimi.ingsw.Slot;
import it.polimi.ingsw.WindowPattern;

public class ColoredDiagonal extends PublicObjective {

    private int points = 1;
    private String name = "Diagonali Colorate";
    private Slot[][] supportMatrix1 = new Slot[8][5];
    private Slot[][] supportMatrix2 = new Slot[8][5];

    public int getPoints() {
        return points;
    }

    public String getName() {
        return name;
    }

    private void fullSupportMatrix(WindowPattern window) {
        int m = 5;
        int k;

        for(int i = 0; i < 5; i++) {
            m--;
            k = 0;
            for (int j = m; j < (m + 4); j++) {
                this.supportMatrix1[j][i] = window.getWindowMatrix(i, k);
                k++;
            }
        }
        for(int i = 0; i < 5; i++) {
            k = 0;
            for(int j = m; j < (m + 4); j++) {
                this.supportMatrix2[j][i] = window.getWindowMatrix(i, k);
                k++;
            }
            m++;
        }
    }

    public int checkPoints(WindowPattern window) {
        this.fullSupportMatrix(window);
        int sum = 0;
        int[] colors1 = new int[5];
        int[] colors2 = new int[5];
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 5; j++) {
                if(this.supportMatrix1[i][j] != null) {
                    if(this.supportMatrix1[i][j].isNotEmpty()) {
                        for(int k = 0; k < 5; k++) {
                            if (this.supportMatrix1[i][j].getDie().getColorAsInt() != k) {
                                if (colors1[k] >= 2) sum = sum + colors1[k];
                                colors1[k] = 0;
                            }
                            if (this.supportMatrix1[i][j].getDie().getColorAsInt() == k) {
                                colors1[k]++;
                            }
                        }
                    }
                }
                if(this.supportMatrix2[i][j] != null) {
                    if(this.supportMatrix2[i][j].isNotEmpty()) {
                        for (int k = 0; k < 5; k++) {
                            if (this.supportMatrix2[i][j].getDie().getColorAsInt() != k) {
                                if (colors2[k] >= 2) sum = sum + colors2[k];
                                colors2[k] = 0;
                            }
                            if (this.supportMatrix2[i][j].getDie().getColorAsInt() == k) {
                                colors2[k]++;
                            }
                        }
                    }
                }
            }
            for(int k = 0; k < 5; k++) {
                if(colors1[k] >= 2) sum = sum + colors1[k];
                colors1[k] = 0;
                if(colors2[k] >= 2) sum = sum + colors2[k];
                colors2[k] = 0;
            }
        }
        return (sum*points);
    }

    @Override
    public String toString() {
        String s = this.name;
        return s + " [#]";
    }

}
