package it.polimi.ingsw.parsers;


import java.io.InputStream;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import it.polimi.ingsw.model.Slot;
import it.polimi.ingsw.model.WindowPattern;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Public class used that receives as input the name
 * of a specific window and read its parameters from a xml file.
 * The parameters read are:
 * - the name of the window.
 * - the number of difficulty token.
 * For each slot it reads the relative color and value limitations.
 * In case there were no limitations, the value read is 'none'.
 */

public class WindowParser {

    private WindowPattern playerWindow  = null;

    private class WindowReader extends DefaultHandler {

        /**
         * Starts the parser and reads the window parameters.
         * @param uri
         * @param localName
         * @param qName
         * @param attributes set of parameters read.
         * @throws SAXException
         */

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

    /**
     * Instantiate the parser by looking at the specified
     * directory for the xml file that contains the values to be read.
     * @param windowName the name of the window whose parameters are to be read.
     * @return the window initialized with the parameters from file.
     */

    public WindowPattern readWindowPattern (String windowName) {

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            WindowReader handler = new WindowReader();
            InputStream path = getClass().getResourceAsStream("/" + windowName + ".xml");
            saxParser.parse(path, handler);
        } catch ( Exception e) {
            System.out.println("Exception in parsing the xml file");
            System.exit(1);
        }
        return playerWindow;
    }

}


