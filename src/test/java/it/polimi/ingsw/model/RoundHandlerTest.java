package it.polimi.ingsw.model;

import it.polimi.ingsw.controller.NewRoundException;
import it.polimi.ingsw.model.RoundHandler;
import org.junit.jupiter.api.Test;

//import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

class RoundHandlerTest {

    @Test
    void checkConstructor(){
        RoundHandler rh = new RoundHandler(4);
        rh.setCurrentPlayerIndex(3);
        rh.dump();
        assertEquals(3, rh.getCurrentPlayer());
    }

    @Test
    void checkConstructor2(){
        RoundHandler rh = new RoundHandler(2);
        rh.setCurrentPlayerIndex(2);
        rh.dump();
        assertEquals(1, rh.getCurrentPlayer());
    }

    @Test
    void nextRound(){
        RoundHandler rh = new RoundHandler(4);
        rh.nextRound();
        rh.dump();
        assertEquals(1, rh.getFirstPlayer());
        assertEquals(2, rh.getRound());
    }

    @Test
    void isGameEndedTest() {
        RoundHandler rh = new RoundHandler(4);
        rh.nextRound();
        assertFalse(rh.isGameEnded());
        rh.nextRound();
        assertFalse(rh.isGameEnded());
    }

    @Test
    void isGameEndedTest2() {
        RoundHandler rh = new RoundHandler(4);
        for(int i = 0; i < 10; i++){
            rh.nextRound();
        }
        assertTrue(rh.isGameEnded());
    }

    @Test
    void nextTurn() {
        RoundHandler rh = new RoundHandler(2);
        rh.dump();
        try{
            rh.nextTurn();
            assertEquals(1, rh.getCurrentPlayer());
            rh.nextTurn();
            assertEquals(1, rh.getCurrentPlayer());
            rh.nextTurn();
            assertEquals(0, rh.getCurrentPlayer());
            rh.nextTurn();
            assertEquals(1, rh.getCurrentPlayer());
            assertEquals(1, rh.getFirstPlayer());
            assertEquals(2, rh.getRound());
            rh.nextTurn();
            rh.nextTurn();
            rh.nextTurn();
            rh.nextTurn();
            assertEquals(0, rh.getCurrentPlayer());
            assertEquals(0, rh.getFirstPlayer());
        } catch (NewRoundException e){
            System.out.println("new round");
            rh.dump();
        }
    }

    @Test
    void endGame() {
        RoundHandler rh = new RoundHandler(2);
        for(int i = 0; i < 9; i++){
            rh.nextRound();
            System.out.println("ROUND: " + rh.getRound());
            rh.dump();
        }
        try{
            for(int i = 0; i < 4; i++){
                rh.nextTurn();
            }
        } catch (NewRoundException e){
            System.out.println("new round");
            rh.dump();
        }
        assertEquals(11, rh.getRound());
    }


}