package it.polimi.ingsw.publicObjectivesTest;
import it.polimi.ingsw.Die;
import it.polimi.ingsw.WindowPattern;
import it.polimi.ingsw.publicObjectivesTest.publicObjectives.DiffColor;
import it.polimi.ingsw.publicObjectivesTest.publicObjectives.PublicObjective;
import it.polimi.ingsw.windows.Window1;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DiffColorTest {

    @Test
    void checkDiffColor ()   {
        PublicObjective s = new DiffColor();
        WindowPattern window = new Window1();

        Die die1 = new Die("RED");
        die1.setValue("1");
        window.getWindowMatrix(0, 0).setDie(die1);
        Die die2 = new Die("GREEN");
        die2.setValue("2");
        window.getWindowMatrix(2, 0).setDie(die2);
        Die die3 = new Die("BLUE");
        die3.setValue("3");
        window.getWindowMatrix(2, 1).setDie(die3);
        Die die4 = new Die("YELLOW");
        die4.setValue("4");
        window.getWindowMatrix(3, 1).setDie(die4);
        Die die5 = new Die("PURPLE");
        die5.setValue("5");
        window.getWindowMatrix(4, 1).setDie(die5);
        Die die6 = new Die("PURPLE");
        die6.setValue("6");
        window.getWindowMatrix(2, 2).setDie(die6);
        Die die7 = new Die("YELLOW");
        die7.setValue("1");
        window.getWindowMatrix(4, 3).setDie(die7);
        Die die8 = new Die("BLUE");
        die8.setValue("1");
        window.getWindowMatrix(4, 0).setDie(die8);
        Die die9 = new Die("GREEN");
        die9.setValue("2");
        window.getWindowMatrix(0, 1).setDie(die9);
        Die die10 = new Die("RED");
        die10.setValue("3");
        window.getWindowMatrix(0, 2).setDie(die10);
        Die die11 = new Die("RED");
        die11.setValue("4");
        window.getWindowMatrix(0, 3).setDie(die11);
        Die die12 = new Die("BLUE");
        die12.setValue("5");
        window.getWindowMatrix(1, 3).setDie(die12);
        Die die13 = new Die("YELLOW");
        die13.setValue("6");
        window.getWindowMatrix(3, 0).setDie(die13);
        Die die14 = new Die("PURPLE");
        die14.setValue("3");
        window.getWindowMatrix(2, 3).setDie(die14);
        Die die15 = new Die("PURPLE");
        die15.setValue("5");
        window.getWindowMatrix(3, 2).setDie(die15);

        assertEquals(8, s.checkPoints(window));
    }

}
