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
import java.util.Stack;

/**
 *
 * @author thaodv-bkit
 */
public class UnitSearching {

    /**
     * @return the allSenInTextDB
     */
    public static ArrayList<Sentence> getAllSenInTextDB() {
        return allSenInTextDB;
    }

    TextInputReader textInputReader;
    TextDBReader textDBReader;
    private static ArrayList<Sentence> allSenInTextDB;
    ArrayList<Sentence> allSenInTextInput;
    Stack<Integer> indexesOfLP;
    private ArrayList<LevelPhrase> foundLPhs = new ArrayList<LevelPhrase>();

    public UnitSearching() throws XMLStreamException, FileNotFoundException {
        textDBReader = new TextDBReader(System.getProperty("user.dir") + "\\Text_DB_Creator.xml");
        textInputReader = new TextInputReader(System.getProperty("user.dir") + "\\result4.xml");
        allSenInTextDB = textDBReader.getAllSentences();
        allSenInTextInput = textInputReader.getAllSentences();
        textInputReader.printDetails();
        this.searchTextInput();
        this.setPosInSenOfPhrs();
    }
    //

    public void searchTextInput() {
        for (int i = 0; i < allSenInTextInput.size(); i++) {
            System.out.println(i);
            //searchSentence(allSenInTextInput.get(i));
            //searchSentence(allSenInTextInput.get(i));
            searchSentenceByLP(allSenInTextInput.get(i));
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
    public void searchSentenceByLP(Sentence s) {
        // lay chi so cua cac LevelPhrase co level = 1
        // tim kiem bat dau tu LevelPhrase co chi so la phan tu dau tien trong indexes
        // doi voi LevelPhrase ko tim thay thi kiem tra LP do co subLevel hay ko?
        // neu LP do co subLevel thi thay LP do = cac SubLevelPhrase cua LP do neu co subLP
        // neu LP do ko co subLevel thi chuyen sang LP tiep theo
        // remove gia tri trong indexes cua LP do
        // add chi so cua SubLP cua LP do vao stack indexes tai vi tri cua LP do
        // tim kiem ket thuc khi indexesOfLP empty
        indexesOfLP = new Stack<Integer>();        
        int topOfStack;
        boolean isFoundLevelPhrase;
        ArrayList<Integer> indexesOfLevelPhrase = s.getIndexesOfPhraseByLevel(1);
        //// chuyen indexesOfLevelPhrase tu Array sang Stack
        addIndexesOfLPtoStack(indexesOfLevelPhrase);
        // in ra indexes ban dau
        System.out.println("indexesOfLP : "+indexesOfLP.toString());
        // bat dau tim kiem LP
        while (!indexesOfLP.isEmpty()) {
            topOfStack = (int) indexesOfLP.pop();
            LevelPhrase topLP = s.getLevelPhrases().get(topOfStack);
            isFoundLevelPhrase = this.searchLevelPhrase(topLP);
            if (!isFoundLevelPhrase) {
                //neu ko tim thay LP
                if(topLP.haveSubLevel()){
                    System.out.println("IndexesOfSubLevel of Top "+topLP.getIndexesOfSubLevel().toString());
                    addIndexesOfLPtoStack(topLP.getIndexesOfSubLevel());
                    System.out.println("indexesOfLP : "+indexesOfLP.toString());
                    System.out.println("ko thay: " + topLP.getPhraseContent()+" :va da add subLevel de tim kiem");
                }else{
                    //neu ko tim thay LP va LP ko co subLevel de tim kiem
                    topLP.setIsFound(false);
                    getFoundLPhs().add(topLP);
                    System.out.println("ko thay: " + topLP.getPhraseContent()+" :va LP nay ko co subLevel");
                }
            } else{
                //neu tim thay LP
                topLP.setIsFound(true);
                getFoundLPhs().add(topLP);
            }
        }
        System.out.println(getFoundLPhs().size());
    }

    /*
     *
     */
    public void addIndexesOfLPtoStack(ArrayList<Integer> indexesOfLevelPhrase){        
        for (int i = indexesOfLevelPhrase.size() - 1; i >= 0; i--) {
            indexesOfLP.push((int) indexesOfLevelPhrase.get(i));
        }
    }

    /*
     *
     */
//    public void searchSen(Sentence s) {
//        boolean isFoundLevelPhrase;
//        for (int j = 1; j <= s.getMaxLevelOfLevelPhrase(); j++) {
//            ArrayList<Integer> indexesOfLevelPhrase = s.getIndexesOfPhraseByLevel(j);
//            int sizeOfIndexes = indexesOfLevelPhrase.size();
//            for (int i = 0; i < sizeOfIndexes; i++) {
//                isFoundLevelPhrase = this.searchLevelPhrase(s.getLevelPhrases().get((int) indexesOfLevelPhrase.get(i)));
//                if (!isFoundLevelPhrase) {
//                    System.out.println("ko thay: " + s.getLevelPhrases().get((int) indexesOfLevelPhrase.get(i)).getPhraseContent());
//                }
//            }
//        }
//    }

    /*
     *
     */
    public boolean searchLevelPhrase(LevelPhrase levelPhrase) {
        String phContentToSearch = (" " + levelPhrase.getPhraseContent().trim() + " ");
        int indexFound = 0;
        boolean isFound = false;
        int sylID;
        for (int i = 0; i < getAllSenInTextDB().size(); i++) {
            ArrayList<SylPhrase> sylPhrasesInSen = getAllSenInTextDB().get(i).getSylPhrases();
            for (int j = 0; j < sylPhrasesInSen.size(); j++) {
                // tim kiem phContentToSearch trong SylPhraseContent
                // khi tim kiem lan dau thay
                // va subString con lai co length > phContentToSearch.length
                // thi tiep tuc tim kiem trong subString con lai
                String phrsContent = sylPhrasesInSen.get(j).getPhraseContent();
                indexFound = phrsContent.indexOf(phContentToSearch);

//                if (indexFound >= 0) {
//                    isFound = true;
//                    sylID = sylPhrasesInSen.get(j).getSylIDbyIndexOfSpace(indexFound);
//                    System.out.println("tim thay: " + phContentToSearch + ": tai syllable co ID =: " + sylID+ " :tai phrase thu: "+j+": cua cau thu: " + i);
//                    System.out.println(phContentToSearch + " - " + sylID);
//                    phrsContent = " " + phrsContent.substring(indexFound + phContentToSearch.length()).trim() + " ";
//                }
                while (indexFound >= 0 && phrsContent.length() >= phContentToSearch.length()) {
                    indexFound = phrsContent.indexOf(phContentToSearch);
                    if (indexFound == -1) {
                        break;
                    }else
                    if (indexFound >= 0) {
                        isFound = true;
                        sylID = sylPhrasesInSen.get(j).getSylIDbyIndexOfSpace(indexFound);
                        levelPhrase.getFoundSen().add(i);
                        levelPhrase.getFoundSylPhrs().add(j);
                        levelPhrase.getFoundSyllable().add(sylID);
                        //System.out.println("tim thay: " + phContentToSearch + ": tai syllable co ID =: " + sylID+ " :tai phrase thu: "+j+": cua cau thu: " + i);
                        System.out.println(phContentToSearch + " : " + sylID);
                        phrsContent = " " + phrsContent.substring(indexFound + phContentToSearch.length()).trim() + " ";
                         if(levelPhrase.getFirstSylInLPhrs()==null){
                             levelPhrase.setFirstSylInLPhrs(sylPhrasesInSen.get(j).getSyllablesInPh().get(sylID));
                             levelPhrase.setLastSylInLPhrs(sylPhrasesInSen.get(j).getSyllablesInPh().get(sylID+levelPhrase.getPhraseLen()-1));
                         }
                    }
                }
            }
        }
        return isFound;
    }

    //
    ////
    public static void main(String[] args) throws XMLStreamException, FileNotFoundException {
        UnitSearching unitSearching = new UnitSearching();        
        System.out.println("keke");
    }

    /**
     * @return the foundLPhs
     */
    public ArrayList<LevelPhrase> getFoundLPhs() {
        return foundLPhs;
    }

    private void setPosInSenOfPhrs() {
        int posToSet = 0;
        for (int i = 0; i < getFoundLPhs().size(); i++) {
            String phraseContent = getFoundLPhs().get(i).getPhraseContent();
            if((phraseContent.compareTo("SIL")==0)||(phraseContent.compareTo("SILS")==0)){
                posToSet = 0;
            } else{
                getFoundLPhs().get(i).setPosInSen(posToSet);
                posToSet += getFoundLPhs().get(i).getPhraseLen();
            }
        }
        
    }
}
