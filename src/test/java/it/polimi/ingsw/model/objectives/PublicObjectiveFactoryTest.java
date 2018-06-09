package it.polimi.ingsw.model.objectives;

import it.polimi.ingsw.model.objectives.publicobjectives.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PublicObjectiveFactoryTest {

    @Test
    void createObjective() {
        PublicObjectiveFactory of = new PublicObjectiveFactory();
        PublicObjective o = of.createObjective("DarkS");
        assertEquals("Deep Shades", o.getName());
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