package it.polimi.ingsw.messages;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChooseWindowMessageTest {

    @Test
    void serialize() {
        ChooseWindowMessage m = new ChooseWindowMessage("a");
        assertEquals("{\"id\":\"window\",\"windowName\":\"a\"}", m.serialize());
    }

    @Test
    void deserialize() {
        ChooseWindowMessage m = new ChooseWindowMessage();
        m = m.deserialize("{\"id\":\"window\",\"windowName\":\"a\"}");
        assertEquals("window", m.getId());
        assertEquals("a", m.getWindowName());
    }

}