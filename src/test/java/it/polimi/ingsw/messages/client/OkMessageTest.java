package it.polimi.ingsw.messages.client;

import it.polimi.ingsw.messages.client.OkMessage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OkMessageTest {

    @Test
    void serialize() {
        OkMessage m = new OkMessage();
        assertEquals("{\"id\":\"ok\"}", m.serialize());
    }

    @Test
    void deserialize() {
        OkMessage m = new OkMessage();
        m = m.deserialize("{\"id\":\"ok\"}");
        assertEquals("ok", m.getId());
    }
}