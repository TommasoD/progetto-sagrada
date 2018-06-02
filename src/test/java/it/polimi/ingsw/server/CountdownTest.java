package it.polimi.ingsw.server;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CountdownTest {

    @Test
    void methods() {

        Countdown cd = new Countdown(new Server());
        cd.reset();
        while(cd.read() < 10000) {};
        cd.stopClock();
        assertEquals("" + 10, cd.toString());
        cd.resetAndStop();
        assertEquals("" + 0, cd.toString());
        cd.reset();
        while(cd.read() < 10000) {};
        assertEquals("" + 10, cd.toString());
        cd.stopClock();
        cd.resumeClock();
        assertEquals("" + 10, cd.toString());
        while(cd.read() < 20000) {};
        cd.stopClock();
        assertEquals("" + 20, cd.toString());

    }
}