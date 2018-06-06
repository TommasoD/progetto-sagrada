package it.polimi.ingsw.messages.client;

import org.junit.jupiter.api.Test;

import static it.polimi.ingsw.messages.client.EndTurnMessage.deserializeEndTurnMessage;
import static org.junit.jupiter.api.Assertions.*;

class EndTurnMessageTest {

    @Test
    void serialize() {
        EndTurnMessage m = new EndTurnMessage();
        assertEquals("{\"id\":\"end\"}", m.serialize());
    }

    @Test
    void deserialize() {
        EndTurnMessage m = deserializeEndTurnMessage("{\"id\":\"end\"}");
        assertEquals("end", m.getId());
    }

}