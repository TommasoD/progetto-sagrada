package it.polimi.ingsw.parsers;

import com.google.gson.stream.JsonReader;
import it.polimi.ingsw.messages.client.*;
import it.polimi.ingsw.messages.controller.*;

import java.io.IOException;
import java.io.StringReader;

import static it.polimi.ingsw.messages.client.DieColorMessage.deserializeDieColorMessage;
import static it.polimi.ingsw.messages.client.EndTurnMessage.deserializeEndTurnMessage;
import static it.polimi.ingsw.messages.client.ErrorMessage.deserializeErrorMessage;
import static it.polimi.ingsw.messages.client.GameOverMessage.deserializeGameOverMessage;
import static it.polimi.ingsw.messages.client.LoginRequestMessage.deserializeLoginRequestMessage;
import static it.polimi.ingsw.messages.client.NewTurnMessage.deserializeNewTurnMessage;
import static it.polimi.ingsw.messages.client.NotificationMessage.deserializeNotificationMessage;
import static it.polimi.ingsw.messages.client.OkMessage.deserializeOkMessage;
import static it.polimi.ingsw.messages.client.ShowTableMessage.deserializeShowTableMessage;
import static it.polimi.ingsw.messages.client.ShowWindowsMessage.deserializeShowWindowsMessage;
import static it.polimi.ingsw.messages.client.UpdateModelMessage.deserializeUpdateModelMessage;
import static it.polimi.ingsw.messages.controller.ChooseWindowMessage.deserializeChooseWindowMessage;
import static it.polimi.ingsw.messages.controller.LoginMessage.deserializeLoginMessage;
import static it.polimi.ingsw.messages.controller.LogoutMessage.deserializeLogoutMessage;
import static it.polimi.ingsw.messages.controller.PassMessage.deserializePassMessage;
import static it.polimi.ingsw.messages.controller.ReconnectMessage.deserializeReconnectMessage;
import static it.polimi.ingsw.messages.controller.SetDieMessage.deserializeSetDieMessage;
import static it.polimi.ingsw.messages.controller.ShowTableRequestMessage.deserializeShowTableRequestMessage;
import static it.polimi.ingsw.messages.controller.ToolCardAMessage.deserializeToolCardAMessage;
import static it.polimi.ingsw.messages.controller.ToolCardBMessage.deserializeToolCardBMessage;
import static it.polimi.ingsw.messages.controller.ToolCardCMessage.deserializeToolCardCMessage;
import static it.polimi.ingsw.messages.controller.ToolCardDMessage.deserializeToolCardDMessage;
import static it.polimi.ingsw.messages.controller.ToolCardEMessage.deserializeToolCardEMessage;

/**
 * A parser to analyze and manage Json string representing classes.
 * Classes represents events and are sent across the network thanks to Gson,
 * from the server to each client and vice versa.
 */
public class GsonParser {

    /**
     * Analyzes Json data on the server side, returning an instance of the corresponding class,
     * with a bunch of information describing a certain event (i.e. a move from a player).
     * @param gson the Json representation of a class.
     * @return the instance of the class.
     */

    public ControllerMessage parseController(String gson) {
        String id = getIdMessage(gson);
        if(id.equals("login")){
            return deserializeLoginMessage(gson);
        }
        if(id.equals("window")){
            return deserializeChooseWindowMessage(gson);
        }
        if(id.equals("pass")){
            return deserializePassMessage(gson);
        }
        if(id.equals("place")){
            return deserializeSetDieMessage(gson);
        }
        if(id.equals("quit")){
            return deserializeLogoutMessage(gson);
        }
        if(id.equals("toolcardA")){
            return deserializeToolCardAMessage(gson);
        }
        if(id.equals("toolcardB")){
            return deserializeToolCardBMessage(gson);
        }
        if(id.equals("toolcardC")){
            return deserializeToolCardCMessage(gson);
        }
        if(id.equals("toolcardD")){
            return deserializeToolCardDMessage(gson);
        }
        if(id.equals("toolcardE")){
            return deserializeToolCardEMessage(gson);
        }
        if(id.equals("show_table")){
            return deserializeShowTableRequestMessage(gson);
        }
        if(id.equals("reconnect")){
            return deserializeReconnectMessage(gson);
        }
        return new UnexpectedMessage();
    }

    /**
     * Analyzes Json data on the client side, returning an instance of the corresponding class,
     * with a bunch of information describing a certain event (someone disconnected, a player made a move etc.).
     * @param gson the Json representation of a class.
     * @return the instance of the class.
     */

    public ClientMessage parseClient(String gson){
        String id = getIdMessage(gson);
        if(id.equals("turn")){
            return deserializeNewTurnMessage(gson);
        }
        if(id.equals("ok")){
            return deserializeOkMessage(gson);
        }
        if(id.equals("login")){
            return deserializeLoginRequestMessage(gson);
        }
        if(id.equals("windows")){
            return deserializeShowWindowsMessage(gson);
        }
        if(id.equals("update")){
            return deserializeUpdateModelMessage(gson);
        }
        if(id.equals("end")){
            return deserializeEndTurnMessage(gson);
        }
        if(id.equals("error")){
            return deserializeErrorMessage(gson);
        }
        if(id.equals("notification")){
            return deserializeNotificationMessage(gson);
        }
        if(id.equals("show_table")){
            return deserializeShowTableMessage(gson);
        }
        if(id.equals("game_over")){
            return deserializeGameOverMessage(gson);
        }
        if(id.equals("die_color")){
            return deserializeDieColorMessage(gson);
        }
        return new ErrorMessage(3);
    }

    /**
     * Analyzes the first string containing the identifier of the represented class and returns it.
     * In case it fails to identify the string, returns a fixed value.
     * @param gson the Json representation of a class.
     * @return the identifier of the class as a string.
     */

    private String getIdMessage(String gson) {
        JsonReader jsonReader = new JsonReader(new StringReader(gson));
        String id;
        try {
            jsonReader.beginObject();
            jsonReader.nextName();
            id = jsonReader.nextString();
            jsonReader.close();
        } catch (IOException e) {
            id = "fail";
        }
        return id;
    }

}