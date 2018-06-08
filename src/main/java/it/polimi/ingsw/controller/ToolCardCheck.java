package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Die;
import it.polimi.ingsw.model.WindowPattern;

import java.util.ArrayList;

public class ToolCardCheck {

    public boolean toolCard1(Die d, int action){
        if(d.getValue().equals("1") && action == 0) return false; ////decrease
        if(d.getValue().equals("6") && action == 1) return false; ////increase
        return true;
    }

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

   /* public boolean toolCard6(WindowPattern window, Die d, int x, int y){


        return false;
    } */

    public boolean toolCard8(WindowPattern window, Die d, int x, int y){
        if(window.isValid(x, y, d)) return true;
        return false;
    }

    public boolean toolCard9(WindowPattern window, Die d, int x, int y){
        if(window.allAdjacent(x, y)) return false;
        if(window.getWindowMatrix(x, y).isValid(d)) return true;
        return false;
    }

    public boolean toolCard12(ArrayList<Die> track, WindowPattern window, int x, int y, int a, int b, int x2, int y2, int a2, int b2){
        if (window.getWindowMatrix(x, y).isNotEmpty() && window.getWindowMatrix(x2, y2).isNotEmpty() && (x != x2 || y != y2) && (a != a2 || b != b2)) {
            Die die1 = window.getWindowMatrix(x, y).removeDie();
            Die die2 = window.getWindowMatrix(x2, y2).removeDie();
            if (window.isValid(a, b, die1) && window.isValid(a2, b2, die2) && die1.getColor().equals(die2.getColor())) {
                for(int i = 0; i < track.size(); i++) {
                    if(die1.getColor().equals(track.get(i).getColor())) {
                        window.getWindowMatrix(x, y).setDie(die1);
                        window.getWindowMatrix(x2, y2).setDie(die2);
                        return true;
                    }
                }
            }
            window.getWindowMatrix(x, y).setDie(die1);
            window.getWindowMatrix(x2, y2).setDie(die2);
        }
        return false;
    }
}
