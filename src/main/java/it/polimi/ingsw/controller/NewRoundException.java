package it.polimi.ingsw.controller;

public class NewRoundException extends Exception {

    private final int round;

    public NewRoundException(int round) {
        this.round = round;
    }

    public int getRound() {
        return round;
    }
}
