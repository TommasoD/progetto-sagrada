package it.polimi.ingsw.model;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
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
                it.polimi.ingsw.model.NetworkParser.NetworkReader handler = new it.polimi.ingsw.model.NetworkParser.NetworkReader();
                saxParser.parse(new File(path + "\\src\\main\\java\\it\\polimi\\ingsw\\model\\networkSetup.xml"), handler);
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
