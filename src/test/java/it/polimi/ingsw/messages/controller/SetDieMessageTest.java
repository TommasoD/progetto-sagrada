package it.polimi.ingsw.messages.controller;

import it.polimi.ingsw.messages.controller.SetDieMessage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SetDieMessageTest {

    @Test
    void serialize() {
        SetDieMessage m = new SetDieMessage(1,1,1);
        assertEquals("{\"id\":\"place\",\"x\":1,\"y\":1,\"index\":1}", m.serialize());
    }

    @Test
    void deserialize() {
        SetDieMessage m = new SetDieMessage();
        m = m.deserialize("{\"id\":\"place\",\"x\":1,\"y\":1,\"index\":1}");
        assertEquals("place", m.getId());
        assertEquals(1, m.getIndex());
        assertEquals(1, m.getX());
        assertEquals(1, m.getY());
    }

}