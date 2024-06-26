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
        g.addPlayer(new Player("user"));
        g.addPlayer(new Player("user2"));
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
        g.addPlayer(new Player("user"));
        g.addPlayer(new Player("user2"));
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
        g.addPlayer(new Player("username"));
        assertEquals(1, g.playersSize());
    }

    @Test
    void allReadyToPlay(){
        Game g = new Game();
        for(int i = 0; i < 4; i++){
            g.addPlayer(new Player("p" + i));
        }
        assertFalse(g.allReadyToPlay());
        for(Player p : g.getPlayers()){
            p.setReady(true);
        }
        assertTrue(g.allReadyToPlay());
    }

    @Test
    void getPlayerFromIdTest(){
        Game g = new Game();
        WindowPatternFactory wf = new WindowPatternFactory();
        Player p1 = new Player("player0", wf.createWindow("Kaleidoscopic Dream"), 0);
        g.addPlayer(p1);
        assertEquals(p1, g.getPlayerFromId(0));
        assertNull(g.getPlayerFromId(1));
    }

    @Test
    void findTest(){
        Game g = new Game();
        WindowPatternFactory wf = new WindowPatternFactory();
        g.addPlayer(new Player("player0", wf.createWindow("Kaleidoscopic Dream"), 0));
        assertTrue(g.find("player0"));
        assertFalse(g.find("username"));
    }

    @Test
    void getNumberOfOnlinePlayersTest(){
        Game g = new Game();
        WindowPatternFactory wf = new WindowPatternFactory();
        g.addPlayer(new Player("player0", wf.createWindow("Kaleidoscopic Dream"), 0));
        g.addPlayer(new Player("player0", wf.createWindow("Kaleidoscopic Dream"), 1));
        g.addPlayer(new Player("player0", wf.createWindow("Kaleidoscopic Dream"), 2));
        assertEquals(3, g.getNumberOfOnlinePlayers());
        g.getPlayerFromId(0).setOnline(false);
        assertEquals(2, g.getNumberOfOnlinePlayers());
    }

    @Test
    void useDieTest(){
        Game g = new Game();
        WindowPatternFactory wf = new WindowPatternFactory();
        WindowPattern w = wf.createWindow("Kaleidoscopic Dream");
        g.addPlayer(new Player("user", w, 0));
        g.setDraft();
        assertEquals(3, g.draftSize());
        g.useDie(0, 2,2, 2);
        assertEquals(2, g.draftSize());
        assertNotNull(g.getPlayerFromId(0).getPlayerWindow().getWindowMatrix(2,2).getDie());
        assertTrue(g.getPlayerFromId(0).isFirstDiePlaced());
        g.dump();
    }

    @Test
    void moveDieTest() {
        Game g = new Game();
        WindowPatternFactory wf = new WindowPatternFactory();
        WindowPattern w = wf.createWindow("Kaleidoscopic Dream");
        g.addPlayer(new Player("user", w, 0));
        Die d = new Die("RED");
        d.setValue("6");
        g.getPlayerFromId(0).getPlayerWindow().getWindowMatrix(0,0).setDie(d);
        g.moveDie(0,0,0,1,1);
        assertEquals(d,g.getPlayerFromId(0).getPlayerWindow().getWindowMatrix(1,1).getDie());
    }

    @Test
    void canUseToolCard(){
        Game g = new Game();
        WindowPatternFactory wf = new WindowPatternFactory();
        g.addPlayer(new Player("user", wf.createWindow("Kaleidoscopic Dream"), 0));
        g.initialize();
        for(ToolCard t : g.getToolCards()){
            t.dump();
        }
        assertTrue(g.canUseToolCard(1, 0));
    }

    @Test
    void useToolCardTest() {
        Game g = new Game();
        WindowPatternFactory wf = new WindowPatternFactory();
        g.addPlayer(new Player("user", wf.createWindow("Kaleidoscopic Dream"), 0));
        g.initialize();
        g.useToolCard(5, 0);
        assertTrue(g.getToolCard(4).isAlreadyUsed());
        assertEquals(3, g.getPlayerFromId(0).getPlayerWindow().getDifficultyToken());
    }

}