package it.polimi.ingsw.toolcardtests;
import it.polimi.ingsw.Die;
import it.polimi.ingsw.WindowPattern;
import it.polimi.ingsw.toolcard.CopperFoilBurnisher;
import it.polimi.ingsw.windows.Window3;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CopperFoilBurnisherTest {

    @Test
    void effectTest() {
        WindowPattern window = new Window3();
        CopperFoilBurnisher tool = new CopperFoilBurnisher();
        Die die1 = new Die("RED");
        die1.setValue("5");
        window.getWindowMatrix(0, 0).setDie(die1);
        Die die2 = new Die("YELLOW");
        die2.setValue("5");
        window.getWindowMatrix(2, 0).setDie(die2);
        Die die3 = new Die("BLUE");
        die3.setValue("1");
        window.getWindowMatrix(2, 1).setDie(die3);
        Die die4 = new Die("PURPLE");
        die4.setValue("6");
        window.getWindowMatrix(3, 1).setDie(die4);
        Die die5 = new Die("GREEN");
        die5.setValue("6");
        window.getWindowMatrix(4, 1).setDie(die5);
        Die die6 = new Die("GREEN");
        die6.setValue("5");
        window.getWindowMatrix(2, 2).setDie(die6);
        Die die7 = new Die("BLUE");
        die7.setValue("3");
        window.getWindowMatrix(4, 3).setDie(die7);
        tool.effect3(window, 0, 0, 2, 3);
        assertEquals(die1, window.getWindowMatrix(2, 3).getDie());
        assertEquals(null, window.getWindowMatrix(0,0).getDie());
    }

}
