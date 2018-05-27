package it.polimi.ingsw.model;
import it.polimi.ingsw.model.objectives.PrivateObjective;
import it.polimi.ingsw.model.windows.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void  usernameTest () {
        Player p = new Player("Giovanni");
        assertEquals("Giovanni",p.getUsername());
    }

    @Test
    void onlineTest() {
        Player p = new Player("Giovanni");
        p.setOnline(true);
        assertTrue(p.getOnline());
    }

    @Test
    void booleanTests(){
        Player p = new Player("user");
        assertFalse(p.isDieUsed());
        assertFalse(p.isToolCardUsed());
        assertFalse(p.isSkipTurn());
        assertFalse(p.isFirstDiePlaced());
    }

    @Test
    void resetTurnTest(){
        Player p = new Player("user");
        p.setDieUsed(true);
        p.resetTurn();
        assertFalse(p.isToolCardUsed());
        assertFalse(p.isDieUsed());
    }

    @Test
    void notNullTest () {
        WindowPattern w = new Window1();
        Player p = new Player("Giovanni",w);
        assertNotNull(p);
    }

    @Test
    void correctWindow () {
        WindowPattern w = new Window1();
        Player p = new Player("Giovanni",w);
        assertEquals(w,p.getPlayerWindow());
    }

    @Test
    void correctObjective () {
        WindowPattern w = new Window1();
        PrivateObjective obj  = new PrivateObjective("RED");
        Player p = new Player("Giovanni",w);
        p.setPlayerObjective(obj);
        assertEquals(obj,p.getPlayerObjective());
    }

    @Test
    void getTotalPoints() {
        WindowPattern window = new Window1();
        PrivateObjective obj  = new PrivateObjective("RED");
        Player p = new Player("Giovanni",window);
        p.setPlayerObjective(obj);
        p.getPlayerWindow().decreaseDifficultyToken(1); //sono 3

        Die die1 = new Die("RED");
        die1.setValue("1");
        window.getWindowMatrix(0, 0).setDie(die1);
        Die die2 = new Die("BLUE");
        die2.setValue("2");
        window.getWindowMatrix(2, 0).setDie(die2);
        Die die3 = new Die("GREEN");
        die3.setValue("2");
        window.getWindowMatrix(2, 1).setDie(die3);
        Die die4 = new Die("RED");
        die4.setValue("3");
        window.getWindowMatrix(3, 1).setDie(die4);
        Die die5 = new Die("PURPLE");
        die5.setValue("6");
        window.getWindowMatrix(4, 1).setDie(die5);
        Die die6 = new Die("RED");
        die6.setValue("1");
        window.getWindowMatrix(2, 2).setDie(die6);
        Die die7 = new Die("RED");
        die7.setValue("6");
        window.getWindowMatrix(4, 3).setDie(die7);
        assertEquals(1, p.getTotalPoints());
    }



}