package it.polimi.ingsw.modeltests.toolcardtests;

import it.polimi.ingsw.model.DiceBag;
import it.polimi.ingsw.model.Die;
import it.polimi.ingsw.model.WindowPattern;
import it.polimi.ingsw.model.toolcard.DiluentePastaSolida;
import it.polimi.ingsw.model.windows.Window1;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiluentePastaSolidaTest {

    @Test
    void getDieAndSetValue() {
        DiluentePastaSolida tool = new DiluentePastaSolida();
        WindowPattern w = new Window1();
        DiceBag db = new DiceBag();
        Die d = new Die("RED");

        tool.getDieAndSetValue(w, 5, 2, 2, d);
        assertEquals(5, w.getWindowMatrix(2, 2).getDie().getValueAsInt());
    }
}