package it.polimi.ingsw.messages;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PassMessageTest {

    @Test
    void serialize() {
        PassMessage m = new PassMessage();
        assertEquals("{\"id\":\"pass\"}", m.serialize());
    }

    @Test
    void deserialize() {
        PassMessage m = new PassMessage();
        m = m.deserialize("{\"id\":\"pass\"}");
        assertEquals("pass", m.getId());
    }

}