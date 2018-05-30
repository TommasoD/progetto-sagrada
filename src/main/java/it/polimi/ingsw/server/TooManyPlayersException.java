package it.polimi.ingsw.server;

public class TooManyPlayersException extends Exception {

    public TooManyPlayersException () {}
    public TooManyPlayersException (String message) {
        super(message);
    }

}
