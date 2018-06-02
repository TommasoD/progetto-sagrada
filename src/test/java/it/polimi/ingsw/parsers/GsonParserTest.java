package it.polimi.ingsw.parsers;

import it.polimi.ingsw.messages.controller.*;
import it.polimi.ingsw.messages.Message;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GsonParserTest {

    @Test
    void parseLogin(){
        GsonParser gp = new GsonParser();
        LoginMessage m = new LoginMessage();
        Message message = gp.parseController(m.serialize());
        assertTrue(message instanceof LoginMessage);
    }

    @Test
    void parseChooseWindow(){
        GsonParser gp = new GsonParser();
        ChooseWindowMessage m = new ChooseWindowMessage();
        Message message = gp.parseController(m.serialize());
        assertTrue(message instanceof ChooseWindowMessage);
    }

    @Test
    void parsePass(){
        GsonParser gp = new GsonParser();
        PassMessage m = new PassMessage();
        Message message = gp.parseController(m.serialize());
        assertTrue(message instanceof PassMessage);
    }

    @Test
    void parseLogout(){
        GsonParser gp = new GsonParser();
        LogoutMessage m = new LogoutMessage();
        Message message = gp.parseController(m.serialize());
        assertTrue(message instanceof LogoutMessage);
    }

    @Test
    void parseSetDie(){
        GsonParser gp = new GsonParser();
        SetDieMessage m = new SetDieMessage();
        Message message = gp.parseController(m.serialize());
        assertTrue(message instanceof SetDieMessage);
    }

    @Test
    void parseError(){
        GsonParser gp = new GsonParser();
        Message message = gp.parseController("test");
        assertTrue(message instanceof UnexpectedMessage);
    }
}