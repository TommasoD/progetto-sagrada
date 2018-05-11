package it.polimi.ingsw.toolcardtests;
import it.polimi.ingsw.Die;
import it.polimi.ingsw.WindowPattern;
import it.polimi.ingsw.toolcard.Lathekin;
import it.polimi.ingsw.windows.Window1;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LathekinTest {
    @Test
    void effectTest() {
        WindowPattern window = new Window1();
        Lathekin tool = new Lathekin();
        Die die = new Die("RED");
        window.getWindowMatrix(1,1).setDie(die);
        tool.effect(window, 1 ,1,2,2);
        assertEquals(die,window.getWindowMatrix(2,2).getDie());
    }
}
