package it.polimi.ingsw.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SlotTest {

    @Test
    void notEmptySlot() {
        Slot s = new Slot("none", "none");
        Die d = new Die("RED");
        s.setDie(d);
        assertEquals(true, s.isNotEmpty());
    }

    @Test
    void emptySlot() {
        Slot s = new Slot("none", "none");
        assertEquals(false, s.isNotEmpty());
    }

    @Test
    void colorRuleFalse() {
        Slot s = new Slot("BLUE", "none");
        Die d = new Die("RED");
        assertEquals(false, s.checkColorRule(d));
    }

    @Test
    void colorRuleTrue() {
        Slot s = new Slot("RED", "none");
        Die d = new Die("RED");
        assertEquals(true, s.checkColorRule(d));
    }

    @Test
    void colorRuleTrue2() {
        Slot s = new Slot("none", "none");
        Die d = new Die("RED");
        assertEquals(true, s.checkColorRule(d));
    }

    @Test
    void valueRuleFalse() {
        Slot s = new Slot("none", "6");
        Die d = new Die("RED");
        d.roll();
        while(d.getValue().equals("6")){
            d.roll();
        }
        assertEquals(false, s.checkValueRule(d));
    }

    @Test
    void valueRuleTrue() {
        Slot s = new Slot("none", "6");
        Die d = new Die("RED");
        d.roll();
        while(!d.getValue().equals("6")){
            d.roll();
        }
        assertEquals(true, s.checkValueRule(d));
    }

    @Test
    void valueRuleTrue2() {
        Slot s = new Slot("none", "none");
        Die d = new Die("RED");
        d.roll();
        assertEquals(true, s.checkValueRule(d));
    }

    @Test
    void notValid() {
        Slot s = new Slot("none", "none");
        Die d = new Die("RED");
        Die d2 = new Die("BLUE");
        s.setDie(d);
        assertEquals(false, s.isValid(d2));
    }

    @Test
    void valid() {
        Slot s = new Slot("none", "none");
        Die d = new Die("RED");
        assertEquals(true, s.isValid(d));
    }
}