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
 * @author thaodv-bkit
 */
public class TextDBReader extends XML_Reader {

    private XMLStreamReader xMLStreamReader;
    private Sentence sentence;
    private SylPhrase phrase;
    private Syllable syllable;
    private int fileId;
    private String fileName;

    public TextDBReader(String textDBLocation) throws XMLStreamException, FileNotFoundException {
        super(textDBLocation);
        setxMLStreamReader();
        xMLStreamReader = getxMLStreamReader();
    }

    @Override
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
                    if (xMLStreamReader.getName().toString().compareTo("file") == 0) {
                        ReadFileDetails();
                    } else if (xMLStreamReader.getName().toString().compareTo("sentence") == 0) {
                        ReadSentenceDetails();
                    } else if (xMLStreamReader.getName().toString().compareTo("phrase") == 0) {
                        ReadPhraseDetails();
                    } else if (xMLStreamReader.getName().toString().compareTo("syllable") == 0) {
                        ReadSylDetails();
                    } else if (xMLStreamReader.getName().toString().compareTo("root") == 0) {
                        continue;
                    } else {
                        System.out.println("DON'T KNOW THIS START_ELEMENT");
                    }
                } else if (eventType.equals(XMLEvent.END_ELEMENT)) {
                    if (xMLStreamReader.getName().toString().compareTo("sentence") == 0) {
                        getAllSentences().add(sentence);
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
      
    }

    private void ReadFileDetails() {
        fileId = StrToInt((xMLStreamReader.getAttributeValue(0)));
        fileName = xMLStreamReader.getAttributeValue(1);
    }

    private void ReadSentenceDetails() {
        sentence = new Sentence();
        sentence.setIDofSentence(StrToInt((xMLStreamReader.getAttributeValue(0))));
        sentence.setiDofFile(fileId);
        sentence.setCarryingFile(fileName);
        this.getAllSentences().add(sentence);
    }

    private void ReadPhraseDetails() {
        phrase = new SylPhrase();
        phrase.setId_phrase(StrToInt(xMLStreamReader.getAttributeValue(0)));
        phrase.setPhraseLen(StrToInt(xMLStreamReader.getAttributeValue(1)));
        sentence.getSylPhrases().add(phrase);
    }

    private void ReadSylDetails() {
        //id_syl="0" name="SILP" start_index="0" end_index="2850" end_index="0" end_index="0" end_index="6944.88"
        syllable = new Syllable();
        syllable.setIDofSyllable(StrToInt(xMLStreamReader.getAttributeValue(0)));
        syllable.setSylName(xMLStreamReader.getAttributeValue(1));
        syllable.setStartIndex(StrToInt(xMLStreamReader.getAttributeValue(2)));
        syllable.setEndIndex(StrToInt(xMLStreamReader.getAttributeValue(3)));
        syllable.set
        
    }

    public static void main(String[] args) throws XMLStreamException, FileNotFoundException {
        
        String textDBLocation = System.getProperty("user.dir") + "\\Text_DB_Creator.xml";
        TextDBReader textDBReader = new TextDBReader(textDBLocation);
    }
}
