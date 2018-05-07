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
    void OnlineTest() {
        WindowPattern w = new Window1();
        Player p = new Player("Giovanni",w);
        p.setOnline(true);
        assertTrue(p.getOnline());
    }

    @Test
    void NotNullTest () {
        WindowPattern w = new Window1();
        Player p = new Player("Giovanni",w);
        assertNotNull(p);
    }

    @Test
    void CorrectWindow () {
        WindowPattern w = new Window1();
        Player p = new Player("Giovanni",w);
        assertEquals(w,p.getPlayerWindow());
    }

    @Test
    void CorrectObjective () {
        WindowPattern w = new Window1();
        PrivateObjective obj  = new PrivateObjective("RED");
        Player p = new Player("Giovanni",w);
        p.setPlayerObjective(obj);
        assertEquals(obj,p.getPlayerObjective());
    }

    @Test
    void PointsTest () {
        WindowPattern w = new Window1();
        Player p = new Player("Giovanni",w);
        p.setPoints(54);
        assertEquals(54,p.getPoints());
    }

}