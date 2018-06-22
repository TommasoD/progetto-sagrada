package it.polimi.ingsw.model;

public class WindowPattern {


    protected Slot[][] windowMatrix;
    protected int difficultyToken;
    protected String name;

    private static final String ADJACENT_SLOT_EXCEPTION = "adjacentSlots exception thrown: ";
    private static final String COLORS_AND_VALUES_EXCEPTION = "colorsAndValues exception thrown: ";
    private static final String TOKEN_AVAILABLE = " tokens available";

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

    public boolean isValidWithoutColors(int x, int y, Die die) {

        if(windowMatrix[x][y].isNotEmpty()) return false;
        if(!windowMatrix[x][y].checkValueRule(die)) return false;

        //check colors and values in orthogonal slots:

        if(colorsAndValues(x-1, y, die)) return false;
        if(colorsAndValues(x+1, y, die)) return false;
        if(colorsAndValues(x, y-1, die)) return false;
        if(colorsAndValues(x, y+1, die)) return false;

        //check empty/full slots around:

        return allAdjacent(x, y);

    }

    public boolean isValidWithoutValues(int x, int y, Die die) {

        if(windowMatrix[x][y].isNotEmpty()) return false;
        if(!windowMatrix[x][y].checkColorRule(die)) return false;

        //check colors and values in orthogonal slots:

        if(colorsAndValues(x-1, y, die)) return false;
        if(colorsAndValues(x+1, y, die)) return false;
        if(colorsAndValues(x, y-1, die)) return false;
        if(colorsAndValues(x, y+1, die)) return false;

        //check empty/full slots around:

        return allAdjacent(x, y);

    }

    public boolean isValidFirstMove(int x, int y, Die die) {

        //regarding the selected slot:

        if(!windowMatrix[x][y].isValid(die)) return false;

        //check if die is placed on one border

        if(x == 0 || x == 4) return true;
        return (y == 0 || y == 3);
    }

    private boolean adjacentSlots(int x, int y){
        try {
            return windowMatrix[x][y].isNotEmpty();
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println(ADJACENT_SLOT_EXCEPTION + e);
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
        return adjacentSlots(x+1, y);
    }

    private boolean colorsAndValues(int x, int y, Die die) {
        try {
            if(windowMatrix[x][y].isNotEmpty()) {
                if (windowMatrix[x][y].getDie().getColor().equals(die.getColor()) ||
                        windowMatrix[x][y].getDie().getValue().equals(die.getValue())) return true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(COLORS_AND_VALUES_EXCEPTION + e);
        }
        return false;
    }

    /*
        print the window
     */

    public String toString(){
        StringBuilder sb = new StringBuilder();
        String s = "\u001B[0m" + name + " (" + difficultyToken + TOKEN_AVAILABLE  + ")" + "\n";
        sb.append(s);
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 5; j++){
                sb.append(windowMatrix[j][i]);
                sb.append(" ");
            }
            sb.append("\u001B[0m\n");
        }
        return sb.toString();
    }

    public void dump() {
        System.out.println(this.toString());
    }

}