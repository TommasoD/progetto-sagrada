package it.polimi.ingsw.messages.client;

import org.junit.jupiter.api.Test;

import static it.polimi.ingsw.messages.client.NewTurnMessage.deserializeNewTurnMessage;
import static org.junit.jupiter.api.Assertions.*;

class NewTurnMessageTest {

    @Test
    void serialize() {
        NewTurnMessage m = new NewTurnMessage();
        assertEquals("{\"id\":\"turn\"}", m.serialize());
    }

    @Test
    void deserialize() {
        NewTurnMessage m = deserializeNewTurnMessage("{\"id\":\"turn\"}");
        assertEquals("turn", m.getId());
    }

}