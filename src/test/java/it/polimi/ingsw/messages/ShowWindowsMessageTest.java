package it.polimi.ingsw.messages;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShowWindowsMessageTest {

    @Test
    void serialize() {
        ShowWindowsMessage m = new ShowWindowsMessage("a", "o", "b", "o", "c", "o", "d", "o");
        assertEquals("{\"id\":\"windows\",\"name1\":\"a\",\"w1\":\"o\",\"name2\":\"b\",\"w2\":\"o\",\"name3\":\"c\",\"w3\":\"o\",\"name4\":\"d\",\"w4\":\"o\"}", m.serialize());
    }

    @Test
    void deserialize() {
        ShowWindowsMessage m = new ShowWindowsMessage();
        m = m.deserialize("{\"id\":\"windows\",\"name1\":\"a\",\"w1\":\"o\",\"name2\":\"b\",\"w2\":\"o\",\"name3\":\"c\",\"w3\":\"o\",\"name4\":\"d\",\"w4\":\"o\"}");
        assertEquals("windows", m.getId());
    }
}