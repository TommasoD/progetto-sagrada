package it.polimi.ingsw.parsers;
import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class NetworkParser {

    private int port;
    private String ip;


    private class NetworkReader extends DefaultHandler {

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

            if (qName.equalsIgnoreCase("networkSetup")) {
                port = Integer.parseInt(attributes.getValue("ip"));
                ip = attributes.getValue("address");
            }
        }
    }

        public void readNetworkSetup() {

            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            String path = System.getProperty("user.dir");
            try {
                SAXParser saxParser = saxParserFactory.newSAXParser();
                NetworkParser.NetworkReader handler = new NetworkParser.NetworkReader();
                saxParser.parse(new File(path + "/src/main/resources/networkSetup.xml"), handler);
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
}
