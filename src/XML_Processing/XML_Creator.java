/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package XML_Processing;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 *
 * @author thaodv-bkit
 */
public class XML_Creator {

    String fileName = "Text_DB.xml";
    XMLOutputFactory xof;
    XMLStreamWriter xmlStrWr;
    FileWriter fileWriter;

    public XMLStreamWriter getXtw() {
        return xmlStrWr;
    }

    public XML_Creator() throws XMLStreamException {

        xof = XMLOutputFactory.newInstance();
        xmlStrWr = null;
        try {
            fileWriter = new FileWriter(fileName);
            xmlStrWr = xof.createXMLStreamWriter(fileWriter);
        } catch (IOException ex) {
            Logger.getLogger(XML_Creator.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        xmlStrWr.flush();
        xmlStrWr.close();
    }
    /////////

    public void WriteXML(String str) throws XMLStreamException {
        StringTokenizer strTk = new StringTokenizer(str.trim());
        ArrayList<String> tokenList = new ArrayList();
        for (int i = 0; i < strTk.countTokens(); i++) {
            //System.out.println(strTk.nextToken());
            tokenList.add(strTk.nextToken());
        }
        System.out.println(strTk.countTokens());
        int i = 0;
        while (i < tokenList.size()) {
        }

    }
    /////

    public void WriteXML(ArrayList<String> listLines, File file, int fileIndex) throws XMLStreamException {
        xmlStrWr = xof.createXMLStreamWriter(fileWriter);
        {
            xmlStrWr.writeStartElement("File");
            xmlStrWr.writeAttribute("Id", Integer.toString(fileIndex));
            xmlStrWr.writeAttribute("File_Name",file.getName());

            for (int i = 0; i < listLines.size(); i++) {
                StringTokenizer strTk = new StringTokenizer(listLines.get(i));
                {

                    ////////////////////////////////////////
                    {
                        xmlStrWr.writeStartElement("Syllable");
                        ////////////////////////////////////////
                        {
                            xmlStrWr.writeStartElement("Syllable_Name");
                            xmlStrWr.writeCharacters(strTk.nextToken());
                            xmlStrWr.writeEndElement();
                        }
                        ///////////////////////////////////////
                        {

                            xmlStrWr.writeStartElement("Start_Index");
                            xmlStrWr.writeCharacters(strTk.nextToken());
                            xmlStrWr.writeEndElement();
                        }
                        ///////////////////////////////////////
                        {
                            xmlStrWr.writeStartElement("End_Index");
                            xmlStrWr.writeCharacters(strTk.nextToken());
                            xmlStrWr.writeEndElement();
                        }
                        ///////////////////////////////////////
                        xmlStrWr.writeEndElement();
                    }
                    //////////////////

                }


            }
            xmlStrWr.writeEndElement();
            
        }
        xmlStrWr.flush();
        xmlStrWr.close();
    }
}
