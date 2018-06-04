package it.polimi.ingsw.messages.client;

import it.polimi.ingsw.messages.Message;
import it.polimi.ingsw.client.ClientManager;

public abstract class ClientMessage extends Message {

    public abstract void accept(ClientManager c, int player);

}
