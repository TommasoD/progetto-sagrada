package it.polimi.ingsw;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GlazingHammerTest {

    @Test
    void effectTest() {
        DraftPool draft = new DraftPool();
        GlazingHammer tool = new GlazingHammer();
        Die die1 = new Die("RED");
        die1.setValue(1);
        Die die2 = new Die("YELLOW");
        die2.setValue(2);
        Die die3 = new Die("BLUE");
        die3.setValue(4);
        Die die4 = new Die("RED");
        die4.setValue(4);
        Die die5 = new Die("PURPLE");
        die5.setValue(6);
        Die die6 = new Die("GREEN");
        die6.setValue(3);
        draft.setDieInDraftPool(die1);
        draft.setDieInDraftPool(die2);
        draft.setDieInDraftPool(die3);
        draft.setDieInDraftPool(die4);
        draft.setDieInDraftPool(die5);
        draft.setDieInDraftPool(die6);
        tool.effect7(draft);
        assertEquals("RED", draft.getDieFromDraft(0).getColor());
        assertEquals("YELLOW", draft.getDieFromDraft(1).getColor());
        assertEquals("BLUE", draft.getDieFromDraft(2).getColor());
        assertEquals("RED", draft.getDieFromDraft(3).getColor());
        assertEquals("PURPLE", draft.getDieFromDraft(4).getColor());
        assertEquals("GREEN", draft.getDieFromDraft(5).getColor());
        assertEquals(6, draft.getDraftPoolSize());
    }

}
