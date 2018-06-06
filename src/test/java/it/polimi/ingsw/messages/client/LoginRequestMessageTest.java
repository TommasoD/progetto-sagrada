package it.polimi.ingsw.messages.client;

import org.junit.jupiter.api.Test;

import static it.polimi.ingsw.messages.client.LoginRequestMessage.deserializeLoginRequestMessage;
import static org.junit.jupiter.api.Assertions.*;

class LoginRequestMessageTest {
    @Test
    void serialize() {
        LoginRequestMessage m = new LoginRequestMessage();
        assertEquals("{\"id\":\"login\"}", m.serialize());
    }

    @Test
    void deserialize() {
        LoginRequestMessage m = deserializeLoginRequestMessage("{\"id\":\"login\"}");
        assertEquals("login", m.getId());
    }


}