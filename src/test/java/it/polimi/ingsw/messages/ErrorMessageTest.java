package it.polimi.ingsw.messages;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ErrorMessageTest {

    @Test
    void serialize() {
        ErrorMessage m = new ErrorMessage(1);
        assertEquals("{\"id\":\"error\",\"type\":1}", m.serialize());
    }

    @Test
    void deserialize() {
        ErrorMessage m = new ErrorMessage();
        m = m.deserialize("{\"id\":\"error\",\"type\":1}");
        assertEquals("error", m.getId());
        assertEquals(1, m.getType());
    }

}