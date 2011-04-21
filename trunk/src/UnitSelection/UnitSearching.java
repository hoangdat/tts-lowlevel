/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UnitSelection;

import XML_Processing.LevelPhrase;
import XML_Processing.Sentence;
import XML_Processing.SylPhrase;
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
        textInputReader = new TextInputReader(System.getProperty("user.dir") + "\\result4.xml");
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
        ArrayList<Integer> indexesOfLevelPhrase;
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
        String phContentToSearch = (" " + levelPhrase.getPhraseContent().trim() + " ");
        int indexFound = 0;
        int sylID;
        for (int i = 0; i < allSenInTextDB.size(); i++) {
            ArrayList<SylPhrase> sylPhrasesInSen = allSenInTextDB.get(i).getSylPhrases();
            for (int j = 0; j < sylPhrasesInSen.size(); j++) {
                // tim kiem phContentToSearch trong SylPhraseContent
                // khi tim kiem lan dau thay
                // va subString con lai co length > phContentToSearch.length
                // thi tiep tuc tim kiem trong subString con lai
                String phrsContent = sylPhrasesInSen.get(j).getPhraseContent();
                indexFound = phrsContent.indexOf(phContentToSearch);
                if (indexFound >= 0) {
                    sylID = sylPhrasesInSen.get(j).getSylIDbyIndexOfSpace(indexFound);
                    //System.out.println("tim thay: " + phContentToSearch + ": tai syllable co ID =: " + sylID+ " :tai phrase thu: "+j+": cua cau thu: " + i);
                    System.out.println(phContentToSearch + " : " + sylID);
                    phrsContent = " " + phrsContent.substring(indexFound + phContentToSearch.length()).trim() + " ";
                }
                while (indexFound >= 0 && phrsContent.length() >= phContentToSearch.length()) {
                    indexFound = phrsContent.indexOf(phContentToSearch);
                    if (indexFound == -1) {
                        break;
                    }
                    if (indexFound >= 0) {
                        sylID = sylPhrasesInSen.get(j).getSylIDbyIndexOfSpace(indexFound);
                        //System.out.println("tim thay: " + phContentToSearch + ": tai syllable co ID =: " + sylID+ " :tai phrase thu: "+j+": cua cau thu: " + i);
                        System.out.println(phContentToSearch + " : " + sylID);
                        phrsContent = " " + phrsContent.substring(indexFound + phContentToSearch.length()).trim() + " ";
                    }
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
