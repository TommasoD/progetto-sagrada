package it.polimi.ingsw.model;

public class WindowPattern {


    protected Slot[][] windowMatrix;
    protected int difficultyToken;
    protected String name;

    public WindowPattern () {
        this.windowMatrix = new Slot[5][4];

    }

    /*
        getter methods
     */


    public void setName (String name) {
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void setDifficultyToken(int difficultyToken) {
        this.difficultyToken = difficultyToken;
    }

    public int getDifficultyToken() {
        return difficultyToken;
    }

    public void setWindowMatrix(Slot slot, int x, int y) {
        windowMatrix[x][y] = slot;
    }


    public Slot getWindowMatrix(int x, int y) {
        return this.windowMatrix[x][y];
    }

    /*
        decrease the amount of difficulty tokens available
     */

    public void decreaseDifficultyToken(int cost){
        difficultyToken = difficultyToken - cost;
    }

    /*
        validation methods:
            adjacentSlots() checks a single slot and return true if it is not empty
            allAdjacent() checks all the slots adjacent to the one passed as parameter
            colorsAndValues() checks if a slot has the same value or color as the Die passed as parameter
            isValid() checks all rules a player should follow when positioning a Die
     */

    public boolean isValid(int x, int y, Die die) {

        //regarding the selected slot:

        if(!windowMatrix[x][y].isValid(die)) return false;

        //check colors and values in orthogonal slots:

        if(colorsAndValues(x-1, y, die)) return false;
        if(colorsAndValues(x+1, y, die)) return false;
        if(colorsAndValues(x, y-1, die)) return false;
        if(colorsAndValues(x, y+1, die)) return false;

        //check empty/full slots around:

        return allAdjacent(x, y);
    }

    private boolean adjacentSlots(int x, int y){
        try {
            return windowMatrix[x][y].isNotEmpty();
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("adjacentSlots exception thrown: " + e);
        }
        return false;
    }

    public boolean allAdjacent(int x, int y){
        for(int i = x-1; i <= x+1; i++) {
            if(adjacentSlots(i, y-1)) return true;
        }
        for(int i = x-1; i <= x+1; i++)
        {
            if(adjacentSlots(i, y+1)) return true;
        }
        if(adjacentSlots(x-1, y)) return true;
        return adjacentSlots(x-1, y);
    }

    public boolean colorsAndValues(int x, int y, Die die) {
        try {
            if(windowMatrix[x][y].isNotEmpty()) {
                if (windowMatrix[x][y].getDie().getColor().equals(die.getColor()) ||
                        windowMatrix[x][y].getDie().getValue().equals(die.getValue())) return true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("colorsAndValues exception thrown: " + e);
        }
        return false;
    }

    /*
        print the window
     */

    public void dump() {
        System.out.println("\u001B[0m" + name + " " + difficultyToken);
        for(int i = 0; i < 4; i++){
            System.out.println(windowMatrix[0][i] + " " + windowMatrix[1][i] + " " + windowMatrix[2][i]
                    + " " + windowMatrix[3][i] + " " + windowMatrix[4][i]);
        }
    }

}