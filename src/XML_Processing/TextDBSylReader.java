/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package XML_Processing;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author thaodv_bkit
 */
public class TextDBSylReader extends XML_Reader {

    private XMLStreamReader xMLStreamReader;

    public TextDBSylReader(String str) throws XMLStreamException, FileNotFoundException {
        super(str);
        xMLStreamReader = getxMLStreamReader();
    }
    /////////////////////////////////////

    public void ReadDetails() {
        try {
            while (xMLStreamReader.hasNext()) {
                Integer eventType = xMLStreamReader.next();
                if (eventType.equals(XMLEvent.START_ELEMENT)) {
                    System.out.println("START_ELEMENT " + xMLStreamReader.getName() + " " + xMLStreamReader.getAttributeValue(0));
                } else if (eventType.equals(XMLEvent.CHARACTERS)) {
                    System.out.println("CHARACTERS " + xMLStreamReader.getText() + " ");
                    //ko thay in ra gi
                } else if (eventType.equals(XMLEvent.ATTRIBUTE)) {
                    System.out.println("ATTRIBUTE " + xMLStreamReader.getName() + " ");
                    // ko thay in ra
                } else if (eventType.equals(XMLEvent.END_ELEMENT)) {
                    System.out.println("END_ELEMENT " + xMLStreamReader.getName() + " ");
                }
            }
        } catch (XMLStreamException ex) {
            Logger.getLogger(TextDBSylReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            xMLStreamReader.close();
        } catch (XMLStreamException ex) {
            Logger.getLogger(TextDBSylReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
