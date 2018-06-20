package it.polimi.ingsw.client;

import java.util.ArrayList;

public class ValidateInput {


    private static final String INVALID_ENTRY = "Invalid entry";
    private static final String WINDOW_NAME_NOT_PRESENT = "The window name entered is not present.";
    private static final String TOOL_CARD_NOT_PRESENT = "Invalid entry, the selected tool card is not present";
    private static final String DIGIT_WINDOW = "You have to choose a window. Digit 'window' to do so.";



    // check if the die index form the round track is correct
    public boolean checkDieInArray(int index, int size) {
        if ((0 <= index) && (index < size)) return true;
        else {
            System.out.println(INVALID_ENTRY);
            return false;
        }
    }


    // check if the die index of the column is correct
    public boolean checkColumnIndex(int index) {
        if ((0 <= index) && (index < 5)) return true;
        else {
            System.out.println(INVALID_ENTRY);
            return false;
        }
    }


    // check if the die index of the row is correct
    public boolean checkRowIndex(int index) {
        if ((0 <= index) && (index < 4)) return true;
        else {
            System.out.println(INVALID_ENTRY);
            return false;
        }
    }

    //the name entered must match exactly one window name
    public boolean checkWindowName(ArrayList<String> windowNames, String insertName) {
        for (String windowName : windowNames) if (windowName.equals(insertName)) return true;
        System.out.println(DIGIT_WINDOW);
        System.out.println(WINDOW_NAME_NOT_PRESENT);
        System.out.println();

        return false;
    }

    // check if the tool card index is correct (between 0 and 11)
    public boolean checkToolCardInArray(int index) {
        if ((0 <= index) && (index < 12)) return true;
        System.out.println(TOOL_CARD_NOT_PRESENT);
        return false;
    }

    public boolean increaseOrDecreaseChoice(int i) {
        if ((i == 0) || (i == 1)) return true;
        System.out.println(INVALID_ENTRY);
        return false;
    }

    public boolean checkDieValue(int i) {
        if ((i >= 1) && (i <= 6)) return true;
        System.out.println(INVALID_ENTRY);
        return false;
    }

}