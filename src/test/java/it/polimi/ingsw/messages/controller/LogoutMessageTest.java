package it.polimi.ingsw.messages.controller;

import org.junit.jupiter.api.Test;

import static it.polimi.ingsw.messages.controller.LogoutMessage.deserializeLogoutMessage;
import static org.junit.jupiter.api.Assertions.*;

class LogoutMessageTest {

    @Test
    void serialize() {
        LogoutMessage m = new LogoutMessage();
        assertEquals("{\"id\":\"quit\"}", m.serialize());
    }

    @Test
    void deserialize() {
        LogoutMessage m = deserializeLogoutMessage("{\"id\":\"quit\"}");
        assertEquals("quit", m.getId());
    }

}