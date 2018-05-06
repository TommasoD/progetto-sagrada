package it.polimi.ingsw;

import it.polimi.ingsw.publicobjectives.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PublicObjectiveFactoryTest {

    @Test
    void createObjective() {
        PublicObjectiveFactory of = new PublicObjectiveFactory();
        PublicObjective o = of.createObjective("DarkS");
        assertEquals("Sfumature Scure", o.getName());
    }

    @Test
    void getAllObjective() {
        PublicObjectiveFactory of = new PublicObjectiveFactory();
        PublicObjective o;
        for(int i = 0; i < 10; i++){
            o = of.getRandomObjective();
        }
        assertEquals(null, of.getRandomObjective());
    }

}