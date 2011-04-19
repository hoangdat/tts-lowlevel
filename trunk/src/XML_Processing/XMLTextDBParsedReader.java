/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package XML_Processing;

import java.io.FileNotFoundException;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author thaodv-bkit
 */
public class XMLTextDBParsedReader extends XML_Reader{

    public XMLTextDBParsedReader(String str) throws XMLStreamException, FileNotFoundException{
        super(str);
    }

    @Override
    public void ReadDetails() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void ReadSentenceDetails() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void ReadPhraseDetails() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
