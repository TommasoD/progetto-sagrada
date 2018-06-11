package it.polimi.ingsw.parsers;
import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SetupParser {

    private int port;
    private String ip;
    private int countdownConnession;
    private int countdownMove;


    private class NetworkReader extends DefaultHandler {

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

            if (qName.equalsIgnoreCase("network")) {
                port = Integer.parseInt(attributes.getValue("ip"));
                ip = attributes.getValue("address");
            }

            if(qName.equalsIgnoreCase("countdown")) {
                countdownConnession = Integer.parseInt(attributes.getValue("connection"));
                countdownMove = Integer.parseInt(attributes.getValue("move"));
            }


        }
    }

        public void readSetup() {

            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            String path = System.getProperty("user.dir");
            try {
                SAXParser saxParser = saxParserFactory.newSAXParser();
                SetupParser.NetworkReader handler = new SetupParser.NetworkReader();
                saxParser.parse(new File(path + "/src/main/resources/setup.xml"), handler);
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

        public int getCountdownConnection() {return countdownConnession;}

        public int getCountdownMove() {return countdownMove;}
}
