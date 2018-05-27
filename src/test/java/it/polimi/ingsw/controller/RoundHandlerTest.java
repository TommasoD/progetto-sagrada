package it.polimi.ingsw.controller;

import org.junit.jupiter.api.Test;

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
    void nextTurn(){
        RoundHandler rh = new RoundHandler(2);
        rh.dump();
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
    }


}