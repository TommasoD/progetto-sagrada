package it.polimi.ingsw.parsers;

import com.google.gson.stream.JsonReader;
import it.polimi.ingsw.messages.client.*;
import it.polimi.ingsw.messages.controller.*;

import java.io.IOException;
import java.io.StringReader;

public class GsonParser {

    public ControllerMessage parseController(String gson) {
        String id = getIdMessage(gson);
        if(id.equals("login")){
            LoginMessage m = new LoginMessage();
            return m.deserialize(gson);
        }
        if(id.equals("window")){
            ChooseWindowMessage m = new ChooseWindowMessage();
            return m.deserialize(gson);
        }
        if(id.equals("pass")){
            PassMessage m = new PassMessage();
            return m.deserialize(gson);
        }
        if(id.equals("place")){
            SetDieMessage m = new SetDieMessage();
            return m.deserialize(gson);
        }
        if(id.equals("quit")){
            LogoutMessage m = new LogoutMessage();
            return m.deserialize(gson);
        }
        return new UnexpectedMessage();
    }

    public ClientMessage parseClient(String gson){
        String id = getIdMessage(gson);
        if(id.equals("turn")){
            NewTurnMessage m = new NewTurnMessage();
            return m.deserialize(gson);
        }
        if(id.equals("ok")){
            OkMessage m = new OkMessage();
            return m.deserialize(gson);
        }
        if(id.equals("windows")){
            ShowWindowsMessage m = new ShowWindowsMessage();
            return m.deserialize(gson);
        }
        if(id.equals("update")){
            UpdateModelMessage m = new UpdateModelMessage();
            return m.deserialize(gson);
        }
        if(id.equals("error")){
            ErrorMessage m = new ErrorMessage();
            return m.deserialize(gson);
        }
        return new ErrorMessage(3);
    }

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