package it.polimi.ingsw.messages.controller;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.messages.Message;

/**
 * Abstract class representing a message sent to the controller.
 */
public abstract class ControllerMessage extends Message {

    /**
     * Visits the correct method in the visitor.
     * @param c the controller.
     * @param player the ID of the player who's performing an action.
     */

    public abstract void accept(Controller c, int player);

}
