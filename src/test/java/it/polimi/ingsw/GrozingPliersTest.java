package it.polimi.ingsw;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GrozingPliersTest {

    @Test
    void effectTest() {
        DraftPool draft = new DraftPool();
        ToolCard tool = new GrozingPliers();
        ToolCardParameters p1 = new ToolCardParameters(draft, 2, "increase");
        ToolCardParameters p2 = new ToolCardParameters(draft, 4, "increase");
        ToolCardParameters p3 = new ToolCardParameters(draft, 0, "decrease");
        ToolCardParameters p4 = new ToolCardParameters(draft, 3, "decrease");
        ToolCardParameters p5 = new ToolCardParameters(draft, 5, "anotherword");
        Die die1 = new Die("RED");
        die1.setValue(1);
        Die die2 = new Die("RED");
        die2.setValue(2);
        Die die3 = new Die("RED");
        die3.setValue(4);
        Die die4 = new Die("RED");
        die4.setValue(4);
        Die die5 = new Die("RED");
        die5.setValue(6);
        Die die6 = new Die("RED");
        die6.setValue(3);
        draft.setDieInDraftPool(die1);
        draft.setDieInDraftPool(die2);
        draft.setDieInDraftPool(die3);
        draft.setDieInDraftPool(die4);
        draft.setDieInDraftPool(die5);
        draft.setDieInDraftPool(die6);
        tool.effect(p1);
        tool.effect(p2);
        tool.effect(p3);
        tool.effect(p4);
        tool.effect(p5);
        assertEquals(5, draft.getDieFromDraft(2).getValueAsInt());
        assertEquals(6, draft.getDieFromDraft(4).getValueAsInt());
        assertEquals(1, draft.getDieFromDraft(0).getValueAsInt());
        assertEquals(3, draft.getDieFromDraft(3).getValueAsInt());
        assertEquals(3, draft.getDieFromDraft(5).getValueAsInt());
    }

}
