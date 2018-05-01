package it.polimi.ingsw;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DieTest {

    @Test
    void notNull(){
        Die d = new Die("RED");
        assertNotNull(d);
    }

    @Test
    void valueIsNull(){
        Die d = new Die("RED");
        String s = d.getValue();
        assertNull(s);
    }

    @Test
    void redDie(){
        Die d = new Die("RED");
        String s = d.getColor();
        assertEquals("RED", s);
    }

    @Test
    void notRedDie(){
        Die d = new Die("BLUE");
        String s = d.getColor();
        assertNotEquals("RED", s);
    }

}