package it.polimi.ingsw.parsers;

import it.polimi.ingsw.messages.Message;
import it.polimi.ingsw.messages.controller.*;
import it.polimi.ingsw.messages.client.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GsonParserTest {

    /*
        parse controller
     */

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
    void parseUnexpectedController(){
        GsonParser gp = new GsonParser();
        Message message = gp.parseController("test");
        assertTrue(message instanceof UnexpectedMessage);
    }

    /*
        parse client
     */

    @Test
    void parseError(){
        GsonParser gp = new GsonParser();
        ErrorMessage m = new ErrorMessage();
        Message message = gp.parseClient(m.serialize());
        assertTrue(message instanceof ErrorMessage);
    }

    @Test
    void parseNewTurn(){
        GsonParser gp = new GsonParser();
        NewTurnMessage m = new NewTurnMessage();
        Message message = gp.parseClient(m.serialize());
        assertTrue(message instanceof NewTurnMessage);
    }

    @Test
    void parseShowWindows(){
        GsonParser gp = new GsonParser();
        ShowWindowsMessage m = new ShowWindowsMessage();
        Message message = gp.parseClient(m.serialize());
        assertTrue(message instanceof ShowWindowsMessage);
    }

    @Test
    void parseOk(){
        GsonParser gp = new GsonParser();
        OkMessage m = new OkMessage();
        Message message = gp.parseClient(m.serialize());
        assertTrue(message instanceof OkMessage);
    }

    @Test
    void parseUpdate(){
        GsonParser gp = new GsonParser();
        UpdateModelMessage m = new UpdateModelMessage();
        Message message = gp.parseClient(m.serialize());
        assertTrue(message instanceof UpdateModelMessage);
    }

    @Test
    void parseUnexpectedClient(){
        GsonParser gp = new GsonParser();
        Message message = gp.parseClient("test");
        assertTrue(message instanceof ErrorMessage);
    }


}