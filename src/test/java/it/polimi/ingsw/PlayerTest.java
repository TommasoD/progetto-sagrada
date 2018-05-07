package it.polimi.ingsw;
import it.polimi.ingsw.windows.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void  usernameTest () {
        WindowPattern w = new Window1();
        Player p = new Player("Giovanni",w);
        assertEquals("Giovanni",p.getUsername());
    }

    @Test
    void onlineTest() {
        WindowPattern w = new Window1();
        Player p = new Player("Giovanni",w);
        p.setOnline(true);
        assertTrue(p.getOnline());
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
    void pointsTest () {
        WindowPattern w = new Window1();
        Player p = new Player("Giovanni",w);
        p.setPoints(54);
        assertEquals(54,p.getPoints());
    }

}