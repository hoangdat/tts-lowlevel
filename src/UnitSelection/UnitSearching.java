/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UnitSelection;

import Units.HalfSyl;
import Units.LevelPhrase;
import Units.Sentence;
import Units.SylPhrase;
import Units.Syllable;
import XML_Processing.DelimitingSign;
import XML_Processing.HalfSylDBReader;
import XML_Processing.SyllableAnalysis;
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
    static TextInputReader textInputReader = new TextInputReader(System.getProperty("user.dir") + "\\sentest\\sen7.xml");
    //static TextInputReader textInputReader = new TextInputReader(System.getProperty("user.dir") + "\\result4.xml");
    static TextDBReader textDBReader = new TextDBReader(System.getProperty("user.dir") + "\\Text_DB_Creator.xml");
    static SyllableAnalysis sylTransDic = new SyllableAnalysis();
    private static ArrayList<Sentence> allSenInTextDB;
    ArrayList<Sentence> allSenInTextInput;
    ArrayList<Syllable> sylDic;
    ArrayList<HalfSyl> allHalfSyls;
    //static HalfSylDBReader halfSylDBReader = new HalfSylDBReader();
    Stack<Integer> indexesOfLP;
    private ArrayList<LevelPhrase> foundLPhs = new ArrayList<LevelPhrase>();

    public UnitSearching() throws XMLStreamException, FileNotFoundException {
        //textDBReader = new TextDBReader(System.getProperty("user.dir") + "\\Text_DB_Creator.xml");
        //textInputReader = new TextInputReader(System.getProperty("user.dir") + "\\result7_fixed.xml");
        allSenInTextDB = textDBReader.getAllSentences();
        allSenInTextInput = textInputReader.getAllSentences();
        sylDic = sylTransDic.getSylDic();
        textInputReader.printDetails();
        //allHalfSyls = halfSylDBReader.getAllHalfSyls();
        this.searchTextInput();
        this.setPosInSenOfPhrs();
    }
    //

    public static ArrayList<Sentence> getAllSenInTextDB() {
        return allSenInTextDB;
    }

    public void searchTextInput() {
        for (int i = 0; i < allSenInTextInput.size(); i++) {
            //System.out.println(i);
            //searchSentence(allSenInTextInput.get(i));
            //searchSentence(allSenInTextInput.get(i));
            searchSentenceByLP(allSenInTextInput.get(i));
        }
    }

