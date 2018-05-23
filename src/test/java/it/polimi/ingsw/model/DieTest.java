package it.polimi.ingsw.model;
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
        assertEquals("RED", d.getColor());
        d.setColor("BLUE");
        assertEquals("BLUE", d.getColor());
    }

    @Test
    void redDie2(){
        Die d = new Die("RED");
        assertEquals(0, d.getColorAsInt());
    }

    @Test
    void notRedDie(){
        Die d = new Die("BLUE");
        assertNotEquals("RED", d.getColor());
    }

    @Test
    void notRedDie2(){
        Die d = new Die("BLUE");
        assertNotEquals(0, d.getColorAsInt());
    }

    @Test
    void sixValueDie(){
        Die d = new Die("RED");
        d.setValue("6");
        assertEquals("6", d.getValue());
    }

    @Test
    void sixValueDie2(){
        Die d = new Die("RED");
        d.setValue("6");
        assertEquals(6, d.getValueAsInt());
    }

}