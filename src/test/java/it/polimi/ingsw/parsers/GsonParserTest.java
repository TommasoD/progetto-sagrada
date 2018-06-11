package it.polimi.ingsw.parsers;

import it.polimi.ingsw.messages.Message;
import it.polimi.ingsw.messages.controller.*;
import it.polimi.ingsw.messages.client.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GsonParserTest {

    /*
    ------------------------------------
        parse controller
    ------------------------------------
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
    void parseToolcardA(){
        GsonParser gp = new GsonParser();
        ToolCardAMessage m = new ToolCardAMessage();
        Message message = gp.parseController(m.serialize());
        assertTrue(message instanceof ToolCardAMessage);
    }

    @Test
    void parseToolcardB(){
        GsonParser gp = new GsonParser();
        ToolCardBMessage m = new ToolCardBMessage();
        Message message = gp.parseController(m.serialize());
        assertTrue(message instanceof ToolCardBMessage);
    }

    @Test
    void parseToolcardC(){
        GsonParser gp = new GsonParser();
        ToolCardCMessage m = new ToolCardCMessage();
        Message message = gp.parseController(m.serialize());
        assertTrue(message instanceof ToolCardCMessage);
    }

    @Test
    void parseToolcardD(){
        GsonParser gp = new GsonParser();
        ToolCardDMessage m = new ToolCardDMessage();
        Message message = gp.parseController(m.serialize());
        assertTrue(message instanceof ToolCardDMessage);
    }

    @Test
    void parseToolcardE(){
        GsonParser gp = new GsonParser();
        ToolCardEMessage m = new ToolCardEMessage();
        Message message = gp.parseController(m.serialize());
        assertTrue(message instanceof ToolCardEMessage);
    }

    @Test
    void parseShowTableRequest(){
        GsonParser gp = new GsonParser();
        ShowTableRequestMessage m = new ShowTableRequestMessage();
        Message message = gp.parseController(m.serialize());
        assertTrue(message instanceof ShowTableRequestMessage);
    }

    @Test
    void parseReconnect(){
        GsonParser gp = new GsonParser();
        ReconnectMessage m = new ReconnectMessage();
        Message message = gp.parseController(m.serialize());
        assertTrue(message instanceof ReconnectMessage);
    }

    @Test
    void parseUnexpectedController(){
        GsonParser gp = new GsonParser();
        Message message = gp.parseController("test");
        assertTrue(message instanceof UnexpectedMessage);
    }

    /*
    ------------------------------------
        parse client
    ------------------------------------
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
    void parseLoginRequest(){
        GsonParser gp = new GsonParser();
        LoginRequestMessage m = new LoginRequestMessage();
        Message message = gp.parseClient(m.serialize());
        assertTrue(message instanceof LoginRequestMessage);
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
    void parseNotification(){
        GsonParser gp = new GsonParser();
        NotificationMessage m = new NotificationMessage();
        Message message = gp.parseClient(m.serialize());
        assertTrue(message instanceof NotificationMessage);
    }

    @Test
    void parseShowTable(){
        GsonParser gp = new GsonParser();
        ShowTableMessage m = new ShowTableMessage();
        Message message = gp.parseClient(m.serialize());
        assertTrue(message instanceof ShowTableMessage);
    }

    @Test
    void parseGameOver(){
        GsonParser gp = new GsonParser();
        GameOverMessage m = new GameOverMessage();
        Message message = gp.parseClient(m.serialize());
        assertTrue(message instanceof GameOverMessage);
    }

    @Test
    void parseDieColor(){
        GsonParser gp = new GsonParser();
        DieColorMessage m = new DieColorMessage();
        Message message = gp.parseClient(m.serialize());
        assertTrue(message instanceof DieColorMessage);
    }

    @Test
    void parseUnexpectedClient(){
        GsonParser gp = new GsonParser();
        Message message = gp.parseClient("test");
        assertTrue(message instanceof ErrorMessage);
    }


}