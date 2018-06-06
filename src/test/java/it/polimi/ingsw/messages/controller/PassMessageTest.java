package it.polimi.ingsw.messages.controller;

import org.junit.jupiter.api.Test;

import static it.polimi.ingsw.messages.controller.PassMessage.deserializePassMessage;
import static org.junit.jupiter.api.Assertions.*;

class PassMessageTest {

    @Test
    void serialize() {
        PassMessage m = new PassMessage();
        assertEquals("{\"id\":\"pass\"}", m.serialize());
    }

    @Test
    void deserialize() {
        PassMessage m = deserializePassMessage("{\"id\":\"pass\"}");
        assertEquals("pass", m.getId());
    }

}