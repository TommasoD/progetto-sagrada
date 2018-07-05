package it.polimi.ingsw.model;

/**
 * Represents a window pattern, a card consisting of slots for dice possibly with some restrictions
 * on color or value. Each window pattern has a unique name and a number of available token to
 * be used during the match in order to activate tool cards. The more difficult the window is, i.e. the more
 * restrictions there are, the more token it gets.
 */
public class WindowPattern {

    protected Slot[][] windowMatrix;
    protected int difficultyToken;
    protected String name;

    private static final String ADJACENT_SLOT_EXCEPTION = "adjacentSlots exception thrown";
    private static final String COLORS_AND_VALUES_EXCEPTION = "colorsAndValues exception thrown";
    private static final String TOKEN_AVAILABLE = " tokens available";
    private static final int LENGTH = 5;
    private static final int HEIGHT = 4;

    /**
     * Class constructor.
     */

    public WindowPattern () {
        this.windowMatrix = new Slot[LENGTH][HEIGHT];
    }

    /**
     *
     * @param name the name of the window.
     */

    public void setName (String name) {
        this.name = name;
    }

    /**
     *
     * @return the name of the window.
     */

    public String getName(){
        return name;
    }

    /**
     *
     * @param difficultyToken the available tokens.
     */

    public void setDifficultyToken(int difficultyToken) {
        this.difficultyToken = difficultyToken;
    }

    /**
     *
     * @return the available tokens.
     */

    public int getDifficultyToken() {
        return difficultyToken;
    }

    /**
     * Assigns a slot to a position in the window.
     * @param slot the slot.
     * @param x the horizontal position.
     * @param y the vertical position.
     */

    public void setWindowMatrix(Slot slot, int x, int y) {
        windowMatrix[x][y] = slot;
    }

    /**
     * Returns the slot in a certain position in the window.
     * @param x the horizontal position.
     * @param y the vertical position.
     * @return the slot.
     */

    public Slot getWindowMatrix(int x, int y) {
        return this.windowMatrix[x][y];
    }

    /**
     * Decreases the number of difficulty tokens available of a certain quantity.
     * @param cost the quantity to be decreased.
     */

    public void decreaseDifficultyToken(int cost){
        difficultyToken = difficultyToken - cost;
    }

    /**
     * Returns true if a certain die can be placed in a certain slot in the window pattern.
     * In particular, returns true if and only if:<p>
     *     - the slot is empty;<p>
     *     - the die doesn't break the color or value rules of the slot;<p>
     *     - the die would not be orthogonally adjacent to a die of the same color or value;<p>
     *     - the die would be adjacent to at least another die.
     * @param x the horizontal position of the slot.
     * @param y the vertical position of the slot.
     * @param die the die.
     * @return true if the die can be placed in a certain slot; false otherwise.
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

    /**
     * Returns true if a certain die can be placed in a certain slot in the window pattern,
     * ignoring all the color rules of the slot.
     * @param x the horizontal position of the slot.
     * @param y the vertical position of the slot.
     * @param die the die.
     * @return true if the die can be placed in a certain slot; false otherwise.
     */

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

    /**
     * Returns true if a certain die can be placed in a certain slot in the window pattern,
     * ignoring all the value rules of the slot.
     * @param x the horizontal position of the slot.
     * @param y the vertical position of the slot.
     * @param die the die.
     * @return true if the die can be placed in a certain slot; false otherwise.
     */

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

    /**
     * Returns true if a certain die can be placed in a certain slot in the window pattern
     * as the first positioning of the match. In particular, the rule regarding the presence
     * of at least one die in the adjacent slots is ignored, but the die must be placed on the border.
     * @param x the horizontal position of the slot.
     * @param y the vertical position of the slot.
     * @param die the die.
     * @return true if the die can be placed in a certain slot; false otherwise.
     */

    public boolean isValidFirstMove(int x, int y, Die die) {

        //regarding the selected slot:

        if(!windowMatrix[x][y].isValid(die)) return false;

        //check if die is placed on one border

        if(x == 0 || x == LENGTH - 1) return true;
        return (y == 0 || y == HEIGHT - 1);
    }

    /**
     * Returns true if there is a die in the slot at the given position.
     * @param x the horizontal position.
     * @param y the vertical position.
     * @return true if there is a die in the slot at the given position; false otherwise.
     */

    private boolean adjacentSlots(int x, int y){
        try {
            return windowMatrix[x][y].isNotEmpty();
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println(ADJACENT_SLOT_EXCEPTION);
        }
        return false;
    }

    /**
     * Returns true if there's at least one die in the slots surrounding the given position.
     * @param x the horizontal position.
     * @param y the vertical position.
     * @return true if there's at least one die in the slots surrounding the given position; false otherwise.
     */

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

    /**
     * Returns true if a given die has a different color and value from the ones of the die
     * in a given slot, or in case the slot is empty.
     * @param x the horizontal position of the slot.
     * @param y the vertical position of the slot.
     * @param die the die.
     * @return true if the die differs in color and value from the die in a certain slot, or the slot is empty; false otherwise.
     */

    private boolean colorsAndValues(int x, int y, Die die) {
        try {
            if(windowMatrix[x][y].isNotEmpty()) {
                if (windowMatrix[x][y].getDie().getColor().equals(die.getColor()) ||
                        windowMatrix[x][y].getDie().getValue().equals(die.getValue())) return true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(COLORS_AND_VALUES_EXCEPTION);
        }
        return false;
    }

    /**
     * Returns the String representation of the class.
     * @return the String representation of the class.
     */

    public String toString(){
        StringBuilder sb = new StringBuilder();
        String s = "\u001B[0m" + name + " (" + difficultyToken + TOKEN_AVAILABLE  + ")" + "\n";
        sb.append(s);
        for(int i = 0; i < HEIGHT; i++){
            for(int j = 0; j < LENGTH; j++){
                sb.append(windowMatrix[j][i]);
                sb.append(" ");
            }
            sb.append("\u001B[0m\n");
        }
        return sb.toString();
    }

    /**
     * Prints the class.
     */

    public void dump() {
        System.out.println(this.toString());
    }

}