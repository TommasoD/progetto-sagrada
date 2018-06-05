package it.polimi.ingsw.parsers;

import it.polimi.ingsw.model.ToolCard;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class ToolCardParserTest {

    @Test
    void readToolCards(){
        ToolCardParser reader = new ToolCardParser();
        ArrayList<ToolCard> t = new ArrayList<ToolCard>(12);
        t.addAll(reader.readToolCards());

        assertEquals(t.get(0).getName(),"Grozing Pliers");
        assertEquals(t.get(1).getName(),"Englomise Brush");
        assertEquals(t.get(2).getName(),"Copper Foil Burnisher");
        assertEquals(t.get(3).getName(),"Lathekin");
        assertEquals(t.get(4).getName(),"Lens Cutter");
        assertEquals(t.get(5).getName(),"Flux Brush");
        assertEquals(t.get(6).getName(),"Glazing Hammer");
        assertEquals(t.get(7).getName(),"Running Pliers");
        assertEquals(t.get(8).getName(),"Cork-backed Straightedge");
        assertEquals(t.get(9).getName(),"Grinding Stone");
        assertEquals(t.get(10).getName(),"Flux Remover");
        assertEquals(t.get(11).getName(),"Tap Wheel");
    }
}
