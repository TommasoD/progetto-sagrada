package it.polimi.ingsw.messages.client;

import org.junit.jupiter.api.Test;

import static it.polimi.ingsw.messages.client.DieColorMessage.deserializeDieColorMessage;
import static org.junit.jupiter.api.Assertions.*;

class DieColorMessageTest {

    @Test
    void serialize() {
        DieColorMessage m = new DieColorMessage("RED");
        assertEquals("{\"id\":\"die_color\",\"color\":\"RED\"}", m.serialize());
    }

    @Test
    void deserialize() {
        DieColorMessage m = deserializeDieColorMessage("{\"id\":\"die_color\",\"color\":\"RED\"}");
        assertEquals("die_color", m.getId());
        assertEquals("RED", m.getColor());
    }

}