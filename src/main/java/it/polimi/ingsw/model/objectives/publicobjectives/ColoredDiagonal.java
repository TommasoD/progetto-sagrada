package it.polimi.ingsw.model.objectives.publicobjectives;

import it.polimi.ingsw.model.Slot;
import it.polimi.ingsw.model.WindowPattern;

/**
 * Represents the public objective card "Diagonals".
 * Contains two support matrix used to assist points calculation.
 */
public class ColoredDiagonal extends PublicObjective {

    private Slot[][] supportMatrix1;
    private Slot[][] supportMatrix2;

    /**
     * Class constructor.
     */

    public ColoredDiagonal(){
        points = 1;
        name = "Diagonals";
        description = "Two or more pieces of the same color";
        supportMatrix1 = new Slot[8][5];
        supportMatrix2 = new Slot[8][5];
    }

    /**
     * fills support matrix.
     * @param window the window of a player.
     */

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

    /**
     *
     * @param window the window of a player.
     * @return public objective points given from this card.
     */

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

    /**
     * Returns the String representation of the class.
     * @return the String representation of the card.
     */

    @Override
    public String toString(){
        return name + " [#] - " + description;
    }
}
