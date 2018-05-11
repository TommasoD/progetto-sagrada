package it.polimi.ingsw;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GrozingPliersTest {

    @Test
    void effectTest() {
        DraftPool draft = new DraftPool();
        GrozingPliers tool = new GrozingPliers();
        Die die1 = new Die("RED");
        die1.setValue(2);
        Die die2 = new Die("RED");
        die2.setValue(4);
        draft.setDieInDraftPool(die1);
        draft.setDieInDraftPool(die2);
        tool.effect1(draft, 0, "increase");
        tool.effect1(draft, 1, "decrease");
        assertEquals(3, draft.getDieFromDraft(0).getValueAsInt());
        assertEquals(3, draft.getDieFromDraft(1).getValueAsInt());
    }

}
