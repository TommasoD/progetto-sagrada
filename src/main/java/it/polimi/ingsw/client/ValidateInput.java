package it.polimi.ingsw.client;

import java.util.ArrayList;

/**
 * Public class with the function of validating keyboard inputs.
 */

public class ValidateInput {


    private static final String INVALID_ENTRY = "Invalid entry";
    private static final String WINDOW_NAME_NOT_PRESENT = "The window name entered is not present.";
    private static final String TOOL_CARD_NOT_PRESENT = "Invalid entry, the selected tool card is not present";
    private static final String DIGIT_WINDOW = "You have to choose a window. Digit 'window' to do so.";


    /**
     * Returns true if the index belongs to the array.
     * @param index the chosen die index.
     * @param size the size of the array.
     * @return true if the index belongs to the array;
     *         false otherwise.
     */

    public boolean checkDieInArray(int index, int size) {
        if ((0 <= index) && (index < size)) return true;
        else {
            System.out.println(INVALID_ENTRY);
            return false;
        }
    }


    /**
     * Returns true if the chosen index belongs to the
     * size of the column.
     * @param index the chosen die index.
     * @return true if the index belongs to the size of the column;
     *         false otherwise.
     */

    public boolean checkColumnIndex(int index) {
        if ((0 <= index) && (index < 5)) return true;
        else {
            System.out.println(INVALID_ENTRY);
            return false;
        }
    }

    /**
     * Returns true if the chosen index belongs to the
     * size of the row.
     * @param index the chosen die index.
     * @return true if the index belongs to the size of the row;
     *         false otherwise.
     */

    public boolean checkRowIndex(int index) {
        if ((0 <= index) && (index < 4)) return true;
        else {
            System.out.println(INVALID_ENTRY);
            return false;
        }
    }

    /**
     * Returns true if the name entered match exactly the name
     * of a window actually present.
     * @param windowNames the names of all the present windows.
     * @param insertName the name entered.
     * @return true if the name entered match exactly the name of a window actually present;
     *         false otherwise.
     */

    public boolean checkWindowName(ArrayList<String> windowNames, String insertName) {
        for (String windowName : windowNames) if (windowName.equals(insertName)) return true;
        System.out.println(DIGIT_WINDOW);
        System.out.println(WINDOW_NAME_NOT_PRESENT);
        System.out.println();

        return false;
    }

    /**
     * Returns true if the chosen tool card index
     * belongs to the set of tool cards actually present.
     * @param index the chosen tool card index.
     * @return true if the index belongs to the size of the set of tool cards;
     *         false otherwise;
     */

    public boolean checkToolCardInArray(int index) {
        if ((0 <= index) && (index < 12)) return true;
        System.out.println(TOOL_CARD_NOT_PRESENT);
        return false;
    }

    /**
     * Returns true if the choice, expressed by the value of i,
     * to increase or decrease the value of the die is correct.
     * @param i the choice to decrease or increase, as an integer.
     * @return true if i is 1 or if i is 0;
     *         false otherwise.
     */

    public boolean increaseOrDecreaseChoice(int i) {
        if ((i == 0) || (i == 1)) return true;
        System.out.println(INVALID_ENTRY);
        return false;
    }

    /**
     * Returns true if the chosen value belongs to the
     * set of correct dice values.
     * @param i chosen die value.
     * @return true if the chosen value belongs to the set of correct dice values.
     *         false otherwise.
     */

    public boolean checkDieValue(int i) {
        if ((i >= 1) && (i <= 6)) return true;
        System.out.println(INVALID_ENTRY);
        return false;
    }

}