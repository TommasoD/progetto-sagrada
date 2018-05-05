package it.polimi.ingsw;
import it.polimi.ingsw.windows.Window1;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class DiffColorColumnTest {

    @Test
    void checkColorColumnTest () {
        WindowPattern window = new Window1();
        PublicObjective obj = new DiffColorColumn();

        //primo indice sono le colonne, secondo indice sono le righe
        window.windowMatrix[0][0].setDie(new Die("RED"));
        window.windowMatrix[0][1].setDie(new Die("YELLOW"));
        window.windowMatrix[0][2].setDie(new Die("GREEN"));
        window.windowMatrix[0][3].setDie(new Die("BLUE"));

        window.windowMatrix[2][0].setDie(new Die("RED"));
        window.windowMatrix[2][1].setDie(new Die("YELLOW"));
        window.windowMatrix[2][2].setDie(new Die("GREEN"));
        window.windowMatrix[2][3].setDie(new Die("PURPLE"));

        window.windowMatrix[1][0].setDie(new Die("PURPLE"));
        window.windowMatrix[1][1].setDie(new Die("BLUE"));
        window.windowMatrix[1][2].setDie(new Die("RED"));
        window.windowMatrix[1][3].setDie(new Die("BLUE"));

        window.windowMatrix[3][3].setDie(new Die("PURPLE"));
        window.windowMatrix[3][1].setDie(new Die("RED"));
        assertEquals(10,obj.checkPoints(window));
    }
}
