package it.polimi.ingsw.messages.controller;

import org.junit.jupiter.api.Test;

import static it.polimi.ingsw.messages.controller.ToolCardBMessage.deserializeToolCardBMessage;
import static org.junit.jupiter.api.Assertions.*;

class ToolCardBMessageTest {

    @Test
    void serialize() {
        ToolCardBMessage m = new ToolCardBMessage(1, 1, 1, 1, 1);
        assertEquals("{\"id\":\"toolcardB\",\"num\":1,\"x\":1,\"y\":1,\"a\":1,\"b\":1}", m.serialize());
    }

    @Test
    void deserialize() {
        ToolCardBMessage m = deserializeToolCardBMessage("{\"id\":\"toolcardB\",\"num\":1,\"x\":1,\"y\":1,\"a\":1,\"b\":1}");
        assertEquals("toolcardB", m.getId());
        assertEquals(1, m.getNum());
        assertEquals(1, m.getA());
        assertEquals(1, m.getB());
        assertEquals(1, m.getX());
        assertEquals(1, m.getY());
    }

}