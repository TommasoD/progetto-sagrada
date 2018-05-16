package it.polimi.ingsw.modeltests;
import it.polimi.ingsw.model.Die;
import it.polimi.ingsw.model.WindowPattern;
import it.polimi.ingsw.model.windows.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WindowPatternTest {

    @Test
    void tokenTest() {
        WindowPattern w = new Window1();
        assertEquals(4, w.getDifficultyToken());
        int cost = 2;
        w.decreaseDifficultyToken(cost);
        assertEquals(2, w.getDifficultyToken());
    }

    @Test
    void correctName() {
        WindowPattern w = new Window1();
        assertEquals("Kaleidoscopic Dream", w.getName());
    }

    @Test
    void valid() {
        WindowPattern w = new Window1();
        Die d = new Die("BLUE");
        d.roll();
        Die d2 = new Die("RED");
        d2.roll();
        w.getWindowMatrix(2, 2).setDie(d);
        assertEquals(true, w.isValid(1, 1, d2));
    }

    @Test
    void valid2() {
        WindowPattern w = new Window1();
        Die d = new Die("BLUE");
        d.roll();
        Die d2 = new Die("YELLOW");
        d2.roll();
        w.getWindowMatrix(1, 1).setDie(d);
        assertEquals(true, w.isValid(0, 0, d2));
    }

    @Test
    void notValidColorRuleSlot() {
        WindowPattern w = new Window1();
        Die d = new Die("BLUE");
        d.roll();
        assertEquals(false, w.isValid(2,2 , d));
    }

    @Test
    void notValidValueRuleSlot() {
        WindowPattern w = new Window1();
        Die d = new Die("BLUE");
        d.setValue("5");
        assertEquals(false, w.isValid(4,0 , d));
    }

    @Test
    void notValidOccupiedSlot() {
        WindowPattern w = new Window1();
        Die d = new Die("RED");
        d.roll();
        w.getWindowMatrix(2, 2).setDie(d);
        Die d2 = new Die("RED");
        d2.roll();
        assertEquals(false, w.isValid(2,2 , d));
    }

    @Test
    void notValidOrthogonalColor() {
        WindowPattern w = new Window1();
        Die d = new Die("RED");
        d.roll();
        w.getWindowMatrix(2, 2).setDie(d);
        Die d2 = new Die("RED");
        d.roll();
        assertEquals(false, w.isValid(3, 2, d2));
    }

    @Test
    void notValidOrthogonalValue() {
        WindowPattern w = new Window1();
        Die d = new Die("RED");
        d.setValue("2");
        w.getWindowMatrix(2, 2).setDie(d);
        Die d2 = new Die("RED");
        d.setValue("2");
        assertEquals(false, w.isValid(3, 2, d2));
    }

    @Test
    void notValidAdjacent() {
        WindowPattern w = new Window1();
        Die d = new Die("RED");
        d.roll();
        assertEquals(false, w.isValid(3, 2, d));
    }
}