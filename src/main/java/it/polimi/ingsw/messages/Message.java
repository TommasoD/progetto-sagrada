package it.polimi.ingsw.messages;

public abstract class Message {

    /**
     * Converts the class in its Json representation.
     * @return Json representation.
     */

    public abstract String serialize();

}
