package it.polimi.ingsw.messages.controller;

import org.junit.jupiter.api.Test;

import static it.polimi.ingsw.messages.controller.ToolCardEMessage.deserializeToolCardEMessage;
import static org.junit.jupiter.api.Assertions.*;

class ToolCardEMessageTest {

    @Test
    void serialize() {
        ToolCardEMessage m = new ToolCardEMessage(1);
        assertEquals("{\"id\":\"toolcardE\",\"num\":1}", m.serialize());
    }

    @Test
    void deserialize() {
        ToolCardEMessage m = deserializeToolCardEMessage("{\"id\":\"toolcardE\",\"num\":1}");
        assertEquals("toolcardE", m.getId());
        assertEquals(1, m.getNum());
    }

}