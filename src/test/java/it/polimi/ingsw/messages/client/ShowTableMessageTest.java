package it.polimi.ingsw.messages.client;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.ToolCard;
import it.polimi.ingsw.model.objectives.publicobjectives.PublicObjective;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static it.polimi.ingsw.messages.client.ShowTableMessage.deserializeShowTableMessage;
import static org.junit.jupiter.api.Assertions.*;

class ShowTableMessageTest {

    @Test
    void serialize_deserialize() {
        Game g = new Game();
        g.addPlayer(new Player("Username"));
        g.addPlayer(new Player("Username2"));
        g.initialize();
        g.dump();
        ArrayList<String> obj = new ArrayList<String>();
        for(PublicObjective o : g.getPublicObjectiveActive()){
            obj.add(o.toString());
        }
        String s = new ShowTableMessage(g.getPlayers(0).getPlayerObjective(), g.getToolCards(), obj).serialize();
        System.out.println(s + "\n\n\n");
        ShowTableMessage m2 = deserializeShowTableMessage(s);
        System.out.println("Your private objective is: " + m2.getPrivateObjective().toString());
        System.out.println("Tool Cards\n");
        for (ToolCard d : m2.getToolCards()) {
            d.dump();
        }
        System.out.println("Public Objectives\n");
        for (String d : m2.getPublicObjective()) {
            System.out.println(d.toString());
        }
    }

}