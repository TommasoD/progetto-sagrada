package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Die;
import it.polimi.ingsw.model.WindowPattern;
import it.polimi.ingsw.model.WindowPatternFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToolCardCheckTest {

    @Test
    void toolCard1() {
        ToolCardCheck tc = new ToolCardCheck();
        Die d1 = new Die("RED");
        d1.setValue(4);
        assertTrue(tc.toolCard1(d1, 0));
        assertTrue(tc.toolCard1(d1, 1));
        d1.setValue(1);
        assertFalse(tc.toolCard1(d1, 0));
        assertTrue(tc.toolCard1(d1, 1));
        d1.setValue(6);
        assertTrue(tc.toolCard1(d1, 0));
        assertFalse(tc.toolCard1(d1, 1));
    }

    @Test
    void toolCard2() {
        WindowPatternFactory factory = new WindowPatternFactory();
        WindowPattern w = factory.createWindow("Kaleidoscopic Dream");
        ToolCardCheck tc = new ToolCardCheck();
        Die d = new Die("BLUE");
        d.roll();
        Die d2 = new Die("YELLOW");
        d2.setValue(5);
        w.getWindowMatrix(3, 3).setDie(d);
        w.getWindowMatrix(0,0).setDie(d2);
        assertTrue(tc.toolCard2(w, 0, 0, 2, 2));
        assertFalse(tc.toolCard2(w, 0, 1, 2, 2));
        Die d3 = new Die("RED");
        w.getWindowMatrix(4, 2).setDie(d3);
        assertFalse(tc.toolCard2(w, 0, 0, 3, 3));
    }

    @Test
    void toolCard3() {
        WindowPatternFactory factory = new WindowPatternFactory();
        WindowPattern w = factory.createWindow("Kaleidoscopic Dream");
        ToolCardCheck tc = new ToolCardCheck();
        Die d = new Die("BLUE");
        d.setValue(1);
        Die d2 = new Die("YELLOW");
        d2.setValue(3);
        w.getWindowMatrix(0, 1).setDie(d);
        w.getWindowMatrix(0,0).setDie(d2);
        assertTrue(tc.toolCard3(w, 0, 0, 1, 2));
        assertFalse(tc.toolCard3(w, 0, 2, 2, 2));
        Die d3 = new Die("RED");
        w.getWindowMatrix(4, 2).setDie(d3);
        assertFalse(tc.toolCard3(w, 0, 0, 3, 3));
    }

    @Test
    void toolCard4() {
        WindowPatternFactory factory = new WindowPatternFactory();
        WindowPattern w = factory.createWindow("Kaleidoscopic Dream");
        ToolCardCheck tc = new ToolCardCheck();
        Die d = new Die("BLUE");
        d.setValue(5);
        Die d2 = new Die("YELLOW");
        d2.setValue(5);
        w.getWindowMatrix(0, 1).setDie(d);
        w.getWindowMatrix(0,0).setDie(d2);
        Die d3 = new Die("BLUE");
        w.getWindowMatrix(3, 2).setDie(d3);
        assertTrue(tc.toolCard4(w, 0, 0, 4, 3, 0, 1, 2, 3));
        assertFalse(tc.toolCard4(w, 0, 0, 4, 3, 0, 0, 1, 2));
        assertFalse(tc.toolCard4(w, 0, 0, 2, 1, 0, 1, 2, 1));
        assertFalse(tc.toolCard4(w, 0, 2, 2, 1, 0, 1, 2, 1));
        assertFalse(tc.toolCard4(w, 0, 0, 2, 1, 0, 2, 2, 1));

    }

    @Test
    void toolCard8() {
    }

    @Test
    void toolCard9() {
    }

    @Test
    void toolCard12() {
    }
}