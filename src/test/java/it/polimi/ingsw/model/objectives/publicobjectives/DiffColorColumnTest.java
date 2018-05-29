package it.polimi.ingsw.model.objectives.publicobjectives;
import it.polimi.ingsw.model.Die;
import it.polimi.ingsw.model.WindowPattern;
import it.polimi.ingsw.model.WindowPatternFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class DiffColorColumnTest {

    @Test
    void checkColorColumn() {
        WindowPatternFactory factory = new WindowPatternFactory();
        WindowPattern window = factory.createWindow("Kaleidoscopic Dream");
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
