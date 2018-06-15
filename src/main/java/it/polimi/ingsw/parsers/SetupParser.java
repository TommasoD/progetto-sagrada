package it.polimi.ingsw.parsers;
import java.io.InputStream;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SetupParser {

    private int port;
    private String ip;
    private int countdownConnection;
    private int countdownMove;


    private class SetupReader extends DefaultHandler {

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

            if (qName.equalsIgnoreCase("network")) {
                port = Integer.parseInt(attributes.getValue("ip"));
                ip = attributes.getValue("address");
            }

            if(qName.equalsIgnoreCase("countdown")) {
                countdownConnection = Integer.parseInt(attributes.getValue("connection"));
                countdownMove = Integer.parseInt(attributes.getValue("move"));
            }


        }
    }

        public void readSetup() {

            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            try {
                SAXParser saxParser = saxParserFactory.newSAXParser();
                SetupParser.SetupReader handler = new SetupParser.SetupReader();
                InputStream path = getClass().getResourceAsStream("/setup.xml");
                saxParser.parse(path, handler);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

        public String getIp () {
            return ip;
        }

        public int getPort() {
            return port;
        }

        public int getCountdownConnection() {return countdownConnection;}

        public int getCountdownMove() {return countdownMove;}
}
