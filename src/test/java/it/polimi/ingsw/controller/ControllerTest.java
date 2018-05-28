package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.DiceBag;
import it.polimi.ingsw.model.Die;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @Test
    void whoIsNextTest() {
        Controller c = new Controller();
        c.addPlayer("user1");
        c.addPlayer("user2");
        c.newMatch();
        assertEquals(0, c.whoIsNext());
    }

    @Test
    void isGameEndedTest() {
        Controller c = new Controller();
        c.addPlayer("user1");
        c.addPlayer("user2");
        c.newMatch();
        assertFalse(c.isGameEnded());
    }

    @Test
    void handleMoveTestCasePlaceValid(){
        Controller c = new Controller();
        c.addPlayer("user1");
        c.setWindowPattern(0, "Kaleidoscopic Dream");
        c.newMatch();
        assertEquals("Die placed", c.handleMove(0, "{\"id\":\"place\",\"x\":3,\"y\":0,\"index\":1}"));
        c.getGame().getPlayers(0).dump();
    }

    @Test
    void handleMoveTestCasePlaceInvalid1(){
        Controller c = new Controller();
        c.addPlayer("user1");
        c.setWindowPattern(0, "Kaleidoscopic Dream");
        c.newMatch();
        assertEquals("Invalid placement", c.handleMove(1, "{\"id\":\"place\",\"x\":2,\"y\":2,\"index\":1}"));
        c.getGame().getPlayers(0).dump();
    }

    @Test
    void handleMoveTestCasePlaceInvalid2(){
        Controller c = new Controller();
        c.addPlayer("user1");
        c.setWindowPattern(0, "Kaleidoscopic Dream");
        c.newMatch();
        Die d = new Die("BLUE");
        d.roll();
        c.getGame().setDieDraft(d);
        assertEquals("Invalid placement", c.handleMove(0, "{\"id\":\"place\",\"x\":2,\"y\":2,\"index\":3}"));
        c.getGame().getPlayers(0).dump();
    }
}