package it.polimi.ingsw.messages.client;

import it.polimi.ingsw.messages.Message;
import it.polimi.ingsw.view.View;

public abstract class ClientMessage extends Message {

    public abstract void accept(View v);

}