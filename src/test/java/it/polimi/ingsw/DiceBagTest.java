package it.polimi.ingsw;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DiceBagTest {
    @Test
    void fullBag(){
        DiceBag db = new DiceBag();
        int size = db.getSize();
        assertEquals(90, size);
    }

    @Test
    void emptyBag(){
        DiceBag db = new DiceBag();
        for(int i = 0; i < 90; i++) db.getDie();
        int size = db.getSize();
        assertEquals(0, size);
    }

    @Test
    void addDie(){
        DiceBag db = new DiceBag();
        Die d = new Die("RED");
        db.addDie(d);
        assertEquals(91, db.getSize());
    }

}