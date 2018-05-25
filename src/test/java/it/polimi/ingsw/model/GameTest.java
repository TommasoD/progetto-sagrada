package it.polimi.ingsw.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {


    @Test
    void roundTrackTest(){
        Game g = new Game();
        Die d = new Die("RED");
        g.setDieRoundTrack(d);
        assertEquals(d, g.getDieFromRoundTrack(0));
    }

    @Test
    void removeFromRoundTrack(){
        Game g = new Game();
        Die d = new Die("RED");
        g.setDieRoundTrack(d);
        assertEquals(1, g.roundTrackSize());
        Die d2 = g.removeDieFromRoundTrack(0);
        assertEquals(0, g.roundTrackSize());
        assertEquals(d, d2);
    }

    @Test
    void draftTest(){
        Game g = new Game();
        Die d = new Die("RED");
        g.setDieDraft(d);
        assertEquals(d, g.getDieFromDraft(0));
    }

    @Test
    void removeFromDraft(){
        Game g = new Game();
        Die d = new Die("RED");
        g.setDieDraft(d);
        assertEquals(1, g.draftSize());
        Die d2 = g.removeDieFromDraft(0);
        assertEquals(0, g.draftSize());
        assertEquals(d, d2);
    }

    @Test
    void LeftDice(){
        Game g = new Game();
        Die d = new Die("RED");
        Die d2 = new Die("RED");
        g.setDieDraft(d);
        g.setDieDraft(d2);
        g.diceLeft();
        assertEquals(2, g.roundTrackSize());
        assertEquals(0, g.draftSize());

    }


}