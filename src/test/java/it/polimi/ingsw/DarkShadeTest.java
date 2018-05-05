package it.polimi.ingsw;

import it.polimi.ingsw.windows.Window1;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DarkShadeTest {

    @Test
    void checkDarkShade () {
        WindowPattern window = new Window1();
        PublicObjective obj = new DarkShade();

        Die die1 = new Die("RED");
        die1.setValue("5");
        window.windowMatrix[0][0].setDie(die1);
        Die die2 = new Die("RED");
        die2.setValue("5");
        window.windowMatrix[2][0].setDie(die2);
        Die die3 = new Die("RED");
        die3.setValue("1");
        window.windowMatrix[2][1].setDie(die3);
        Die die4 = new Die("RED");
        die4.setValue("6");
        window.windowMatrix[3][1].setDie(die4);
        Die die5 = new Die("RED");
        die5.setValue("6");
        window.windowMatrix[4][1].setDie(die5);
        Die die6 = new Die("RED");
        die6.setValue("5");
        window.windowMatrix[2][2].setDie(die6);
        Die die7 = new Die("RED");
        die7.setValue("3");
        window.windowMatrix[4][3].setDie(die7);
        assertEquals(4, obj.checkPoints(window));

    }

}
