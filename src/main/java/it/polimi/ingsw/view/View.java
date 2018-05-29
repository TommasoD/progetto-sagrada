package it.polimi.ingsw.view;

import com.google.gson.stream.JsonReader;
import it.polimi.ingsw.messages.ShowWindowsMessage;
import it.polimi.ingsw.utils.Observer;

import java.io.IOException;
import java.io.StringReader;

public class View implements Observer{

    //View observes Client

    public void update(Object message) {

        //string = metodo gson reader

        //if (string equals windoes) -> showwindowmex.deserialize(string)
        //
        // systemour (mex.getW1)
        String line = getIdMessage((String) message);
        if(line.equals("windows")) {
            ShowWindowsMessage mex = new ShowWindowsMessage();
            mex = mex.deserialize(line);
            System.out.println(mex.getW1());
            System.out.println(mex.getW2());
            System.out.println(mex.getW3());
            System.out.println(mex.getW4());
        }

    }

    private String getIdMessage(String s){
        JsonReader jsonReader = new JsonReader(new StringReader(s));
        String action;
        try{
            jsonReader.beginObject();
            jsonReader.nextName();
            action = jsonReader.nextString();
            jsonReader.close();
        } catch(IOException e){
            action = "error";
        }
        return action;
    }
}
