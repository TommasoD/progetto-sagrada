package it.polimi.ingsw.messages.controller;

import org.junit.jupiter.api.Test;

import static it.polimi.ingsw.messages.controller.ToolCardDMessage.deserializeToolCardDMessage;
import static org.junit.jupiter.api.Assertions.*;

class ToolCardDMessageTest {

    @Test
    void serialize() {
        ToolCardDMessage m = new ToolCardDMessage(1, 1, 1, 1);
        assertEquals("{\"id\":\"toolcardD\",\"num\":1,\"dieIndex\":1,\"x\":1,\"y\":1}", m.serialize());
    }

    @Test
    void deserialize() {
        ToolCardDMessage m = deserializeToolCardDMessage("{\"id\":\"toolcardD\",\"num\":1,\"dieIndex\":1,\"x\":1,\"y\":1}");
        assertEquals("toolcardD", m.getId());
        assertEquals(1, m.getNum());
        assertEquals(1, m.getDieIndex());
        assertEquals(1, m.getX());
        assertEquals(1, m.getY());
    }

}