package it.polimi.ingsw.model;
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

    @Test
    void printAllWindowTest(){
        String[] windows = {
                "Kaleidoscopic Dream",
                "Aurorae Magnificus",
                "Sun Catcher",
                "Virtus",
                "Via Lux",
                "Bellesguard",
                "Shadow Thief",
                "Aurora Sagradis",
                "Firmitas",
                "Batllo",
                "Industria",
                "Symphony of Light",
                "Firelight",
                "Lux Astram",
                "Gravitas",
                "Luz Celestial",
                "Chromatic Splendor",
                "Fractal Drops",
                "Ripples of Lights",
                "Comitas",
                "Fulgor del Ciel",
                "Water of Light",
                "Lux Mundi",
                "Sun's Glory"
        };
        WindowPatternFactory wf = new WindowPatternFactory();
        for(String s : windows){
            wf.createWindow(s).dump();
        }
    }
}