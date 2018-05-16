package it.polimi.ingsw.modeltests;
import it.polimi.ingsw.model.WindowPattern;
import it.polimi.ingsw.model.WindowPatternFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WindowPatternFactoryTest {

    @Test
    void createWindow() {
        WindowPatternFactory wf = new WindowPatternFactory();
        WindowPattern w = wf.createWindow("Kaleidoscopic Dream");
        assertEquals("Kaleidoscopic Dream", w.getName());
    }

    @Test
    void getAllWindows() {
        WindowPatternFactory wf = new WindowPatternFactory();
        WindowPattern w;
        for(int i = 0; i < 24; i++){
            w = wf.getWindow();
        }
        assertEquals(null, wf.getWindow());
    }
}