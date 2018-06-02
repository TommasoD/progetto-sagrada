package it.polimi.ingsw.parsers;

import com.google.gson.stream.JsonReader;
import it.polimi.ingsw.messages.client.ClientMessage;
import it.polimi.ingsw.messages.client.ErrorMessage;
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

        //client messages to be implemented

        return new ErrorMessage(0);
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