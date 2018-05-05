package it.polimi.ingsw;
import it.polimi.ingsw.windows.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WindowPatternTest {

    @Test
    void tokenTest() {
        WindowPattern w = new Window1();
        assertEquals(4, w.getDifficultyToken());
        w.decreaseDifficultyToken();
        assertEquals(3, w.getDifficultyToken());
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
        w.windowMatrix[2][2].setDie(d);
        assertEquals(true, w.isValid(1, 1, d2));
    }

    @Test
    void valid2() {
        WindowPattern w = new Window1();
        Die d = new Die("BLUE");
        d.roll();
        Die d2 = new Die("YELLOW");
        d2.roll();
        w.windowMatrix[1][1].setDie(d);
        assertEquals(true, w.isValid(0, 0, d2));
    }

    @Test
    void notValidColorRuleSlot() {                                  //valueRule
        WindowPattern w = new Window1();
        Die d = new Die("BLUE");
        d.roll();
        assertEquals(false, w.isValid(2,2 , d));
    }

    @Test
    void notValidOccupiedSlot() {
        WindowPattern w = new Window1();
        Die d = new Die("RED");
        d.roll();
        w.windowMatrix[2][2].setDie(d);
        Die d2 = new Die("RED");
        d2.roll();
        assertEquals(false, w.isValid(2,2 , d));
    }

    @Test
    void notValidOrthogonalColor() {                                //value
        WindowPattern w = new Window1();
        Die d = new Die("RED");
        d.roll();
        w.windowMatrix[2][2].setDie(d);
        Die d2 = new Die("RED");
        d.roll();
        assertEquals(false, w.isValid(3, 2, d2));
    }

    @Test
    void notValidAdjacent() {                                //value
        WindowPattern w = new Window1();
        Die d = new Die("RED");
        d.roll();
        assertEquals(false, w.isValid(3, 2, d));
    }
}