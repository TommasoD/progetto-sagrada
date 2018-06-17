package it.polimi.ingsw.messages.controller;

import org.junit.jupiter.api.Test;

import static it.polimi.ingsw.messages.controller.ToolCardCMessage.deserializeToolCardCMessage;
import static org.junit.jupiter.api.Assertions.*;

class ToolCardCMessageTest {

    @Test
    void serialize() {
        ToolCardCMessage m = new ToolCardCMessage(1, 1, 1, 1, 1, 1, 1, 1, 1);
        assertEquals("{\"id\":\"toolcardC\",\"num\":1,\"x\":1,\"y\":1,\"a\":1,\"b\":1,\"x2\":1,\"y2\":1,\"a2\":1,\"b2\":1}", m.serialize());
    }

    @Test
    void deserialize() {
        ToolCardCMessage m = deserializeToolCardCMessage("{\"id\":\"toolcardC\",\"num\":1,\"x\":1,\"y\":1,\"a\":1,\"b\":1,\"x2\":1,\"y2\":1,\"a2\":1,\"b2\":1}");
        assertEquals("toolcardC", m.getId());
        assertEquals(1, m.getNum());
        assertEquals(1, m.getA());
        assertEquals(1, m.getB());
        assertEquals(1, m.getX());
        assertEquals(1, m.getY());
        assertEquals(1, m.getA2());
        assertEquals(1, m.getB2());
        assertEquals(1, m.getX2());
        assertEquals(1, m.getY2());
    }

}