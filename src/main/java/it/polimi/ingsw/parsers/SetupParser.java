package it.polimi.ingsw.parsers;
import java.io.File;
import java.io.FileNotFoundException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/**
 * Public class used to read parameters from a specific xml file
 * that contains:
 * - the ip address to which the client must connect if the
 *  value entered by the user is wrong.
 * - the specific port to which the server and the client must connect.
 * - the time value within the clients must successfully complete
 *  the connection phase if they want to participate in the game.
 * - the time value within the clients must make a move during the game.
 */

public class SetupParser {

    private int port;
    private String ip;
    private int countdownConnection;
    private int countdownMove;


    private class SetupReader extends DefaultHandler {

        /**
         * Starts the parser and reads the setup parameters.
         * @param uri
         * @param localName
         * @param qName to identify the element to be read.
         * @param attributes set of parameters read.
         * @throws SAXException
         */

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



    /**
     * instantiate the parser by looking at the specified
     * directory for the xml file that contains the values to be read.
     */
    public void readSetup() {

            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            try {
                SAXParser saxParser = saxParserFactory.newSAXParser();
                SetupParser.SetupReader handler = new SetupParser.SetupReader();
                String dir = System.getProperty("user.dir");
                try {
                    saxParser.parse(new File(dir + "/src/main/resources/setup.xml"), handler);
                } catch (FileNotFoundException e) {
                    saxParser.parse(new File(dir + "/setup.xml"), handler);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }

        }

    /**
     *
     * @return ip address read from file.
     */

    public String getIp () {
            return ip;
        }

    /**
     *
     * @return the port read from file
     */
    public int getPort() {
            return port;
        }

    /**
     *
      * @return maximum time available to connect
     */

    public int getCountdownConnection() {return countdownConnection;}

    /**
     *
     * @return maximum time available to make a move
     */
    public int getCountdownMove() {return countdownMove;}
}
