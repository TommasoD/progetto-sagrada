package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Die;
import it.polimi.ingsw.model.WindowPattern;
import it.polimi.ingsw.model.WindowPatternFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
        WindowPatternFactory factory = new WindowPatternFactory();
        WindowPattern w = factory.createWindow("Kaleidoscopic Dream");
        ToolCardCheck tc = new ToolCardCheck();
        Die d = new Die("BLUE");
        d.roll();
        Die d2 = new Die("RED");
        d2.roll();
        w.getWindowMatrix(2, 2).setDie(d);
        assertTrue(tc.toolCard8(w, d2, 1, 1));
        Die d3 = new Die("BLUE");
        d3.roll();
        Die d4 = new Die("YELLOW");
        d4.roll();
        w.getWindowMatrix(1, 1).setDie(d3);
        assertTrue(tc.toolCard8(w, d4, 0, 0));
    }

    @Test
    void toolCard9() {
        WindowPatternFactory factory = new WindowPatternFactory();
        WindowPattern w = factory.createWindow("Kaleidoscopic Dream");
        ToolCardCheck tc = new ToolCardCheck();
        Die d = new Die("BLUE");
        d.roll();
        Die d2 = new Die("YELLOW");
        d2.setValue(5);
        w.getWindowMatrix(3, 3).setDie(d);
        assertTrue(tc.toolCard9(w, d2, 0, 0));
        assertFalse(tc.toolCard9(w, d2, 2, 3));
        assertFalse(tc.toolCard9(w, d2, 0, 1));
    }

    @Test
    void toolCard12() {
        WindowPatternFactory factory = new WindowPatternFactory();
        WindowPattern w = factory.createWindow("Kaleidoscopic Dream");
        ArrayList<Die> rt = new ArrayList<Die>();
        rt.add(new Die("RED"));
        rt.add(new Die("RED"));
        rt.add(new Die("BLUE"));
        rt.add(new Die("YELLOW"));
        rt.add(new Die("YELLOW"));
        rt.add(new Die("BLUE"));
        rt.add(new Die("RED"));
        ToolCardCheck tc = new ToolCardCheck();
        Die d = new Die("BLUE");
        d.setValue(5);
        Die d2 = new Die("YELLOW");
        d2.setValue(5);
        w.getWindowMatrix(0, 1).setDie(d);
        w.getWindowMatrix(0,0).setDie(d2);
        Die d3 = new Die("RED");
        d3.setValue(1);
        w.getWindowMatrix(3, 2).setDie(d3);
        assertFalse(tc.toolCard12(rt, w, 0, 0, 4, 3, 0, 1, 2, 3));
        assertFalse(tc.toolCard12(rt, w, 0, 0, 4, 3, 0, 0, 1, 2));
        assertFalse(tc.toolCard12(rt, w, 0, 0, 2, 1, 0, 1, 2, 1));
        assertFalse(tc.toolCard12(rt, w, 0, 2, 2, 1, 0, 1, 2, 1));
        assertFalse(tc.toolCard12(rt, w, 0, 0, 2, 1, 0, 2, 2, 1));
        Die d4 = new Die("BLUE");
        d4.setValue(2);
        w.getWindowMatrix(1, 0).setDie(d4);
        assertTrue(tc.toolCard12(rt, w, 1, 0, 3, 1, 0, 1, 2, 3));
        Die d5 = new Die("PURPLE");
        Die d6 = new Die("PURPLE");
        d5.setValue(2);
        d5.setValue(5);
        w.getWindowMatrix(0, 3).setDie(d5);
        w.getWindowMatrix(0, 2).setDie(d6);
        assertFalse(tc.toolCard12(rt, w, 0, 3, 3, 1, 0, 2, 2, 3));
    }
}