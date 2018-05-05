package it.polimi.ingsw;
import it.polimi.ingsw.windows.Window1;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ColoredDiagonalTest {

    @Test
    void checkColoredDiagonal() {
        PublicObjective s = new ColoredDiagonal();
        WindowPattern window = new Window1();
        window.windowMatrix[0][2].setDie(new Die("BLUE"));
        window.windowMatrix[1][1].setDie(new Die("BLUE"));
        window.windowMatrix[2][2].setDie(new Die("BLUE"));
        window.windowMatrix[3][3].setDie(new Die("BLUE"));
        window.windowMatrix[4][1].setDie(new Die("YELLOW"));
        window.windowMatrix[3][2].setDie(new Die("YELLOW"));
        window.windowMatrix[2][3].setDie(new Die("YELLOW"));
        window.windowMatrix[1][2].setDie(new Die("PURPLE"));
        window.windowMatrix[0][0].setDie(new Die("RED"));
        window.windowMatrix[1][0].setDie(new Die("RED"));
        window.windowMatrix[1][3].setDie(new Die("PURPLE"));
        window.windowMatrix[4][0].setDie(new Die("GREEN"));
        assertEquals(8, s.checkPoints(window));
    }

}