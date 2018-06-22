package it.polimi.ingsw.messages.client;

import it.polimi.ingsw.messages.Message;
import it.polimi.ingsw.client.ClientManager;

/**
 * Abstract class representing a message sent to the client.
 */
public abstract class ClientMessage extends Message {

    /**
     * Visits the correct method in the visitor.
     * @param c the visitor client.
     */

    public abstract void accept(ClientManager c);

}
