package it.polimi.ingsw.messages.client;

import org.junit.jupiter.api.Test;

import static it.polimi.ingsw.messages.client.OkMessage.deserializeOkMessage;
import static org.junit.jupiter.api.Assertions.*;

class OkMessageTest {

    @Test
    void serialize() {
        OkMessage m = new OkMessage();
        assertEquals("{\"id\":\"ok\"}", m.serialize());
    }

    @Test
    void deserialize() {
        OkMessage m = deserializeOkMessage("{\"id\":\"ok\"}");
        assertEquals("ok", m.getId());
    }
}