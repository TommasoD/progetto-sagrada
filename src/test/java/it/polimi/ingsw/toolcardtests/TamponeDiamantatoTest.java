package it.polimi.ingsw.toolcardtests;
import it.polimi.ingsw.Die;
import it.polimi.ingsw.DraftPool;
import it.polimi.ingsw.toolcard.TamponeDiamantato;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TamponeDiamantatoTest {

    @Test
    void effectTest() {
        DraftPool draftPool = new DraftPool();
        Die die = new Die("RED");
        die.setValue(6);
        draftPool.setDieInDraftPool(die);
        TamponeDiamantato tool = new TamponeDiamantato();
        tool.effect(draftPool,0);
        assertEquals(1,die.getValueAsInt());
    }
}
