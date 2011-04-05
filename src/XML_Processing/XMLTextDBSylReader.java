/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package XML_Processing;

import XML_Processing.Syllable;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author thaodv_bkit
 */
public class XMLTextDBSylReader extends XML_Reader {

    private XMLStreamReader xMLStreamReader;
    private ArrayList<Sentence> allSentencesInText = new ArrayList<Sentence>();
    Sentence sentence;
    int fileID;
    String fileName;
    Syllable syllable;

    public XMLTextDBSylReader(String str) throws XMLStreamException, FileNotFoundException {
        super(str);
        setxMLStreamReader();
        xMLStreamReader = getxMLStreamReader();


    }
    /////////////////////////////////////

    public void ReadDetails() {
        try {
            while (xMLStreamReader.hasNext()) {
                Integer eventType = xMLStreamReader.next();
//                if (eventType.equals(XMLEvent.START_ELEMENT)) {
//                    System.out.println("START_ELEMENT " + xMLStreamReader.getName() + " " + xMLStreamReader.getAttributeCount());
//                } else if (eventType.equals(XMLEvent.CHARACTERS)) {
//                    System.out.println("CHARACTERS " + xMLStreamReader.getText() + " ");
//                    //ko thay in ra gi
                if (eventType.equals(XMLEvent.START_ELEMENT)) {
                    // neu eventType =  START_ELEMENT thi kiem tra, neu Name = File thi ReadFile,
                    //      neu Name = root thi bo qua, neu Name = Syllable thi ReadSyl
                    // neu eventType = CHARACTERS thi bo qua
                    // neu eventType = END_ELEMENT thi kiem tra, neu Name = Syllable thi bo qua
                    //      neu Name = File thi ket thuc file
                    //      neu Name = sentence thi ket thuc cau
                    if (xMLStreamReader.getName().toString().compareTo("File") == 0) {
                        ReadFileDetails();
                    } else if (xMLStreamReader.getName().toString().compareTo("sentence") == 0) {
                        ReadSentenceDetails();
                    } else if (xMLStreamReader.getName().toString().compareTo("Syllable") == 0) {
                        ReadSylDetails();
                    } else if (xMLStreamReader.getName().toString().compareTo("root") == 0) {
                        continue;
                    } else {
                        System.out.println("DON'T KNOW THIS START_ELEMENT");
                    }
                } else if (eventType.equals(XMLEvent.END_ELEMENT)) {
                    if (xMLStreamReader.getName().toString().compareTo("sentence") == 0) {
                        getAllSentencesInText().add(sentence);
                    }
                } else if (eventType.equals(XMLEvent.CHARACTERS)) {
                    continue;
                } else if (eventType.equals(XMLEvent.END_DOCUMENT)) {
                    continue;
                } else {
                    System.out.println(eventType);
                    System.out.println("DON'T KNOW THIS EVENT");
                }

            }
        } catch (XMLStreamException ex) {
            Logger.getLogger(XMLTextDBSylReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            xMLStreamReader.close();
        } catch (XMLStreamException ex) {
            Logger.getLogger(XMLTextDBSylReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        ////////////////////////////////////////////////////////////////////////
        // in ra ket qua
        for (int i = 0; i < allSentencesInText.size(); i++) {
             int sizeOfSen = allSentencesInText.get(i).getSyllablesInSen().size();
            for (int j = 1; j < sizeOfSen; j++) {
                System.out.println(allSentencesInText.get(i).getSyllablesInSen().get(j).getSylName());
            }
        }
    }
    ////////////////////////////////////////////////////////////////////////////

    public void ReadFileDetails() {
        fileID = StrToInt((xMLStreamReader.getAttributeValue(0)));
        fileName = xMLStreamReader.getAttributeValue(1);
        System.out.println(fileID + " " + fileName);

    }
    ////////////////////////////////////////////////////////////////////////////

    public void ReadSentenceDetails() {
        sentence = new Sentence();
        sentence.setCarryingFile(fileName);
        sentence.setIDofSentence(StrToInt(xMLStreamReader.getAttributeValue(0)));
    }
    ////////////////////////////////////////////////////////////////////////////

    public void ReadSylDetails() {
        syllable = new Syllable();
        syllable.setIDofSyllable(StrToInt(xMLStreamReader.getAttributeValue(0)));
        syllable.setSylName(xMLStreamReader.getAttributeValue(1));
        syllable.setStartIndex(StrToInt(xMLStreamReader.getAttributeValue(2)));
        syllable.setEndIndex(StrToInt(xMLStreamReader.getAttributeValue(3)));
        sentence.addSyllable(syllable);
    }
    ////////////////////////////////////////////////////////////////////////////

    public int StrToInt(String str) {
        return Integer.parseInt(str);
    }
    ////////////////////////////////////////////////////////////////////////////

    public static void main(String[] args) {
        try {
            XMLTextDBSylReader xMLTextDBSylReader = new XMLTextDBSylReader("Text_DB_Syllable.xml");
            xMLTextDBSylReader.ReadDetails();
        } catch (XMLStreamException ex) {
            Logger.getLogger(XMLTextDBSylReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(XMLTextDBSylReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the allSentencesInText
     */
    public ArrayList<Sentence> getAllSentencesInText() {
        return allSentencesInText;
    }
}
