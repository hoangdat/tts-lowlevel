/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package XML_Processing;

/**
 *
 * @author thaodv-bkit
 */
import java.io.FileWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

public class CursorWriter {

    public static void main(String[] args) throws Exception {
        String fileName = "yourXML.xml";
        XMLOutputFactory xof = XMLOutputFactory.newInstance();
        XMLStreamWriter xtw = null;
        xtw = xof.createXMLStreamWriter(new FileWriter(fileName));
        xtw.writeComment("all elements here are explicitly in the HTML namespace");
        xtw.writeStartDocument("utf-8", "1.0");
        xtw.setPrefix("html", "http://www.w3.org/TR/REC-html40");
        xtw.writeStartElement("http://www.w3.org/TR/REC-html40", "html");
        xtw.writeNamespace("html", "http://www.w3.org/TR/REC-html40");
        xtw.writeStartElement("http://www.w3.org/TR/REC-html40", "head");
        xtw.writeStartElement("http://www.w3.org/TR/REC-html40", "title");
        xtw.writeCharacters("character");
        xtw.writeEndElement();
        xtw.writeEndElement();
        xtw.writeStartElement("http://www.w3.org/TR/REC-html40", "body");
        xtw.writeStartElement("http://www.w3.org/TR/REC-html40", "p");
        xtw.writeCharacters("another character");
        xtw.writeStartElement("http://www.w3.org/TR/REC-html40", "a");
        xtw.writeAttribute("href", "http://www.java2s.com");
        xtw.writeCharacters("here");
        xtw.writeEndElement();
        xtw.writeEndElement();
        xtw.writeEndElement();
        xtw.writeEndElement();
        xtw.writeEndDocument();
        xtw.flush();
        xtw.close();
        System.out.println("Done");
    }
}
