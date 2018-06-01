package it.polimi.ingsw.server;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CountdownTest {

    /**tested with 10 -> 10000, 20 -> 20000, 0 -> 20. */
    @Test
    void methods() {

        Countdown cd = new Countdown();
        cd.reset();
        while(cd.read() < 10) {};
        cd.stop();
        assertEquals(10, cd.read());
        cd.resetAndStop();
        assertEquals(0, cd.read());
        cd.reset();
        while(cd.read() < 10) {};
        assertEquals(10, cd.read());
        cd.stop();
        cd.resume();
        assertEquals(10, cd.read());
        while(cd.read() < 20) {};
        cd.stop();
        assertEquals(20, cd.read());
        assertEquals("" + 0, cd.toString());

    }
}