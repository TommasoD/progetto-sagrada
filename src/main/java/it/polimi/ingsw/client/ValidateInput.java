package it.polimi.ingsw.client;

import java.util.ArrayList;

public class ValidateInput {


    // check if the die index form the roundtrack is correct
    public boolean checkDieInArray(int index, int size) {
        if ((0 <= index) && (index <= size)) return true;
        else {
            System.out.println("Invalid entry");
            return false;
        }
    }


    // check if the die index of the column is correct
    public boolean checkColumnIndex(int index) {
        if ((0 <= index) && (index < 5)) return true;
        else {
            System.out.println("Invalid entry");
            return false;
        }
    }


    // check if the die index of the row is correct
    public boolean checkRowIndex(int index) {
        if ((0 <= index) && (index < 4)) return true;
        else {
            System.out.println("Invalid entry");
            return false;
        }
    }

    //the name entered must match exactly one window name
    public boolean checkWindowName(ArrayList<String> windowNames, String insertName) {
        for (String windowName : windowNames) if (windowName.equals(insertName)) return true;
        System.out.println("The window name entered is not present");
        return false;
    }

    // check if the toolcard index is correct (between 0 and 11)
    public boolean checkToolCardInArray(int index) {
        if ((0 <= index) && (index < 12)) return true;
        System.out.println("Invalid entry, the selected tool card is not present");
        return false;
    }

    public boolean increaseOrDecreaseChoice(int i) {
        if ((i == 0) || (i == 1)) return true;
        System.out.println("Invalid entry");
        return false;
    }

    public boolean checkDieValue(int i) {
        if ((i >= 1) && (i <= 6)) return true;
        System.out.println("Invalid entry");
        return false;
    }

}