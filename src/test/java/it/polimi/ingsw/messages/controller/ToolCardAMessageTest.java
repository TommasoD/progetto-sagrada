package it.polimi.ingsw.messages.controller;

import org.junit.jupiter.api.Test;

import static it.polimi.ingsw.messages.controller.ToolCardAMessage.deserializeToolCardAMessage;
import static org.junit.jupiter.api.Assertions.*;

class ToolCardAMessageTest {

    @Test
    void serialize() {
        ToolCardAMessage m = new ToolCardAMessage(1,1,1);
        assertEquals("{\"id\":\"toolcardA\",\"num\":1,\"dieIndex\":1,\"action\":1}", m.serialize());
    }

    @Test
    void deserialize() {
        ToolCardAMessage m = deserializeToolCardAMessage("{\"id\":\"toolcardA\",\"num\":1,\"dieIndex\":1,\"action\":1}");
        assertEquals("toolcardA", m.getId());
        assertEquals(1, m.getNum());
        assertEquals(1, m.getDieIndex());
        assertEquals(1, m.getAction());
    }

}