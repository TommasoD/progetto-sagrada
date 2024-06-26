package it.polimi.ingsw.model.objectives.publicobjectives;
import it.polimi.ingsw.model.Die;
import it.polimi.ingsw.model.WindowPattern;
import it.polimi.ingsw.model.WindowPatternFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ColoredDiagonalTest {

    @Test
    void checkColoredDiagonal() {
        PublicObjective s = new ColoredDiagonal();
        WindowPatternFactory factory = new WindowPatternFactory();
        WindowPattern window = factory.createWindow("Kaleidoscopic Dream");
        window.getWindowMatrix(0, 2).setDie(new Die("BLUE"));
        window.getWindowMatrix(1, 1).setDie(new Die("BLUE"));
        window.getWindowMatrix(2, 2).setDie(new Die("BLUE"));
        window.getWindowMatrix(3, 3).setDie(new Die("BLUE"));
        window.getWindowMatrix(4, 1).setDie(new Die("YELLOW"));
        window.getWindowMatrix(3, 2).setDie(new Die("YELLOW"));
        window.getWindowMatrix(2, 3).setDie(new Die("YELLOW"));
        window.getWindowMatrix(1, 2).setDie(new Die("PURPLE"));
        window.getWindowMatrix(0, 0).setDie(new Die("RED"));
        window.getWindowMatrix(1, 0).setDie(new Die("RED"));
        window.getWindowMatrix(1, 3).setDie(new Die("PURPLE"));
        window.getWindowMatrix(4, 0).setDie(new Die("GREEN"));
        assertEquals(8, s.checkPoints(window));
    }

}