package it.polimi.ingsw.controller;

import it.polimi.ingsw.messages.controller.*;
import it.polimi.ingsw.messages.client.ShowWindowsMessage;
import it.polimi.ingsw.model.Die;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @Test
    void visitLogin(){
        Game g = new Game();
        Controller c = new Controller(g);
        c.newMatch(3);
        String s = new LoginMessage("username").serialize();
        c.update(s, 2);
        assertEquals("username", g.getPlayerFromId(2).getUsername());

    }

    @Test
    void visitWindow(){
        Game g = new Game();
        Controller c = new Controller(g);
        c.newMatch(3);
        String s = new ChooseWindowMessage("Kaleidoscopic Dream").serialize();
        c.update(s, 2);
        g.getPlayerFromId(2).getPlayerWindow().dump();
        assertEquals("Kaleidoscopic Dream", g.getPlayerFromId(2).getPlayerWindow().getName());
    }

    @Test
    void visitLogout(){
        Game g = new Game();
        Controller c = new Controller(g);
        c.newMatch(3);
        c.startMatch();
        String s = new LogoutMessage().serialize();
        c.update(s, 2);
        assertFalse(g.getPlayerFromId(2).isOnline());
    }

    @Test
    void visitPass(){
        Game g = new Game();
        Controller c = new Controller(g);
        c.newMatch(3);
        c.startMatch();
        String s = new PassMessage().serialize();
        c.update(s, 2);
    }

    @Test
    void visitInCaseOfError(){
        Game g = new Game();
        Controller c = new Controller(g);
        c.newMatch(3);
        String s = new ShowWindowsMessage().serialize();
        c.update(s, 2);
    }

    @Test
    void setWindowPatternTest(){
        Game g = new Game();
        g.addPlayer(new Player("user", null, 5));
        Controller c = new Controller(g);
        c.setWindowPattern(5, "Kaleidoscopic Dream");
        assertNotNull(g.getPlayerFromId(5));
        g.getPlayerFromId(5).dump();
    }

    @Test
    void visitSetDieValid(){
        Game g = new Game();
        Controller c = new Controller(g);
        c.newMatch(3);
        c.startMatch();
        c.setWindowPattern(0, "Kaleidoscopic Dream");
        String s = new SetDieMessage(3, 0, 1).serialize();
        c.update(s, 0);
        assertNotNull(c.getGame().getPlayerFromId(0).getPlayerWindow().getWindowMatrix(3, 0).getDie());
        c.getGame().getPlayerFromId(0).dump();
    }

    @Test
    void visitSetDieInvalidBreakRules(){
        Game g = new Game();
        Controller c = new Controller(g);
        c.newMatch(3);
        c.startMatch();
        Die d = new Die("BLUE");
        d.roll();
        c.getGame().getDraft().add(1, d);
        c.setWindowPattern(0, "Kaleidoscopic Dream");
        String s = new SetDieMessage(0, 0, 1).serialize();
        c.update(s, 0);
        assertNull(c.getGame().getPlayerFromId(0).getPlayerWindow().getWindowMatrix(0, 0).getDie());
        c.getGame().getPlayerFromId(0).dump();
    }

    @Test
    void visitSetDieInvalidFirstMove(){
        Game g = new Game();
        Controller c = new Controller(g);
        c.newMatch(3);
        c.startMatch();
        c.setWindowPattern(0, "Kaleidoscopic Dream");
        String s = new SetDieMessage(3, 1, 1).serialize();
        c.update(s, 0);
        assertNull(c.getGame().getPlayerFromId(0).getPlayerWindow().getWindowMatrix(3, 1).getDie());
        c.getGame().getPlayerFromId(0).dump();
    }

    @Test
    void visitSetDieInvalidIncorrectTurn(){
        Game g = new Game();
        Controller c = new Controller(g);
        c.newMatch(3);
        c.startMatch();
        c.setWindowPattern(0, "Kaleidoscopic Dream");
        String s = new SetDieMessage(3, 0, 1).serialize();
        c.update(s, 1);
        assertNull(c.getGame().getPlayerFromId(1).getPlayerWindow().getWindowMatrix(3, 0).getDie());
        c.getGame().getPlayerFromId(1).dump();
    }

    @Test
    void visitSetDieInvalidDieAlreadyPlaced(){
        Game g = new Game();
        Controller c = new Controller(g);
        c.newMatch(3);
        c.startMatch();
        c.setWindowPattern(0, "Kaleidoscopic Dream");
        c.getGame().getPlayerFromId(0).setDieUsed(true);
        String s = new SetDieMessage(3, 0, 1).serialize();
        c.update(s, 0);
        assertNull(c.getGame().getPlayerFromId(0).getPlayerWindow().getWindowMatrix(3, 0).getDie());
        c.getGame().getPlayerFromId(0).dump();
    }

    @Test
    void visitToolCardA_1(){
        Game g = new Game();
        Controller c = new Controller(g);
        c.newMatch(1);
        c.startMatch();
        c.setWindowPattern(0, "Kaleidoscopic Dream");
        Die d = new Die("RED");
        d.setValue("3");
        c.getGame().setDieDraft(d);
        c.update(new ToolCardAMessage(1, 3, 1).serialize(), 0);
        assertEquals("4", c.getGame().getDieFromDraft(3).getValue());
        assertTrue(c.getGame().getToolCard(0).isAlreadyUsed());
        assertTrue(c.getGame().getPlayerFromId(0).isToolCardUsed());
        assertEquals(3, c.getGame().getPlayerFromId(0).getPlayerWindow().getDifficultyToken());
    }
    @Test
    void visitToolCardA_5(){
        Game g = new Game();
        Controller c = new Controller(g);
        c.newMatch(1);
        c.startMatch();
        c.setWindowPattern(0, "Kaleidoscopic Dream");
        Die d = new Die("RED");
        d.roll();
        c.getGame().setDieDraft(d);
        Die d2 = new Die("BLUE");
        d2.roll();
        c.getGame().setDieRoundTrack(d2);
        c.update(new ToolCardAMessage(5, 3, 0).serialize(), 0);
        assertEquals(d2, c.getGame().getDieFromDraft(3));
        assertEquals(d, c.getGame().getDieFromRoundTrack(0));
        assertTrue(c.getGame().getToolCard(4).isAlreadyUsed());
        assertTrue(c.getGame().getPlayerFromId(0).isToolCardUsed());
        assertEquals(3, c.getGame().getPlayerFromId(0).getPlayerWindow().getDifficultyToken());
    }

    @Test
    void visitToolCardA_6(){
        Game g = new Game();
        Controller c = new Controller(g);
        c.newMatch(1);
        c.startMatch();
        c.setWindowPattern(0, "Kaleidoscopic Dream");
        c.update(new ToolCardAMessage(6, 0, 0).serialize(), 0);
        assertTrue(c.getGame().getToolCard(5).isAlreadyUsed());
        assertTrue(c.getGame().getPlayerFromId(0).isToolCardUsed());
        assertEquals(3, c.getGame().getPlayerFromId(0).getPlayerWindow().getDifficultyToken());
    }

    @Test
    void visitToolCardA_10(){
        Game g = new Game();
        Controller c = new Controller(g);
        c.newMatch(1);
        c.startMatch();
        c.setWindowPattern(0, "Kaleidoscopic Dream");
        Die d = new Die("RED");
        d.setValue("1");
        c.getGame().setDieDraft(d);
        c.update(new ToolCardAMessage(10, 3, 0).serialize(), 0);
        assertEquals("6", c.getGame().getDieFromDraft(3).getValue());
        assertTrue(c.getGame().getToolCard(9).isAlreadyUsed());
        assertTrue(c.getGame().getPlayerFromId(0).isToolCardUsed());
        assertEquals(3, c.getGame().getPlayerFromId(0).getPlayerWindow().getDifficultyToken());
    }

    @Test
    void visitToolCardA_11(){
        Game g = new Game();
        Controller c = new Controller(g);
        c.newMatch(1);
        c.startMatch();
        c.setWindowPattern(0, "Kaleidoscopic Dream");
        System.out.println(c.getGame().draftToString());
        int sizeBefore = c.getGame().getDiceBag().getSize();
        c.update(new ToolCardAMessage(11, 0, 0).serialize(), 0);
        assertTrue(c.getGame().getToolCard(10).isAlreadyUsed());
        assertTrue(c.getGame().getPlayerFromId(0).isToolCardUsed());
        assertEquals(3, c.getGame().getPlayerFromId(0).getPlayerWindow().getDifficultyToken());
        assertEquals(sizeBefore, c.getGame().getDiceBag().getSize());
        System.out.println(c.getGame().draftToString());

        c.update(new ToolCardAMessage(11, 0, 4).serialize(), 0);
        assertTrue(c.getGame().getToolCard(10).isAlreadyUsed());
        assertTrue(c.getGame().getPlayerFromId(0).isToolCardUsed());
        assertEquals(3, c.getGame().getPlayerFromId(0).getPlayerWindow().getDifficultyToken());
        assertEquals("4", c.getGame().getDieFromDraft(0).getValue());
        System.out.println(c.getGame().draftToString());
    }

    @Test
    void visitToolCardB_2() {
        Game g = new Game();
        Controller c = new Controller(g);
        c.newMatch(1);
        c.startMatch();
        c.setWindowPattern(0, "Kaleidoscopic Dream");
        Die d = new Die("RED", "2");
        Die d2 = new Die("GREEN", "3");
        c.getGame().getPlayerFromId(0).getPlayerWindow().getWindowMatrix(0, 0).setDie(d);
        c.getGame().getPlayerFromId(0).getPlayerWindow().getWindowMatrix(1, 1).setDie(d2);
        c.update(new ToolCardBMessage(2, 0, 0, 1, 0).serialize(), 0);
        c.getGame().getPlayerFromId(0).getPlayerWindow().dump();
        assertEquals(d, c.getGame().getPlayerFromId(0).getPlayerWindow().getWindowMatrix(1, 0).getDie());
        assertTrue(c.getGame().getToolCard(1).isAlreadyUsed());
        assertTrue(c.getGame().getPlayerFromId(0).isToolCardUsed());
        assertEquals(3, c.getGame().getPlayerFromId(0).getPlayerWindow().getDifficultyToken());
    }

    @Test
    void visitToolCardB_3() {
        Game g = new Game();
        Controller c = new Controller(g);
        c.newMatch(1);
        c.startMatch();
        c.setWindowPattern(0, "Kaleidoscopic Dream");
        Die d = new Die("RED", "2");
        Die d2 = new Die("GREEN", "3");
        c.getGame().getPlayerFromId(0).getPlayerWindow().getWindowMatrix(0, 0).setDie(d);
        c.getGame().getPlayerFromId(0).getPlayerWindow().getWindowMatrix(3, 0).setDie(d2);
        c.update(new ToolCardBMessage(3, 0, 0, 4, 0).serialize(), 0);
        assertEquals(d, c.getGame().getPlayerFromId(0).getPlayerWindow().getWindowMatrix(4, 0).getDie());
        assertTrue(c.getGame().getToolCard(2).isAlreadyUsed());
        assertTrue(c.getGame().getPlayerFromId(0).isToolCardUsed());
        assertEquals(3, c.getGame().getPlayerFromId(0).getPlayerWindow().getDifficultyToken());
    }

    @Test
    void visitToolCardC_4() {
        Game g = new Game();
        Controller c = new Controller(g);
        c.newMatch(1);
        c.startMatch();
        c.setWindowPattern(0, "Kaleidoscopic Dream");
        Die d = new Die("RED", "2");
        Die d2 = new Die("GREEN", "3");
        Die d3 = new Die("BLUE", "4");
        c.getGame().getPlayerFromId(0).getPlayerWindow().getWindowMatrix(2, 0).setDie(d);
        c.getGame().getPlayerFromId(0).getPlayerWindow().getWindowMatrix(3, 1).setDie(d2);
        c.getGame().getPlayerFromId(0).getPlayerWindow().getWindowMatrix(2, 2).setDie(d3);
        c.update(new ToolCardCMessage(4, 3, 1, 3, 0, 2, 2, 1, 0).serialize(), 0);
        assertEquals(d2, c.getGame().getPlayerFromId(0).getPlayerWindow().getWindowMatrix(3, 0).getDie());
        assertEquals(d3, c.getGame().getPlayerFromId(0).getPlayerWindow().getWindowMatrix(1, 0).getDie());
        assertTrue(c.getGame().getToolCard(3).isAlreadyUsed());
        assertTrue(c.getGame().getPlayerFromId(0).isToolCardUsed());
        assertEquals(3, c.getGame().getPlayerFromId(0).getPlayerWindow().getDifficultyToken());
    }

    @Test
    void visitToolCardC_12() {
        Game g = new Game();
        Controller c = new Controller(g);
        c.newMatch(1);
        c.startMatch();
        c.setWindowPattern(0, "Kaleidoscopic Dream");
        c.getGame().setDieRoundTrack(new Die("BLUE", "4"));
        Die d = new Die("RED", "2");
        Die d2 = new Die("BLUE", "3");
        Die d3 = new Die("BLUE", "4");
        c.getGame().getPlayerFromId(0).getPlayerWindow().getWindowMatrix(2, 0).setDie(d);
        c.getGame().getPlayerFromId(0).getPlayerWindow().getWindowMatrix(3, 1).setDie(d2);
        c.getGame().getPlayerFromId(0).getPlayerWindow().getWindowMatrix(2, 2).setDie(d3);
        c.update(new ToolCardCMessage(12, 3, 1, 3, 0, 2, 2, 1, 0).serialize(), 0);
        assertEquals(d2, c.getGame().getPlayerFromId(0).getPlayerWindow().getWindowMatrix(3, 0).getDie());
        assertEquals(d3, c.getGame().getPlayerFromId(0).getPlayerWindow().getWindowMatrix(1, 0).getDie());
        assertTrue(c.getGame().getToolCard(11).isAlreadyUsed());
        assertTrue(c.getGame().getPlayerFromId(0).isToolCardUsed());
        assertEquals(3, c.getGame().getPlayerFromId(0).getPlayerWindow().getDifficultyToken());
    }

    @Test
    void visitToolCardC_12_OneMoveOnly() {
        Game g = new Game();
        Controller c = new Controller(g);
        c.newMatch(1);
        c.startMatch();
        c.setWindowPattern(0, "Kaleidoscopic Dream");
        c.getGame().setDieRoundTrack(new Die("BLUE", "4"));
        Die d = new Die("RED", "2");
        Die d2 = new Die("BLUE", "3");
        Die d3 = new Die("BLUE", "4");
        c.getGame().getPlayerFromId(0).getPlayerWindow().getWindowMatrix(2, 0).setDie(d);
        c.getGame().getPlayerFromId(0).getPlayerWindow().getWindowMatrix(3, 1).setDie(d2);
        c.getGame().getPlayerFromId(0).getPlayerWindow().getWindowMatrix(2, 2).setDie(d3);
        c.getGame().dump();
        c.update(new ToolCardCMessage(12, 3, 1, 3, 0, -1, -1, -1, -1).serialize(), 0);
        assertEquals(d2, c.getGame().getPlayerFromId(0).getPlayerWindow().getWindowMatrix(3, 0).getDie());
        assertNull(c.getGame().getPlayerFromId(0).getPlayerWindow().getWindowMatrix(1, 0).getDie());
        assertTrue(c.getGame().getToolCard(11).isAlreadyUsed());
        assertTrue(c.getGame().getPlayerFromId(0).isToolCardUsed());
        assertEquals(3, c.getGame().getPlayerFromId(0).getPlayerWindow().getDifficultyToken());
        c.getGame().dump();
    }

    @Test
    void visitToolCardD_8(){
        Game g = new Game();
        Controller c = new Controller(g);
        c.newMatch(1);
        c.startMatch();
        c.setWindowPattern(0, "Kaleidoscopic Dream");
        c.getGame().getPlayerFromId(0).getPlayerWindow().getWindowMatrix(0, 0).setDie(new Die("RED", "2"));
        c.update(new ToolCardDMessage(8, 1, 1, 1).serialize(), 0);
        assertNotNull(c.getGame().getPlayerFromId(0).getPlayerWindow().getWindowMatrix(1, 1).getDie());
        assertTrue(c.getGame().getToolCard(7).isAlreadyUsed());
        assertTrue(c.getGame().getPlayerFromId(0).isToolCardUsed());
        assertEquals(3, c.getGame().getPlayerFromId(0).getPlayerWindow().getDifficultyToken());
        assertTrue(g.getPlayerFromId(0).isSecondTurnDone());
    }

    @Test
    void visitToolCardD_9(){
        Game g = new Game();
        Controller c = new Controller(g);
        c.newMatch(1);
        c.startMatch();
        c.setWindowPattern(0, "Kaleidoscopic Dream");
        c.update(new ToolCardDMessage(9, 1, 1, 1).serialize(), 0);
        assertNotNull(c.getGame().getPlayerFromId(0).getPlayerWindow().getWindowMatrix(1, 1).getDie());
        assertTrue(c.getGame().getToolCard(8).isAlreadyUsed());
        assertTrue(c.getGame().getPlayerFromId(0).isToolCardUsed());
        assertEquals(3, c.getGame().getPlayerFromId(0).getPlayerWindow().getDifficultyToken());
    }

    @Test
    void visitToolCardE_7(){
        Game g = new Game();
        Controller c = new Controller(g);
        c.newMatch(1);
        c.startMatch();
        c.setWindowPattern(0, "Kaleidoscopic Dream");
        System.out.println(c.getGame().draftToString());
        c.update(new ToolCardEMessage(7).serialize(), 0);
        System.out.println(c.getGame().draftToString());
        assertTrue(c.getGame().getToolCard(6).isAlreadyUsed());
        assertTrue(c.getGame().getPlayerFromId(0).isToolCardUsed());
        assertEquals(3, c.getGame().getPlayerFromId(0).getPlayerWindow().getDifficultyToken());

        c.getGame().getPlayerFromId(0).setToolCardUsed(false);
        c.getGame().getPlayerFromId(0).setFirstTurnDone(true);
        c.update(new ToolCardEMessage(7).serialize(), 0);
        System.out.println(c.getGame().draftToString());
        assertTrue(c.getGame().getToolCard(6).isAlreadyUsed());
        assertFalse(c.getGame().getPlayerFromId(0).isToolCardUsed());
        assertEquals(3, c.getGame().getPlayerFromId(0).getPlayerWindow().getDifficultyToken());
    }

    @Test
    void visitReconnect(){
        Game g = new Game();
        Controller c = new Controller(g);
        c.newMatch(1);
        c.startMatch();
        c.getGame().getPlayerFromId(0).setOnline(false);
        c.update(new ReconnectMessage().serialize(), 0);
        assertTrue(c.getGame().getPlayerFromId(0).isOnline());
    }

}