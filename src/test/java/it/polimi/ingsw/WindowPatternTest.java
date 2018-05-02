package it.polimi.ingsw;
import it.polimi.ingsw.windows.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WindowPatternTest {
                                        /*
                                        ** more tests must be added
                                        **/
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
    void print(){
        WindowPattern w = new Window1();
        w.dump();
    }

    @Test
    void valid() {
        WindowPattern w = new Window1();
        Die d = new Die("BLUE");
        d.roll();
        d.dump();
        Die d2 = new Die("RED");
        d2.roll();
        d2.dump();
        w.windowMatrix[23].setDie(d);
        assertEquals(true, w.isValid(24, d2));
    }

    @Test
    void notValid() {
        WindowPattern w = new Window1();
        Die d = new Die("BLUE");
        d.roll();
        assertEquals(false, w.isValid(24, d));
    }

    @Test
    void notValid2() {
        WindowPattern w = new Window1();
        Die d = new Die("RED");
        d.roll();
        w.windowMatrix[24].setDie(d);
        Die d2 = new Die("RED");
        d.roll();
        assertEquals(false, w.isValid(24, d2));
    }
}