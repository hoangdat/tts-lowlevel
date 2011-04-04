/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package XML_Processing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 *
 * @author thaodv-bkit
 */
public class XML_Reader {
    XMLInputFactory xMLInputFactory;
    XMLStreamReader xMLStreamReader;

    public XML_Reader(File inputFile) throws XMLStreamException, FileNotFoundException{
        xMLInputFactory = XMLInputFactory.newInstance();
        xMLStreamReader = xMLInputFactory.createXMLStreamReader(new FileInputStream(inputFile));
        

    }
}
