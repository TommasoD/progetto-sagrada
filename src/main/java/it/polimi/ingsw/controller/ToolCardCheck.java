package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Die;
import it.polimi.ingsw.model.WindowPattern;

import java.util.List;

/**
 * Checks if a ToolCard effect can be used.
 */
public class ToolCardCheck {

    /**
     * Checks if the ToolCard1 effect can be used.
     * @param d a die from the draft pool.
     * @param action 0 -> decrease, 1 -> increase.
     * @return false if a player try to make an illegal move; true otherwise.
     */

    public boolean toolCard1(Die d, int action){
        if(d.getValue().equals("1") && action == 0) return false; ////decrease
        if(d.getValue().equals("6") && action == 1) return false; ////increase
        return true;
    }

    /**
     * Checks if the ToolCard2 effect can be used.
     * @param window the window of a player.
     * @param x the column of the selected die in the window.
     * @param y the row of the selected die in the window.
     * @param a the destination column of the selected die in the window.
     * @param b the destination row of the selected die in the window.
     * @return true if a player try to make a legal move; false otherwise.
     */

    public boolean toolCard2(WindowPattern window, int x, int y, int a, int b){
        if (window.getWindowMatrix(x, y).isNotEmpty()) {
            Die die = window.getWindowMatrix(x, y).removeDie();
            if (window.isValidWithoutColors(a, b, die)) {
                window.getWindowMatrix(x, y).setDie(die);
                return true;
            }
            window.getWindowMatrix(x, y).setDie(die);
        }
        return false;
    }

    /**
     * Checks if the ToolCard3 effect can be used.
     * @param window the window of a player.
     * @param x the column of the selected die in the window.
     * @param y the row of the selected die in the window.
     * @param a the destination column of the selected die in the window.
     * @param b the destination row of the selected die in the window.
     * @return true if a player try to make a legal move; false otherwise.
     */

    public boolean toolCard3(WindowPattern window, int x, int y, int a, int b){
        if (window.getWindowMatrix(x, y).isNotEmpty()) {
            Die die = window.getWindowMatrix(x, y).removeDie();
            if (window.isValidWithoutValues(a, b, die)) {
                window.getWindowMatrix(x, y).setDie(die);
                return true;
            }
            window.getWindowMatrix(x, y).setDie(die);
        }
        return false;
    }

    /**
     * Checks if the ToolCard4 effect can be used.
     * @param window the window of a player.
     * @param x the column of the first selected die in the window.
     * @param y the row of the second selected die in the window.
     * @param a the column of the second selected die in the window.
     * @param b the row of the second selected die in the window.
     * @param x2 the destination column of the first selected die in the window.
     * @param y2 the destination row of the first selected die in the window.
     * @param a2 the destination column of the second selected die in the window.
     * @param b2 the destination row of the second selected die in the window.
     * @return true if a player try to make a legal move; false otherwise.
     */

    public boolean toolCard4(WindowPattern window, int x, int y, int a, int b, int x2, int y2, int a2, int b2){
        if (window.getWindowMatrix(x, y).isNotEmpty() && window.getWindowMatrix(x2, y2).isNotEmpty() && (x != x2 || y != y2) && (a != a2 || b != b2)) {
            Die die1 = window.getWindowMatrix(x, y).removeDie();
            Die die2 = window.getWindowMatrix(x2, y2).removeDie();
            if (window.isValid(a, b, die1) && window.isValid(a2, b2, die2)) {
                window.getWindowMatrix(x, y).setDie(die1);
                window.getWindowMatrix(x2, y2).setDie(die2);
                return true;
                }
            window.getWindowMatrix(x, y).setDie(die1);
            window.getWindowMatrix(x2, y2).setDie(die2);
            }
        return false;
    }

    /**
     * Checks if the ToolCard1 effect can be used.
     * @param window the window of a player.
     * @param d a die from the draft pool.
     * @param x the destination column of the parameter d in the window.
     * @param y the destination row of the parameter d in the window.
     * @return true if a player try to make a legal move; false otherwise.
     */

    public boolean toolCard8(WindowPattern window, Die d, int x, int y){
        if(window.isValid(x, y, d)) return true;
        return false;
    }

    /**
     * Checks if the ToolCard1 effect can be used.
     * @param window the window of a player.
     * @param d a die from the draft pool.
     * @param x the destination column of the parameter d in the window.
     * @param y the destination row of the parameter d in the window.
     * @return true if a player try to make a legal move; false otherwise.
     */

    public boolean toolCard9(WindowPattern window, Die d, int x, int y){
        if(window.allAdjacent(x, y)) return false;
        if(window.getWindowMatrix(x, y).isValid(d)) return true;
        return false;
    }

    /**
     * Checks if the ToolCard4 effect can be used.
     * @param track a round track.
     * @param window the window of a player.
     * @param x the column of the first selected die in the window.
     * @param y the row of the second selected die in the window.
     * @param a the column of the second selected die in the window.
     * @param b the row of the second selected die in the window.
     * @param x2 the destination column of the first selected die in the window.
     * @param y2 the destination row of the first selected die in the window.
     * @param a2 the destination column of the second selected die in the window.
     * @param b2 the destination row of the second selected die in the window.
     * @return true if a player try to make a legal move; false otherwise.
     */

    public boolean toolCard12(List<Die> track, WindowPattern window, int x, int y, int a, int b, int x2, int y2, int a2, int b2){
        if(!window.getWindowMatrix(x, y).isNotEmpty() || (x == x2 && y == y2) || (a == a2 && b == b2)) return false;
        Die die1 = window.getWindowMatrix(x, y).removeDie();
        if (!window.isValid(a, b, die1)){
            window.getWindowMatrix(x, y).setDie(die1);
            return false;
        }

        try{
            if(window.getWindowMatrix(x2, y2).isNotEmpty()){
                Die die2 = window.getWindowMatrix(x2, y2).removeDie();
                if (window.isValid(a2, b2, die2) && die1.getColor().equals(die2.getColor())){
                    for(Die d : track) {
                        if(die1.getColor().equals(d.getColor())) {
                            window.getWindowMatrix(x, y).setDie(die1);
                            window.getWindowMatrix(x2, y2).setDie(die2);
                            return true;
                        }
                    }
                }
                window.getWindowMatrix(x, y).setDie(die1);
                window.getWindowMatrix(x2, y2).setDie(die2);
                return false;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            for(Die d : track) {
                if(die1.getColor().equals(d.getColor())) {
                    window.getWindowMatrix(x, y).setDie(die1);
                    return true;
                }
            }
        }
        window.getWindowMatrix(x, y).setDie(die1);
        return false;
    }
}
