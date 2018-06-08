package it.polimi.ingsw;

import it.polimi.ingsw.client.ValidateInput;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ValidateInputTest {

    @Test
    void ValidCheckDieRoundTrack() {
        int index = 3;
        int size = 5;
        ValidateInput validateInput = new ValidateInput();
        assertTrue(validateInput.checkDieInArray(index, size));
    }

    @Test
    void InvalidCheckDieRoundTrack() {
        int index = 8;
        int size = 5;
        ValidateInput validateInput = new ValidateInput();
        assertFalse(validateInput.checkDieInArray(index, size));
    }

    @Test
    void  ValidCheckColumnIndex() {
        int index = 2;
        ValidateInput validateInput = new ValidateInput();
        assertTrue(validateInput.checkColumnIndex(index));
    }

    @Test
    void  InvalidCheckColumnIndex() {
        int index = -23;
        ValidateInput validateInput = new ValidateInput();
        assertFalse(validateInput.checkColumnIndex(index));
    }

    @Test
    void  ValidCheckRowIndex() {
        int index = 3;
        ValidateInput validateInput = new ValidateInput();
        assertTrue(validateInput.checkRowIndex(index));
    }

    @Test
    void  InvalidCheckRowIndex() {
        int index = -23;
        ValidateInput validateInput = new ValidateInput();
        assertFalse(validateInput.checkRowIndex(index));
    }


    @Test
    void  ValidCheckWindowName() {
        ArrayList<String> windowNames = new ArrayList<String>();
        windowNames.add("Aurorae Magnificus");
        windowNames.add("Bellesguard");
        windowNames.add("Batllo");
        windowNames.add("Sun Catcher");
        String name = "Sun Catcher";
        ValidateInput validateInput = new ValidateInput();
        assertTrue(validateInput.checkWindowName(windowNames, name));
    }


    @Test
    void  InvalidCheckWindowName() {
        ArrayList<String> windowNames = new ArrayList<String>();
        windowNames.add("Aurorae Magnificus");
        windowNames.add("Bellesguard");
        windowNames.add("Batllo");
        windowNames.add("Sun Catcher");
        String name = "Chromatic Splendor";
        ValidateInput validateInput = new ValidateInput();
        assertFalse(validateInput.checkWindowName(windowNames, name));
    }

    @Test
    void ValidCheckToolCardInArray() {
        int index = 4;
        ValidateInput validateInput = new ValidateInput();
        assertTrue(validateInput.checkToolCardInArray(index));
    }

    @Test
    void InvalidCheckToolCardInArray() {
        int index = 23;
        ValidateInput validateInput = new ValidateInput();
        assertFalse(validateInput.checkToolCardInArray(index));
    }

}
