package it.polimi.ingsw;

public abstract class WindowPattern {

    protected Slot[][] windowMatrix;
    protected int difficultyToken;
    protected String name;

    public String getName(){
        return name;
    }

    public int getDifficultyToken() {
        return difficultyToken;
    }

    public Slot getWindowMatrix(int x, int y) {
        return this.windowMatrix[x][y];
    }

    public void decreaseDifficultyToken(){
        difficultyToken--;
    }

    public boolean isValid(int x, int y, Die die) {
        if(!windowMatrix[x][y].isValid(die)) return false;                 //regarding the selected slot

        if(colorsAndValues(x-1, y, die)) return false;	    			//check colors and values in orthogonal slots
        if(colorsAndValues(x+1, y, die)) return false;
        if(colorsAndValues(x, y-1, die)) return false;
        if(colorsAndValues(x, y+1, die)) return false;

        for(int i = x-1; i <= x+1; i++) {                                   //check empty/full slots around
            if(adjacentSlots(i, y-1)) return true;
        }
        for(int i = x-1; i <= x+1; i++)
        {
            if(adjacentSlots(i, y+1)) return true;
        }
        if(adjacentSlots(x-1, y)) return true;
        return adjacentSlots(x-1, y);
    }

    private boolean adjacentSlots(int x, int y){
        try {
            return windowMatrix[x][y].isNotEmpty();
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("adjacentSlots exception thrown: " + e);
        }
        return false;
    }

    private boolean colorsAndValues(int x, int y, Die die) {
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

    public void dump() {
        System.out.println(name + " " + difficultyToken + "\n");
        for(int i = 0; i < 4; i++){
            System.out.println(windowMatrix[0][i] + " " + windowMatrix[1][i] + " " + windowMatrix[2][i]
                    + " " + windowMatrix[3][i] + " " + windowMatrix[4][i]);
        }
    }

}