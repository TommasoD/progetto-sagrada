package it.polimi.ingsw;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LensCutterTest {

    @Test
    void effectTest() {
        DraftPool draft = new DraftPool();
        ArrayList<Die> track = new ArrayList<Die>();
        ToolCard tool = new LensCutter();
        ToolCardParameters p = new ToolCardParameters(draft, track, 0, 2);
        Die die1 = new Die("RED");
        die1.setValue(1);
        Die die2 = new Die("BLUE");
        die2.setValue(2);
        Die die3 = new Die("YELLOW");
        die3.setValue(4);
        Die die4 = new Die("GREEN");
        die4.setValue(4);
        Die die5 = new Die("PURPLE");
        die5.setValue(6);
        Die die6 = new Die("BLUE");
        die6.setValue(3);
        draft.setDieInDraftPool(die1);
        draft.setDieInDraftPool(die2);
        draft.setDieInDraftPool(die3);
        track.add(die4);
        track.add(die5);
        track.add(die6);
        tool.effect(p);
        assertEquals(die2, draft.getDieFromDraft(0));
        assertEquals(die3, draft.getDieFromDraft(1));
        assertEquals(die6, draft.getDieFromDraft(2));
        assertEquals(die4, track.get(0));
        assertEquals(die5, track.get(1));
        assertEquals(die1, track.get(2));
    }

}
