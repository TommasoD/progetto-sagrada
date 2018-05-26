package it.polimi.ingsw.messages;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogoutMessageTest {

    @Test
    void serialize() {
        LogoutMessage m = new LogoutMessage();
        assertEquals("{\"id\":\"quit\"}", m.serialize());
    }

    @Test
    void deserialize() {
        LogoutMessage m = new LogoutMessage();
        m = m.deserialize("{\"id\":\"quit\"}");
        assertEquals("quit", m.getId());
    }

}