package it.polimi.ingsw.messages.client;

import org.junit.jupiter.api.Test;

import static it.polimi.ingsw.messages.client.GameOverMessage.deserializeGameOverMessage;
import static org.junit.jupiter.api.Assertions.*;

class GameOverMessageTest {

    @Test
    void serialize() {
        GameOverMessage m = new GameOverMessage("player0");
        assertEquals("{\"id\":\"game_over\",\"winner\":\"player0\"}", m.serialize());
    }

    @Test
    void deserialize() {
        GameOverMessage m = deserializeGameOverMessage("{\"id\":\"game_over\",\"winner\":\"player0\"}");
        assertEquals("game_over", m.getId());
        assertEquals("player0", m.getWinner());
    }

}