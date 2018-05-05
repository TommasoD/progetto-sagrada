package it.polimi.ingsw.publicObjectiveTest;
import it.polimi.ingsw.Die;
import it.polimi.ingsw.WindowPattern;
import it.polimi.ingsw.publicObjective.DiffColorColumn;
import it.polimi.ingsw.publicObjective.PublicObjective;
import it.polimi.ingsw.windows.Window1;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class DiffColorColumnTest {

    @Test
    void checkColorColumn() {
        WindowPattern window = new Window1();
        PublicObjective obj = new DiffColorColumn();

        //primo indice sono le colonne, secondo indice sono le righe
        window.getWindowMatrix(0, 0).setDie(new Die("RED"));
        window.getWindowMatrix(0, 1).setDie(new Die("YELLOW"));
        window.getWindowMatrix(0, 2).setDie(new Die("GREEN"));
        window.getWindowMatrix(0, 3).setDie(new Die("BLUE"));

        window.getWindowMatrix(2, 0).setDie(new Die("RED"));
        window.getWindowMatrix(2, 1).setDie(new Die("YELLOW"));
        window.getWindowMatrix(2, 2).setDie(new Die("GREEN"));
        window.getWindowMatrix(2, 3).setDie(new Die("PURPLE"));

        window.getWindowMatrix(1, 0).setDie(new Die("PURPLE"));
        window.getWindowMatrix(1, 1).setDie(new Die("BLUE"));
        window.getWindowMatrix(1, 2).setDie(new Die("RED"));
        window.getWindowMatrix(1, 3).setDie(new Die("BLUE"));

        window.getWindowMatrix(3, 3).setDie(new Die("PURPLE"));
        window.getWindowMatrix(3, 1).setDie(new Die("RED"));
        assertEquals(10,obj.checkPoints(window));
    }
}
