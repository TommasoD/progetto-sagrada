package it.polimi.ingsw.messages.controller;

import org.junit.jupiter.api.Test;

import static it.polimi.ingsw.messages.controller.ReconnectMessage.deserializeReconnectMessage;
import static org.junit.jupiter.api.Assertions.*;

class ReconnectMessageTest {

    @Test
    void serialize() {
        ReconnectMessage m = new ReconnectMessage();
        assertEquals("{\"id\":\"reconnect\"}", m.serialize());
    }

    @Test
    void deserialize() {
        ReconnectMessage m = deserializeReconnectMessage("{\"id\":\"reconnect\"}");
        assertEquals("reconnect", m.getId());
    }

}