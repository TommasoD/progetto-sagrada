package it.polimi.ingsw;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DraftPoolTest {

    @Test
    void getDraftPoolSize() {
        DraftPool dp = new DraftPool();
        Die d1 = new Die("RED");
        Die d2 = new Die("PURPLE");
        Die d3 = new Die("RED");
        dp.setDieInDraftPool(d1);
        dp.setDieInDraftPool(d2);
        dp.setDieInDraftPool(d3);
        dp.removeDieFromDraft(2);
        assertEquals(2, dp.getDraftPoolSize());
    }

    @Test
    void emptyDraftPool() {
        DraftPool dp = new DraftPool();
        assertEquals(0, dp.getDraftPoolSize());
    }

    @Test
    void getDieFromDraft() {
        DraftPool dp = new DraftPool();
        Die d1 = new Die("RED");
        Die d2 = new Die("PURPLE");
        Die d3 = new Die("RED");
        dp.setDieInDraftPool(d1);
        dp.setDieInDraftPool(d2);
        dp.setDieInDraftPool(d3);
        assertEquals(d2,dp.getDieFromDraft(1));
    }

}
