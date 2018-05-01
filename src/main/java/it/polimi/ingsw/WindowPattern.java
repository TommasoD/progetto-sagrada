package it.polimi.ingsw;

public abstract class WindowPattern {

    protected Slot[] windowMatrix;
    protected int difficultyToken;
    protected String name;

    public String getName(){
        return name;
    }

    public int getDifficultyToken() {
        return difficultyToken;
    }

    public void decreaseDifficultyToken(){
        difficultyToken--;
    }

    public boolean isValid(int index, Die die) {
        if(!windowMatrix[index].isValid(die)) return false;                 //regarding the selected slot

        if(colorsAndValues(index - 7, die)) return false;				//colors and values in orthogonal slots
        if(colorsAndValues(index + 7, die)) return false;
        if(colorsAndValues(index - 1, die)) return false;
        if(colorsAndValues(index + 1, die)) return false;

        if(windowMatrix[index - 8].isNotEmpty()) return true;				//check empty/full slots around
        if(windowMatrix[index - 7].isNotEmpty()) return true;
        if(windowMatrix[index - 6].isNotEmpty()) return true;
        if(windowMatrix[index - 1].isNotEmpty()) return true;
        if(windowMatrix[index + 1].isNotEmpty()) return true;
        if(windowMatrix[index + 6].isNotEmpty()) return true;
        if(windowMatrix[index + 7].isNotEmpty()) return true;
        return windowMatrix[index + 8].isNotEmpty();
    }

    private boolean colorsAndValues(int index, Die die) {
        if(windowMatrix[index].isNotEmpty()) {
            if(windowMatrix[index].getDie().getColor().equals(die.getColor()) ||
                    windowMatrix[index].getDie().getValue().equals(die.getValue())) return true;
        }
        return false;
    }

    public void dump() {
        for(Slot s : windowMatrix) {
            System.out.println(s);
        }
    }

}