//    public void searchSentence(Sentence s) {
//        for (int j = 1; j <= s.getMaxLevelOfLevelPhrase(); j++) {
//            ArrayList<LevelPhrase> lp = s.getPhraseByLevel(j);
//            int sizeOfPhrase = lp.size();
//            for (int i = 0; i < sizeOfPhrase; i++) {
//                this.searchLevelPhrase(lp.get(i));
//            }
//        }
//    }

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
        //System.out.println("indexesOfLP : "+indexesOfLP.toString());
        // bat dau tim kiem LP
        while (!indexesOfLP.isEmpty()) {
            topOfStack = (int) indexesOfLP.pop();
            LevelPhrase topLP = s.getLevelPhrases().get(topOfStack);
            isFoundLevelPhrase = this.searchLevelPhrase(topLP);
            if (!isFoundLevelPhrase) {
                //neu ko tim thay LP
                if (topLP.haveSubLevel()) {
                    //System.out.println("IndexesOfSubLevel of Top "+topLP.getIndexesOfSubLevel().toString());
                    addIndexesOfLPtoStack(topLP.getIndexesOfSubLevel());
                    //System.out.println("indexesOfLP : "+indexesOfLP.toString());
                    //System.out.println("ko thay: " + topLP.getPhraseContent()+" :va da add subLevel de tim kiem");
                } else {
                    //neu ko tim thay LP va LP ko co subLevel de tim kiem
                    //topLP.setIsFound(2);
                    //tim kiem trong tu dien am tiet va ban am tiet                    
                    if (!checkSyllableInSylDic(topLP)) {
                        System.out.println("ko thay: " + topLP.getPhraseContent() + " :va LP nay ko co subLevel");
                    } else {
                        //searchSyllableInHalfSylDB(topLP);
                    }
                    getFoundLPhs().add(topLP);
                    //System.out.println("so luong LP tim thay" + getFoundLPhs().size());
                }
            } else {
                //neu tim thay LP
                topLP.setIsFound(1);
                getFoundLPhs().add(topLP);
            }
        }
        //System.out.println(getFoundLPhs().size());
    }

    /*
     *
     */
    public void searchSyllableByHalfSyl(Syllable syl) {
    }

    /*
     *
     */
    public void addIndexesOfLPtoStack(ArrayList<Integer> indexesOfLevelPhrase) {
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
                    } else if (indexFound >= 0) {
                        if (phContentToSearch.trim().compareTo("SIL") == 0 || phContentToSearch.trim().compareTo("SILS") == 0 || phContentToSearch.trim().compareTo("SILP") == 0) {
                            if (levelPhrase.getFoundIndexes1().size() == 1) {
                                break;
                            }
                        }
                        isFound = true;
                        sylID = sylPhrasesInSen.get(j).getSylIDbyIndexOfSpace(indexFound);
                        levelPhrase.getFoundIndexes1().add(i);
                        levelPhrase.getFoundIndexes2().add(j);
                        levelPhrase.getFoundIndexes3().add(sylID);
                        levelPhrase.getFoundIndexes4().add(sylID + levelPhrase.getPhraseLen() - 1);

                        //System.out.println("tim thay: " + phContentToSearch + ": tai syllable co ID =: " + sylID+ " :tai phrase thu: "+j+": cua cau thu: " + i);
                        //System.out.println(phContentToSearch + " : " + sylID);
                        phrsContent = " " + phrsContent.substring(indexFound + phContentToSearch.length()).trim() + " ";
                        if (levelPhrase.getFirstSylInLPhrs() == null) {
                            levelPhrase.setFirstSylInLPhrs(sylPhrasesInSen.get(j).getSyllablesInPh().get(sylID));
                            levelPhrase.setLastSylInLPhrs(sylPhrasesInSen.get(j).getSyllablesInPh().get(sylID + levelPhrase.getPhraseLen() - 1));
                        }

                    }
                }
            }
        }
        return isFound;
    }

    //
    ////
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
            if ((phraseContent.compareTo("SIL") == 0) || (phraseContent.compareTo("SILS") == 0)) {
                posToSet = 0;
            } else {
                getFoundLPhs().get(i).setPosInSen(posToSet);
                posToSet += getFoundLPhs().get(i).getPhraseLen();
            }
        }
    }

    private boolean checkSyllableInSylDic(LevelPhrase topLP) {
        DelimitingSign delimittedSignLP = new DelimitingSign(topLP.getPhraseContent());
        String strResult = delimittedSignLP.getStrResult().trim();
        //topLP.getFirstSylInLPhrs().setSylTone(delimittedSignLP.getSign());
        for (int i = 0; i < sylDic.size(); i++) {
            //System.out.println("tim kiem "+topLP.getPhraseContent()+"::"+sylDic.get(i).getSylName());
            if (strResult.compareTo(sylDic.get(i).getSylName()) == 0) {
                topLP.setFirstSylInLPhrs(sylDic.get(i));
                topLP.getFirstSylInLPhrs().setSylTone(delimittedSignLP.getSign());
                //System.out.println("da tim thay trong ban am tiet");
                topLP.setIsFound(2);
                return true;
            }
        }
        topLP.setIsFound(0);
        return false;
    }

    private void searchSyllableInHalfSylDB(LevelPhrase topLP) {
        for (int i = 0; i < allHalfSyls.size(); i++) {
            if (topLP.getFirstSylInLPhrs().getLeftHalfSyl().compareTo(allHalfSyls.get(i).getHalfSylName()) == 0) {
                //System.out.println("tim thay " + topLP.getFirstSylInLPhrs().getLeftHalfSyl() + " tai vi tri " + i);
                topLP.getFoundIndexes1().add(i);
            }
            if (topLP.getFirstSylInLPhrs().getRightHalfSyl().compareTo(allHalfSyls.get(i).getHalfSylName()) == 0) {
                //System.out.println("tim thay " + topLP.getFirstSylInLPhrs().getRightHalfSyl() + " tai vi tri " + i);
                topLP.getFoundIndexes2().add(i);
            }
        }
    }

    public static void main(String[] args) throws XMLStreamException, FileNotFoundException {
        UnitSearching unitSearching = new UnitSearching();
        System.out.println("keke");
    }
}
