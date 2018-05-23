package it.polimi.ingsw.model.objectives.publicobjectives;
import it.polimi.ingsw.model.Die;
import it.polimi.ingsw.model.WindowPattern;
import it.polimi.ingsw.model.windows.Window1;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DiffColorRowTest {

    @Test
    void checkColorRow() {
        PublicObjective s = new DiffColorRow();
        WindowPattern window = new Window1();
        window.getWindowMatrix(0, 1).setDie(new Die("RED"));
        window.getWindowMatrix(1, 1).setDie(new Die("YELLOW"));
        window.getWindowMatrix(2, 1).setDie(new Die("GREEN"));
        window.getWindowMatrix(3, 1).setDie(new Die("BLUE"));
        window.getWindowMatrix(4, 1).setDie(new Die("PURPLE"));
        window.getWindowMatrix(0, 3).setDie(new Die("RED"));
        window.getWindowMatrix(1, 3).setDie(new Die("YELLOW"));
        window.getWindowMatrix(2, 3).setDie(new Die("GREEN"));
        window.getWindowMatrix(3, 3).setDie(new Die("PURPLE"));
        window.getWindowMatrix(4, 3).setDie(new Die("BLUE"));
        window.getWindowMatrix(0, 0).setDie(new Die("PURPLE"));
        window.getWindowMatrix(1, 0).setDie(new Die("GREEN"));
        window.getWindowMatrix(2, 0).setDie(new Die("RED"));
        window.getWindowMatrix(3, 0).setDie(new Die("BLUE"));
        window.getWindowMatrix(4, 0).setDie(new Die("BLUE"));
        window.getWindowMatrix(0, 2).setDie(new Die("PURPLE"));
        window.getWindowMatrix(3, 2).setDie(new Die("GREEN"));
        window.getWindowMatrix(2, 2).setDie(new Die("RED"));
        assertEquals(12, s.checkPoints(window));
    }

}
