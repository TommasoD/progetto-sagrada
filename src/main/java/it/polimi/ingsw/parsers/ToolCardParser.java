package it.polimi.ingsw.parsers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import it.polimi.ingsw.model.ToolCard;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class ToolCardParser {

    private ArrayList<ToolCard> toolCards = new ArrayList<ToolCard>();
    private ToolCard toolCard = null;

    private class ToolCardReader extends DefaultHandler {

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


    public ArrayList<ToolCard> readToolCards() {

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        String path = System.getProperty("user.dir");
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            ToolCardParser.ToolCardReader handler = new ToolCardParser.ToolCardReader();
                saxParser.parse(new File(path + "/src/main/resources/toolcards.xml"), handler);

            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
            return toolCards;
        }

}