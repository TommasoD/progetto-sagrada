package it.polimi.ingsw.modeltests.toolcardtests;
import it.polimi.ingsw.model.Die;
import it.polimi.ingsw.model.DraftPool;
import it.polimi.ingsw.model.toolcard.TamponeDiamantato;
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
