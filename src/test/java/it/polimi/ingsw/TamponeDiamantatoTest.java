package it.polimi.ingsw;
import it.polimi.ingsw.windows.Window1;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TamponeDiamantatoTest {

    @Test
    void effectTest() {
        DraftPool draftPool = new DraftPool();
        Die die = new Die("RED");
        die.setValue(6);
        draftPool.setDieInDraftPool(die);
        ToolCard tool = new TamponeDiamantato();
        ((TamponeDiamantato) tool).effect(draftPool,0);
        assertEquals(1,die.getValueAsInt());
    }
}
