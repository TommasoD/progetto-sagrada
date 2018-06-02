package it.polimi.ingsw.messages.client;

import it.polimi.ingsw.messages.client.ShowWindowsMessage;
import it.polimi.ingsw.model.WindowPatternFactory;
import org.junit.jupiter.api.Test;

class ShowWindowsMessageTest {

    @Test
    void serialize_deserialize() {
        WindowPatternFactory wf = new WindowPatternFactory();
        ShowWindowsMessage m = new ShowWindowsMessage(wf.getWindow(), wf.getWindow(), wf.getWindow(), wf.getWindow());
        String s = m.serialize();
        System.out.println(s);

        ShowWindowsMessage m2 = new ShowWindowsMessage();
        m2 = m2.deserialize(s);

        System.out.println(m2.getW1());
        System.out.println(m2.getW2());
        System.out.println(m2.getW3());
        System.out.println(m2.getW4());

    }
}