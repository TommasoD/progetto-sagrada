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
    void setDraftTest(){
        Game g = new Game();
        g.setPlayers(new Player("user"));
        g.setPlayers(new Player("user2"));
        g.setDraft();
        System .out.println(g.draftToString());
        assertEquals(5, g.draftSize());
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
    void diceLeft(){
        Game g = new Game();
        g.setPlayers(new Player("user"));
        g.setPlayers(new Player("user2"));
        g.setDraft();
        g.diceLeft();
        System.out.println(g.roundTrackToString());
        assertEquals(5, g.roundTrackSize());
        assertEquals(0, g.draftSize());
    }

    @Test
    void playersSize(){
        Game g = new Game();
        assertEquals(0, g.playersSize());
        g.setPlayers(new Player("username"));
        assertEquals(1, g.playersSize());
    }

    @Test
    void getPlayerFromIdTest(){
        Game g = new Game();
        WindowPatternFactory wf = new WindowPatternFactory();
        Player p1 = new Player("player0", wf.createWindow("Kaleidoscopic Dream"), 0);
        g.setPlayers(p1);
        assertEquals(p1, g.getPlayerFromId(0));
        assertNull(g.getPlayerFromId(1));
    }

    @Test
    void findTest(){
        Game g = new Game();
        WindowPatternFactory wf = new WindowPatternFactory();
        g.setPlayers(new Player("player0", wf.createWindow("Kaleidoscopic Dream"), 0));
        assertTrue(g.find("player0"));
        assertFalse(g.find("username"));
    }

    @Test
    void findAndReconnectTest(){
        Game g = new Game();
        WindowPatternFactory wf = new WindowPatternFactory();
        g.setPlayers(new Player("player0", wf.createWindow("Kaleidoscopic Dream"), 0));
        g.getPlayerFromId(0).setOnline(false);
        assertTrue(g.findAndReconnect("player0", 5));
        assertFalse(g.findAndReconnect("username", 4));
        assertTrue(g.getPlayerFromId(5).isOnline());
    }

    @Test
    void useDieTest(){
        Game g = new Game();
        WindowPatternFactory wf = new WindowPatternFactory();
        WindowPattern w = wf.createWindow("Kaleidoscopic Dream");
        g.setPlayers(new Player("user", w));
        g.setDraft();
        assertEquals(3, g.draftSize());
        g.useDie(0, 2,2, 2);
        assertEquals(2, g.draftSize());
        assertNotNull(g.getPlayers(0).getPlayerWindow().getWindowMatrix(2,2).getDie());
        assertTrue(g.getPlayers(0).isFirstDiePlaced());
        g.dump();
    }

}