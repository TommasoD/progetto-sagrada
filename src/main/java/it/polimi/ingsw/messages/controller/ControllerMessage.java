package it.polimi.ingsw.messages.controller;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.messages.Message;

public abstract class ControllerMessage extends Message {

    public abstract void accept(Controller c, int player);

}
