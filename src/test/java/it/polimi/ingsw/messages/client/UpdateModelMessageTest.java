package it.polimi.ingsw.messages.client;

import it.polimi.ingsw.model.Die;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.WindowPatternFactory;
import org.junit.jupiter.api.Test;

import static it.polimi.ingsw.messages.client.UpdateModelMessage.deserializeUpdateModelMessage;

class UpdateModelMessageTest {

    @Test
    void serialize_deserialize() {
        Game g = new Game();
        WindowPatternFactory wf = new WindowPatternFactory();
        g.addPlayer(new Player("Username", wf.createWindow("Kaleidoscopic Dream")));
        g.addPlayer(new Player("Username2", wf.createWindow("Bellesguard")));
        g.setDraft();
        UpdateModelMessage m = new UpdateModelMessage(g.getPlayers(), g.getDraft(), g.getRoundTrack());
        String s = m.serialize();
        System.out.println(s + "\n\n\n");
        UpdateModelMessage m2 = deserializeUpdateModelMessage(s);
        StringBuilder sb = new StringBuilder();
        for (Die d : m2.getDraft()) {
            sb.append(d.toString());
            sb.append(" ");
        }
        System.out.println("Draft: " + sb.toString() + "\u001B[0m");
        sb = new StringBuilder();
        for (Die d : m2.getRoundTrack()) {
            sb.append(d.toString());
            sb.append(" ");
        }
        System.out.println("Track: " + sb.toString() + "\u001B[0m");
        for(Player p : m2.getPlayers()){
            p.dump();
        }
    }
}