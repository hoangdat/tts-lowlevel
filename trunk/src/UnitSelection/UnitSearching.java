/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UnitSelection;

import XML_Processing.LevelPhrase;
import XML_Processing.Sentence;
import XML_Processing.TextDBReader;
import XML_Processing.TextInputReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author thaodv-bkit
 */
public class UnitSearching {

    TextInputReader textInputReader;
    TextDBReader textDBReader;
    ArrayList<Sentence> allSenInTextDB;
    ArrayList<Sentence> allSenInTextInput;

    public UnitSearching() throws XMLStreamException, FileNotFoundException {
        textDBReader = new TextDBReader(System.getProperty("user.dir") + "\\Text_DB_Creator.xml");
        textInputReader = new TextInputReader(System.getProperty("user.dir") + "\\result2.xml");
        allSenInTextDB = textDBReader.getAllSentences();
        allSenInTextInput = textInputReader.getAllSentences();
        textInputReader.printDetails();
        this.searchTextInput();
    }
    //

    public void searchTextInput() {
        for (int i = 0; i < allSenInTextInput.size(); i++) {
            System.out.println(i);
            searchSentence(allSenInTextInput.get(i));
        }
    }

    public void searchSentence(Sentence s) {
        for (int j = 1; j <= s.getMaxLevelOfLevelPhrase(); j++) {
            ArrayList<LevelPhrase> lp = s.getPhraseByLevel(j);
            int sizeOfPhrase = lp.size();
            for (int i = 0; i < sizeOfPhrase; i++) {
                this.searchLevelPhrase(lp.get(i));
            }
        }

    }

    /*
     *
     */
    public void searchLevelPhrase(LevelPhrase levelPhrase) {
        String phraseContent = levelPhrase.getPhraseContent();
        int indexFound;
        for (int i = 0; i < allSenInTextDB.size(); i++) {
            for (int j = 0; j < allSenInTextDB.get(i).getSylPhrases().size(); j++) {
                indexFound = allSenInTextDB.get(i).getSylPhrases().get(j).getPhraseContent().indexOf(phraseContent);
                if (indexFound > 0) {
                    System.out.println("tim thay: " + phraseContent + ": tai vi tri thu: " + indexFound + ": cua cau thu: " + i);
                }
            }
        }
    }

    //
    ////
    public static void main(String[] args) throws XMLStreamException, FileNotFoundException {
        UnitSearching unitSearching = new UnitSearching();
    }
}
