package it.polimi.ingsw.parsers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import it.polimi.ingsw.model.ToolCard;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Public class used to read from a specific xml file
 * the parameters of the 12 tool cards to use during the game.
 * Every tool cards has:
 * - a specific id, from 1 to 12.
 * - a name.
 * - a description that shows the move that the card allows to make.
 */

public class ToolCardParser {

    private ArrayList<ToolCard> toolCards = new ArrayList<ToolCard>();
    private ToolCard toolCard = null;

    private class ToolCardReader extends DefaultHandler {

        /**
         * Starts the parser and reads the tool cards parameters.
         * @param uri
         * @param localName
         * @param qName
         * @param attributes set of parameters read.
         * @throws SAXException
         */

        @Override
        public void startElement(java.lang.String uri, java.lang.String localName, java.lang.String qName, Attributes attributes) throws SAXException {


            if (qName.equalsIgnoreCase("toolcard")) {
                int index = Integer.parseInt(attributes.getValue("id"));
                String name = attributes.getValue("name");
                String description = attributes.getValue("description");
                toolCard = new ToolCard(index-1, name, description);

                toolCards.add(toolCard);
            }
        }
    }

    /**
     * Instantiate the parser by looking at the specified
     * directory for the xml file that contains the values to be read.
     * @return the set of read tool cards
     */

    public ArrayList<ToolCard> readToolCards() {

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            ToolCardParser.ToolCardReader handler = new ToolCardParser.ToolCardReader();
            InputStream path = getClass().getResourceAsStream("/toolcards.xml");
            saxParser.parse(path, handler);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
            return toolCards;
        }

}