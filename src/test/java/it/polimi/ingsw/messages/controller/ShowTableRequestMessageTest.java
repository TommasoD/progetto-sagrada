package it.polimi.ingsw.messages.controller;

import org.junit.jupiter.api.Test;

import static it.polimi.ingsw.messages.controller.ShowTableRequestMessage.deserializeShowTableRequestMessage;
import static org.junit.jupiter.api.Assertions.*;

class ShowTableRequestMessageTest {

    @Test
    void serialize() {
        ShowTableRequestMessage m = new ShowTableRequestMessage();
        assertEquals("{\"id\":\"show_table\"}", m.serialize());
    }

    @Test
    void deserialize() {
        ShowTableRequestMessage m = deserializeShowTableRequestMessage("{\"id\":\"show_table\"}");
        assertEquals("show_table", m.getId());
    }

}