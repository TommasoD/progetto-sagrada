package it.polimi.ingsw.messages.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnexpectedMessageTest {

    @Test
    void serialize() {
        UnexpectedMessage m = new UnexpectedMessage();
        assertEquals("{}", m.serialize());
    }

}