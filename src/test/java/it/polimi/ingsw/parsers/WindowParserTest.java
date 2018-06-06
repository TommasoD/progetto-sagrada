package it.polimi.ingsw.parsers;

import it.polimi.ingsw.model.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WindowParserTest {

    @Test
    void WindowReaderTest() {
        WindowParser reader = new WindowParser();
        Player p = new Player("user");
        String windowName = "window1";
        p.setPlayerWindow(reader.readWindowPattern(windowName));

        assertEquals("Kaleidoscopic Dream",p.getPlayerWindow().getName());
        assertEquals(4,p.getPlayerWindow().getDifficultyToken());
        assertEquals("YELLOW",p.getPlayerWindow().getWindowMatrix(0,0).getColorRule());
        assertEquals("none",p.getPlayerWindow().getWindowMatrix(0,0).getValueRule());

        assertEquals("none",p.getPlayerWindow().getWindowMatrix(4,0).getColorRule());
        assertEquals("1",p.getPlayerWindow().getWindowMatrix(4,0).getValueRule());
    }
}
