package it.polimi.ingsw.server;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CountdownTest {

    @Test
    void methods() {

        Countdown cd = new Countdown();
        cd.reset();
        while(cd.read() < 1000) {};
        cd.stopClock();
        assertEquals("" + 1, cd.toString());
        cd.resetAndStop();
        assertEquals("" + 0, cd.toString());
        cd.reset();
        while(cd.read() < 1000) {};
        assertEquals("" + 1, cd.toString());
        cd.stopClock();
        cd.resumeClock();
        assertEquals("" + 1, cd.toString());
        while(cd.read() < 2000) {};
        cd.stopClock();
        assertEquals("" + 2, cd.toString());

    }
}
