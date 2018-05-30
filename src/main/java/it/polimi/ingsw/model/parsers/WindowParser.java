package it.polimi.ingsw.model.parsers;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import it.polimi.ingsw.model.Slot;
import it.polimi.ingsw.model.WindowPattern;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class WindowParser {

    private WindowPattern playerWindow  = null;

    private class WindowReader extends DefaultHandler {

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {


            if (qName.equalsIgnoreCase("window")) {

                //create a new window
                playerWindow = new WindowPattern();

            } else if (qName.equalsIgnoreCase("id")) {
                //read window name
                playerWindow.setName(attributes.getValue("name"));

            } else if (qName.equalsIgnoreCase("difficulty")) {
                //read token
                String token = attributes.getValue("token");
                playerWindow.setDifficultyToken(Integer.parseInt(token));

            } else if (qName.equalsIgnoreCase("limitation")) {
                //read window restriction
                String row = attributes.getValue("x");
                String column = attributes.getValue("y");
                String colorLimitation = attributes.getValue("color");
                String valueLimitation = attributes.getValue("value");
                playerWindow.setWindowMatrix(new Slot(colorLimitation, valueLimitation), Integer.parseInt(row), Integer.parseInt(column));
            }


        }
    }

    public WindowPattern readWindowPattern (String windowName) {

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        String path = System.getProperty("user.dir");
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            WindowReader handler = new WindowReader();
            saxParser.parse(new File(path + "\\src\\main\\java\\it\\polimi\\ingsw\\model\\windows\\" + windowName + ".xml"), handler);
        } catch ( Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return playerWindow;
    }

}


