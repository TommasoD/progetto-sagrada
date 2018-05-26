package it.polimi.ingsw.messages;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShowWindowsMessageTest {

    @Test
    void serialize() {
        ShowWindowsMessage m = new ShowWindowsMessage("a", 1, "o", "b", 2, "o",
                        "c", 3, "o", "d", 4, "o");
        assertEquals("{\"id\":\"windows\",\"name1\":\"a\",\"token1\":1,\"w1\":\"o\",\"name2\":\"b\",\"token2\":2,\"w2\":\"o\",\"name3\":\"c\",\"token3\":3,\"w3\":\"o\",\"name4\":\"d\",\"token4\":4,\"w4\":\"o\"}", m.serialize());
    }

    @Test
    void deserialize() {
        ShowWindowsMessage m = new ShowWindowsMessage();
        m = m.deserialize("{\"id\":\"windows\",\"name1\":\"a\",\"token1\":1,\"w1\":\"o\",\"name2\":\"b\",\"token2\":2,\"w2\":\"o\",\"name3\":\"c\",\"token3\":3,\"w3\":\"o\",\"name4\":\"d\",\"token4\":4,\"w4\":\"o\"}");
        assertEquals("windows", m.getId());
    }
}