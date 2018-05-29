package it.polimi.ingsw.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WindowPatternTest {

    @Test
    void tokenTest() {
        WindowPatternFactory factory = new WindowPatternFactory();
        WindowPattern w = factory.createWindow("Kaleidoscopic Dream");
        assertEquals(4, w.getDifficultyToken());
        int cost = 2;
        w.decreaseDifficultyToken(cost);
        assertEquals(2, w.getDifficultyToken());
    }

    @Test
    void correctName() {
        WindowPatternFactory factory = new WindowPatternFactory();
        WindowPattern w = factory.createWindow("Kaleidoscopic Dream");
        assertEquals("Kaleidoscopic Dream", w.getName());
    }

    @Test
    void valid() {
        WindowPatternFactory factory = new WindowPatternFactory();
        WindowPattern w = factory.createWindow("Kaleidoscopic Dream");
        Die d = new Die("BLUE");
        d.roll();
        Die d2 = new Die("RED");
        d2.roll();
        w.getWindowMatrix(2, 2).setDie(d);
        assertTrue(w.isValid(1, 1, d2));
    }

    @Test
    void valid2() {
        WindowPatternFactory factory = new WindowPatternFactory();
        WindowPattern w = factory.createWindow("Kaleidoscopic Dream");
        Die d = new Die("BLUE");
        d.roll();
        Die d2 = new Die("YELLOW");
        d2.roll();
        w.getWindowMatrix(1, 1).setDie(d);
        assertTrue(w.isValid(0, 0, d2));
    }

    @Test
    void notValidColorRuleSlot() {
        WindowPatternFactory factory = new WindowPatternFactory();
        WindowPattern w = factory.createWindow("Kaleidoscopic Dream");
        Die d = new Die("BLUE");
        d.roll();
        assertFalse(w.isValid(2,2 , d));
    }

    @Test
    void notValidValueRuleSlot() {
        WindowPatternFactory factory = new WindowPatternFactory();
        WindowPattern w = factory.createWindow("Kaleidoscopic Dream");
        Die d = new Die("BLUE");
        d.setValue("5");
        assertFalse(w.isValid(4,0 , d));
    }

    @Test
    void notValidOccupiedSlot() {
        WindowPatternFactory factory = new WindowPatternFactory();
        WindowPattern w = factory.createWindow("Kaleidoscopic Dream");
        Die d = new Die("RED");
        d.roll();
        w.getWindowMatrix(2, 2).setDie(d);
        Die d2 = new Die("RED");
        d2.roll();
        assertFalse(w.isValid(2,2 , d));
    }

    @Test
    void notValidOrthogonalColor() {
        WindowPatternFactory factory = new WindowPatternFactory();
        WindowPattern w = factory.createWindow("Kaleidoscopic Dream");
        Die d = new Die("RED");
        d.roll();
        w.getWindowMatrix(2, 2).setDie(d);
        Die d2 = new Die("RED");
        d.roll();
        assertFalse(w.isValid(3, 2, d2));
    }

    @Test
    void notValidOrthogonalValue() {
        WindowPatternFactory factory = new WindowPatternFactory();
        WindowPattern w = factory.createWindow("Kaleidoscopic Dream");
        Die d = new Die("RED");
        d.setValue("2");
        w.getWindowMatrix(2, 2).setDie(d);
        Die d2 = new Die("RED");
        d.setValue("2");
        assertFalse(w.isValid(3, 2, d2));
    }

    @Test
    void notValidAdjacent() {
        WindowPatternFactory factory = new WindowPatternFactory();
        WindowPattern w = factory.createWindow("Kaleidoscopic Dream");
        Die d = new Die("RED");
        d.roll();
        assertFalse(w.isValid(3, 2, d));
    }

    @Test
    void validFirstMove(){
        WindowPatternFactory wf = new WindowPatternFactory();
        WindowPattern w = wf.createWindow("Kaleidoscopic Dream");
        Die d = new Die("RED");
        d.roll();
        assertTrue(w.isValidFirstMove(2, 0, d));
    }

    @Test
    void invalidFirstMove(){
        WindowPatternFactory wf = new WindowPatternFactory();
        WindowPattern w = wf.createWindow("Kaleidoscopic Dream");
        Die d = new Die("RED");
        d.roll();
        assertFalse(w.isValidFirstMove(1, 1, d));
    }

    @Test
    void invalidFirstMove2(){
        WindowPatternFactory wf = new WindowPatternFactory();
        WindowPattern w = wf.createWindow("Kaleidoscopic Dream");
        Die d = new Die("RED");
        d.roll();
        assertFalse(w.isValidFirstMove(0, 0, d));
    }
}