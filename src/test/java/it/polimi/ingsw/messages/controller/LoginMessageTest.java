package it.polimi.ingsw.messages.controller;

import it.polimi.ingsw.messages.controller.LoginMessage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginMessageTest {

    @Test
    void serialize() {
        LoginMessage m = new LoginMessage("user");
        assertEquals("{\"id\":\"login\",\"username\":\"user\"}", m.serialize());
    }

    @Test
    void deserialize() {
        LoginMessage m = new LoginMessage();
        m = m.deserialize("{\"id\":\"login\",\"username\":\"user\"}");
        assertEquals("login", m.getId());
        assertEquals("user", m.getUsername());
    }
}