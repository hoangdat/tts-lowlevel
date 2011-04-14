/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package XML_Processing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 *
 * @author thaodv-bkit
 */
public abstract class XML_Reader {

    XMLInputFactory xMLInputFactory;
    private XMLStreamReader xMLStreamReader;
    FileInputStream fileInputStream;
    
    ////////////////////////////////////////////////////////////////////////////

    public XML_Reader(File inputFile) throws XMLStreamException, FileNotFoundException {
        fileInputStream = new FileInputStream(inputFile);
    }
    ////////////////////////////////////////////////////////////////////////////

    public XML_Reader(String str) throws XMLStreamException, FileNotFoundException {
        fileInputStream = new FileInputStream(new File(str));
    }
    ////////////////////////////////////////////////////////////////////////////
//        System.out.println(xMLStreamReader.CHARACTERS);//4
//        System.out.println(xMLStreamReader.COMMENT);//5
//        System.out.println(xMLStreamReader.END_DOCUMENT);//8
//        System.out.println(xMLStreamReader.END_ELEMENT);//2
//        System.out.println(xMLStreamReader.START_DOCUMENT);//7
//        System.out.println(xMLStreamReader.START_ELEMENT);//1
//        System.out.println(xMLStreamReader.NAMESPACE);//13

    // Doc cac thong tin chi tiet
    public abstract void ReadDetails();

    /**
     * @return the xMLStreamReader
     */
    public XMLStreamReader getxMLStreamReader() {
        return xMLStreamReader;
    }

    /**
     * 
     */
    public void setxMLStreamReader() {
        xMLInputFactory = XMLInputFactory.newInstance();
        try {
            xMLStreamReader = xMLInputFactory.createXMLStreamReader(fileInputStream);
        } catch (XMLStreamException ex) {
            Logger.getLogger(XML_Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
