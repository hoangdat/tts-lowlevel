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
    private String nameOfElement;

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
                    nameOfElement = xMLStreamReader.getName().toString();
                    if (nameOfElement.compareTo("file") == 0) {
                        ReadFileDetails();
                    } else if (nameOfElement.compareTo("sentence") == 0) {
                        ReadSentenceDetails();
                    } else if (nameOfElement.compareTo("phrase") == 0) {
                        ReadPhraseDetails();
                    } else if (nameOfElement.compareTo("syllable") == 0) {
                        ReadSylDetails();
                    } else if (nameOfElement.compareTo("initial") == 0) {
                        syllable.setInitialType(xMLStreamReader.getAttributeValue(0));
                        syllable.setIntialPhoneme(xMLStreamReader.getElementText());
                    } else if (nameOfElement.compareTo("middle") == 0) {
                        syllable.setMiddleType(xMLStreamReader.getAttributeValue(0));
                        syllable.setMiddlePhoneme(xMLStreamReader.getElementText());
                    } else if (nameOfElement.compareTo("nucleus") == 0) {
                        syllable.setNucleusType(xMLStreamReader.getAttributeValue(0));
                        syllable.setNucleusPhoneme(xMLStreamReader.getElementText());
                    } else if (nameOfElement.compareTo("final") == 0) {
                        syllable.setFinalType(xMLStreamReader.getAttributeValue(0));
                        syllable.setFinalPhoneme(xMLStreamReader.getElementText());
                    } else if (nameOfElement.compareTo("tone") == 0) {
                        syllable.setSylTone(StrToInt(xMLStreamReader.getElementText()));
                    } else if (nameOfElement.compareTo("syllable") == 0) {
                        ReadSylDetails();
                    } else if (nameOfElement.compareTo("root") == 0) {
                        continue;
                    } else {
                        System.out.println("DON'T KNOW THIS START_ELEMENT");
                    }
                } else if (eventType.equals(XMLEvent.END_ELEMENT)) {
                    nameOfElement = xMLStreamReader.getName().toString();
                    if (nameOfElement.compareTo("syllable") == 0) {
                        phrase.getSyllablesInPh().add(syllable);
                    } else if (nameOfElement.compareTo("phrase") == 0) {
                        sentence.getSylPhrases().add(phrase);
                    } else if (nameOfElement.compareTo("sentence") == 0) {
                        getAllSentences().add(sentence);
                    }
                } else if (eventType.equals(XMLEvent.CHARACTERS)) {
                    System.out.println("Characters");
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
        //System.out.println("so luong cau: " + this.getAllSentences().size());
    }

    private void ReadFileDetails() {
        fileId = StrToInt((xMLStreamReader.getAttributeValue(0)));
        setFileName(xMLStreamReader.getAttributeValue(1));
    }

    private void ReadSentenceDetails() {
        sentence = new Sentence();
        sentence.setIDofSentence(StrToInt((xMLStreamReader.getAttributeValue(0))));
        sentence.setiDofFile(fileId);
        sentence.setCarryingFile(getFileName());


    }

    private void ReadPhraseDetails() {
        phrase = new SylPhrase();
        phrase.setId_phrase(StrToInt(xMLStreamReader.getAttributeValue(0)));
        phrase.setPhraseLen(StrToInt(xMLStreamReader.getAttributeValue(1)));

    }

    private void ReadSylDetails() {
        //id_syl="1" name="mặt" start_index = "0" end_index="2850" position = "1" num_of_phone = "3" energy="1023.4">
        syllable = new Syllable();
        syllable.setIDofSyllable(StrToInt(xMLStreamReader.getAttributeValue(0)));
        syllable.setSylName(xMLStreamReader.getAttributeValue(1));
        syllable.setStartIndex(StrToInt(xMLStreamReader.getAttributeValue(2)));
        syllable.setEndIndex(StrToInt(xMLStreamReader.getAttributeValue(3)));
        //syllable.setSylPosition(StrToInt(xMLStreamReader.getAttributeValue(4)));
        syllable.setNumOfPhone(StrToInt(xMLStreamReader.getAttributeValue(4)));
        syllable.setEnergy(Float.parseFloat(xMLStreamReader.getAttributeValue(5)));
    }

    public static void main(String[] args) throws XMLStreamException, FileNotFoundException {

        String textDBLocation = System.getProperty("user.dir") + "\\Text_DB_Creator.xml";
        TextDBReader textDBReader = new TextDBReader(textDBLocation);
        textDBReader.ReadDetails();
        System.out.println(textDBReader.getFileName());

    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
