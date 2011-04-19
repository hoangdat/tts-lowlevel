/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package UnitSelection;

import XML_Processing.TextDBReader;
import XML_Processing.TextInputReader;
import java.io.FileNotFoundException;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author thaodv-bkit
 */
public class UnitSearching {
    TextInputReader textInputReader;
    TextDBReader textDBReader;
    public UnitSearching() throws XMLStreamException, FileNotFoundException{
        textDBReader = new TextDBReader(System.getProperty("user.dir") + "\\Text_DB_Creator.xml");
        textInputReader = new TextInputReader(System.getProperty("user.dir") + "\\result.xml");
        
    }


}
