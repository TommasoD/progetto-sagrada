package it.polimi.ingsw.model.objectives.publicobjectives;
import it.polimi.ingsw.model.Die;
import it.polimi.ingsw.model.WindowPattern;
import it.polimi.ingsw.model.windows.Window1;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MediumShadeTest {
    @Test
    void checkMediumShade() {
        PublicObjective obj = new MediumShade();
        WindowPattern window = new Window1();

        Die die1 = new Die("RED");
        die1.setValue("1");
        window.getWindowMatrix(0, 0).setDie(die1);
        Die die2 = new Die("RED");
        die2.setValue("3");
        window.getWindowMatrix(2, 0).setDie(die2);
        Die die3 = new Die("RED");
        die3.setValue("3");
        window.getWindowMatrix(2, 1).setDie(die3);
        Die die4 = new Die("RED");
        die4.setValue("4");
        window.getWindowMatrix(3, 1).setDie(die4);
        Die die5 = new Die("RED");
        die5.setValue("4");
        window.getWindowMatrix(4, 1).setDie(die5);
        Die die6 = new Die("RED");
        die6.setValue("4");
        window.getWindowMatrix(2, 2).setDie(die6);
        Die die7 = new Die("RED");
        die7.setValue("6");

        window.getWindowMatrix(4, 3).setDie(die7);
        assertEquals(4, obj.checkPoints(window));

    }

}
