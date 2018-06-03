package it.polimi.ingsw.controller;

import it.polimi.ingsw.messages.controller.*;
import it.polimi.ingsw.messages.client.ShowWindowsMessage;
import org.junit.jupiter.api.Test;

class ControllerTest {

    @Test
    void visitLogin(){
        Controller c = new Controller();
        String s = new LoginMessage().serialize();
        c.update(s, 2);
    }

    @Test
    void visitWindow(){
        Controller c = new Controller();
        String s = new ChooseWindowMessage().serialize();
        c.update(s, 2);
    }

    @Test
    void visitLogout(){
        Controller c = new Controller();
        String s = new LogoutMessage().serialize();
        c.update(s, 2);
    }

    @Test
    void visitPass(){
        Controller c = new Controller();
        String s = new PassMessage().serialize();
        c.update(s, 2);
    }

    @Test
    void visitSetDie(){
        Controller c = new Controller();
        String s = new SetDieMessage().serialize();
        c.update(s, 2);
    }

    @Test
    void visitInCaseOfError(){
        Controller c = new Controller();
        String s = new ShowWindowsMessage().serialize();
        c.update(s, 2);
    }

    /*@Test
    void handleMoveTestCasePlaceValid(){
        Controller c = new Controller();
        c.addPlayer("user1");
        c.setWindowPattern(0, "Kaleidoscopic Dream");
        c.newMatch();
        c.update("{\"id\":\"place\",\"x\":3,\"y\":0,\"index\":1}", 0);
        c.getGame().getPlayers(0).dump();
    }

    @Test
    void handleMoveTestCasePlaceInvalid1(){
        Controller c = new Controller();
        c.addPlayer("user1");
        c.setWindowPattern(0, "Kaleidoscopic Dream");
        c.newMatch();
        //c.update("{\"id\":\"place\",\"x\":2,\"y\":2,\"index\":1}", 1);
        c.getGame().getPlayers(0).dump();
    }

    @Test
    void handleMoveTestCasePlaceInvalid2(){
        Controller c = new Controller();
        c.addPlayer("user1");
        c.setWindowPattern(0, "Kaleidoscopic Dream");
        c.newMatch();
        Die d = new Die("BLUE");
        d.roll();
        c.getGame().setDieDraft(d);
        c.update("{\"id\":\"place\",\"x\":2,\"y\":2,\"index\":3}", 0);
        c.getGame().getPlayers(0).dump();
    }*/
}