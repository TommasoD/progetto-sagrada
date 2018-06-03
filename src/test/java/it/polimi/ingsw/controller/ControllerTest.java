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
}