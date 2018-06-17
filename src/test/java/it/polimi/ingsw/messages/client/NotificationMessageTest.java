package it.polimi.ingsw.messages.client;

import org.junit.jupiter.api.Test;

import static it.polimi.ingsw.messages.client.NotificationMessage.deserializeNotificationMessage;
import static org.junit.jupiter.api.Assertions.*;

class NotificationMessageTest {

    @Test
    void serialize() {
        NotificationMessage m = new NotificationMessage("player0","disconnect");
        assertEquals("{\"id\":\"notification\",\"username\":\"player0\",\"event\":\"disconnect\"}", m.serialize());
    }

    @Test
    void deserialize() {
        NotificationMessage m = deserializeNotificationMessage("{\"id\":\"notification\",\"username\":\"player0\",\"event\":\"disconnect\"}");
        assertEquals("notification", m.getId());
        assertEquals("disconnect", m.getEvent());
        assertEquals("player0", m.getUsername());
    }

}