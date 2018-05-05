package it.polimi.ingsw;
import it.polimi.ingsw.windows.Window1;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DiffShadeRowTest {

    @Test
    void checkBrightShade() {
        PublicObjective s = new DiffShadeRow();
        WindowPattern window = new Window1();
        Die die1 = new Die("RED");
        die1.setValue("1");
        window.windowMatrix[0][0].setDie(die1);
        Die die2 = new Die("RED");
        die2.setValue("2");
        window.windowMatrix[1][0].setDie(die2);
        Die die3 = new Die("RED");
        die3.setValue("3");
        window.windowMatrix[2][0].setDie(die3);
        Die die4 = new Die("RED");
        die4.setValue("4");
        window.windowMatrix[3][0].setDie(die4);
        Die die5 = new Die("RED");
        die5.setValue("5");
        window.windowMatrix[4][0].setDie(die5);
        Die die6 = new Die("RED");
        die6.setValue("6");
        window.windowMatrix[2][1].setDie(die6);
        Die die7 = new Die("RED");
        die7.setValue("1");
        window.windowMatrix[4][3].setDie(die7);
        Die die8 = new Die("RED");
        die8.setValue("1");
        window.windowMatrix[3][3].setDie(die8);
        Die die9 = new Die("RED");
        die9.setValue("2");
        window.windowMatrix[0][1].setDie(die9);
        Die die10 = new Die("RED");
        die10.setValue("3");
        window.windowMatrix[0][2].setDie(die10);
        Die die11 = new Die("RED");
        die11.setValue("4");
        window.windowMatrix[0][3].setDie(die11);
        Die die12 = new Die("RED");
        die12.setValue("5");
        window.windowMatrix[1][3].setDie(die12);
        Die die13 = new Die("RED");
        die13.setValue("6");
        window.windowMatrix[3][1].setDie(die13);
        Die die14 = new Die("RED");
        die14.setValue("3");
        window.windowMatrix[2][3].setDie(die14);
        Die die15 = new Die("RED");
        die15.setValue("5");
        window.windowMatrix[3][2].setDie(die15);
        assertEquals(5, s.checkPoints(window));
    }

}
