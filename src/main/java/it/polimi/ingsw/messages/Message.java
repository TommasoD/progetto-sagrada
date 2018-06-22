package it.polimi.ingsw.messages;

/**
 * Abstract class representing a message sent across the network
 */
public abstract class Message {

    /**
     * Converts the class in its Json representation.
     * @return Json representation.
     */

    public abstract String serialize();

}
