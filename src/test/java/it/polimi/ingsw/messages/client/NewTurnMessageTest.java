package it.polimi.ingsw.messages.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NewTurnMessageTest {

    @Test
    void serialize() {
        NewTurnMessage m = new NewTurnMessage();
        assertEquals("{\"id\":\"turn\"}", m.serialize());
    }

    @Test
    void deserialize() {
        NewTurnMessage m = new NewTurnMessage();
        m = m.deserialize("{\"id\":\"turn\"}");
        assertEquals("turn", m.getId());
    }

}