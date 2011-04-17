/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package XML_Processing;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.util.Stack;

/**
 *
 * @author thaodv_bkit
 */
public class TextInputReader extends XML_Reader {

    private XMLStreamReader xMLStreamReader;
    private String nameOfElement;
    private Sentence sentence;
    private Stack<String> stack;
    private LevelPhrase levelPhrase;
    private ArrayList<Integer> level;

    public TextInputReader(String str) throws XMLStreamException, FileNotFoundException {
        super(str);
        setxMLStreamReader();
        xMLStreamReader = getxMLStreamReader();
        stack = new Stack<String>();
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
                    //neu element la "sentence" thi tao moi 1 cau, getSentenceContent
                    //neu element la "parse" thi ko lam j ca
                    //neu element la cac phrase thi doc phrase
                    nameOfElement = xMLStreamReader.getName().toString();
                    stack.add(nameOfElement);
                    if (nameOfElement.compareTo("sentence") == 0) {
                        ReadSentenceDetails();
                    } else if (nameOfElement.compareTo("parse") == 0) {
                        ReadParseDetails();
                    } else if (nameOfElement.compareTo("root") == 0) {
                    } else {
                        ReadPhraseDetails();
                    }

                } else if (eventType.equals(XMLEvent.CHARACTERS)) {
                } else if (eventType.equals(XMLEvent.END_ELEMENT)) {
                    // so sanh
                    nameOfElement = xMLStreamReader.getName().toString();
                    if (nameOfElement.compareTo(stack.peek()) == 0) {
                        stack.pop();
                    } else {
                        System.out.println("tai sao vay?");
                    }
                    //////////
                    if (nameOfElement.compareTo("sentence") == 0) {
                        this.getAllSentences().add(sentence);
                        this.setLevelPhrase();
                    }
                } else {
                    System.out.println("Don't know this event");
                }
            }
        } catch (XMLStreamException ex) {
            Logger.getLogger(TextInputReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    ///////////

    public void ReadSentenceDetails() {

        sentence = new Sentence();
        level = new ArrayList<Integer>();
        sentence.setiDofFile(StrToInt(xMLStreamReader.getAttributeValue(0)));
        try {
            xMLStreamReader.next();
            sentence.setSenContent(xMLStreamReader.getText());
        } catch (XMLStreamException ex) {
            Logger.getLogger(TextInputReader.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
    /////////////

    public void ReadParseDetails() {
        xMLStreamReader.getAttributeValue(0);
    }
    ////////////

    public void ReadPhraseDetails() {

        levelPhrase = new LevelPhrase();
        int lv = StrToInt(xMLStreamReader.getAttributeValue(0));
        levelPhrase.setLevel(lv);
        try {
            xMLStreamReader.next();
            levelPhrase.setContent(xMLStreamReader.getText());
        } catch (XMLStreamException ex) {
            Logger.getLogger(TextInputReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        sentence.getSylPhrases().add(levelPhrase);
        level.add(lv);
    }
    //////////////

    private void setLevelPhrase() {
        System.out.println(level.listIterator());
        int initialLevel = 1;
        int currentLevel = initialLevel;
        int nextLevel;
        for (int i = 0; i < level.size(); i++) {
            nextLevel = (int)level.get(i);
           if(nextLevel>currentLevel){
               System.out.println(i+ " is child of: "+ currentLevel);
               currentLevel = i;
           }

        }
    }
    ////////////////////////////////

    public static void main(String[] args) throws XMLStreamException, FileNotFoundException {
        String textDBLocation = System.getProperty("user.dir") + "\\result.xml";
        TextInputReader textInputReader = new TextInputReader(textDBLocation);
        textInputReader.ReadDetails();

    }
}