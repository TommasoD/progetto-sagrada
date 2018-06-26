package it.polimi.ingsw.controller;

/**
 * This exception is thrown when a new round starts together with a new turn,
 * to distinguish the event from a simple new turn in the controller.
 */
public class NewRoundException extends Exception {

    private final int round;

    /**
     * Class constructor specifying the number of the round.
     * @param round the current round as an integer.
     */

    public NewRoundException(int round) {
        this.round = round;
    }

    /**
     *
     * @return the current round as an integer.
     */

    public int getRound() {
        return round;
    }
}
