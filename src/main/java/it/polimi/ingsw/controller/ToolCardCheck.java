package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Die;
import it.polimi.ingsw.model.WindowPattern;

public class ToolCardCheck {

    public boolean toolCard1(Die d, int action){
        if(d.getValue().equals("1") && action == 0) return false; ////decrease
        if(d.getValue().equals("6") && action == 1) return false; ////increase
        return true;
    }

    public boolean toolCard2(WindowPattern window, int x, int y, int a, int b){
        if (window.getWindowMatrix(x, y).isNotEmpty()) {
            if (window.isValidWithoutColors(a, b, window.getWindowMatrix(x, y).getDie())) return true;
        }
        return false;
    }

    public boolean toolCard3(WindowPattern window, int x, int y, int a, int b){
        if (window.getWindowMatrix(x, y).isNotEmpty()) {
            if (window.isValidWithoutValues(a, b, window.getWindowMatrix(x, y).getDie())) return true;
        }
        return false;
    }

    public boolean toolCard4(WindowPattern window, int x, int y, int a, int b, int x2, int y2, int a2, int b2){
        if (window.getWindowMatrix(x, y).isNotEmpty() && window.getWindowMatrix(x2, y2).isNotEmpty() && (x != x2 || y != y2) && (a != a2 || b != b2)) {
            if (window.isValid(a, b, window.getWindowMatrix(x, y).getDie()) && window.isValid(a2, b2, window.getWindowMatrix(x2, y2).getDie()))
                return true;
            }
        return false;
    }

   /* public boolean toolCard6(WindowPattern window, Die d, int x, int y){


        return false;
    } */

    public boolean toolCard8(WindowPattern window, Die d, int x, int y){


        return false;
    }

    public boolean toolCard9(WindowPattern window, Die d, int x, int y){


        return false;
    }

    public boolean toolCard12(WindowPattern window, int x, int y, int a, int b, int x2, int y2, int a2, int b2){


        return false;
    }
}
