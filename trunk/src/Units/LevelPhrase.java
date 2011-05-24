/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Units;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author thaodv_bkit
 */
public class LevelPhrase extends Phrase {

    private int level;
    private ArrayList<Integer> indexesOfSubLevel = new ArrayList<Integer>();
    private ArrayList<String> syllableIn = new ArrayList<String>();
    private int isFound;// LP co duoc tim thay trong TextDB hay khong
    //isFound = 1; LP duoc tim thay trong TextDB
    //isFound = 2; LP duoc tim thay trong HalfSyl
    //isFound = 0; LP ko duoc tim thay trong HalfSyl
    private ArrayList<Integer> foundSen = new ArrayList<Integer>();//id cua cac cau tim thay LP
    private ArrayList<Integer> foundSylPhrs = new ArrayList<Integer>();// id cua SylPhrase tim thay LP
    private ArrayList<Integer> foundSyllable = new ArrayList<Integer>();// id cua Syllable trong SylPhrase tim thay LP
    private int selectedSen, selectedSylPhrs, selectedSyllable; //id cua cau, sylphrase, syllable duoc chon sau khi co ham chi phi
    private Syllable firstSylInLPhrs, lastSylInLPhrs;// am tiet bat dau va ket thuc cua LevelPhrase. dung de tinh khoang cach ngu canh cho cac LP ben canh
    private int posInSen; // vi tri bat dau cua LP trong Phrase

    public LevelPhrase(){
        
    }

    /**
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(int level) {
        this.level = level;
    }
    /**
     * @return the subLevel
     */
    public ArrayList<Integer> getIndexesOfSubLevel() {
        return indexesOfSubLevel;
    }

    /*
     * kiem tra xem phrase co subLevel hay khong
     */
    public boolean haveSubLevel() {
        if (indexesOfSubLevel.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /*
     *
     */
     public void addIndexOfSubLevel(int indexOfPhrase){
         indexesOfSubLevel.add(indexOfPhrase);
     }

    /**
     * @return the syllableIn
     */
    public ArrayList<String> getSyllableIn() {
        return syllableIn;
    }

    /**
     * @param syllableIn the syllableIn to set
     */
    public void setSyllableIn() {
        String pc = this.getPhraseContent();
        StringTokenizer stk = new StringTokenizer(pc);
        setPhraseLen(stk.countTokens());
        
        while(stk.hasMoreTokens()){
            syllableIn.add(stk.nextToken());
        }
    }


    @Override
    public void setPhraseContent(String phraseContent) {
        super.setPhraseContent(phraseContent.trim());
        //this.setSyllableIn();
    }

    /*
     *
     */
    public void setIsFound(int f){
        isFound = f;
    }

    public int isFound(){
        return isFound;
    }

    /**
     * @return the foundSen
     */
    public ArrayList<Integer> getFoundIndexes1() {
        return foundSen;
    }

    /**
     * @return the foundSylPhrs
     */
    public ArrayList<Integer> getFoundIndexes2() {
        return foundSylPhrs;
    }

    /**
     * @return the foundSyllable
     */
    public ArrayList<Integer> getFoundIndexes3() {
        return foundSyllable;
    }

    /**
     * @return the selectedSen
     */
    public int getSelectedSen() {
        return selectedSen;
    }

    /**
     * @param selectedSen the selectedSen to set
     */
    public void setSelectedSen(int selectedSen) {
        this.selectedSen = selectedSen;
    }

    /**
     * @return the selectedSylPhrs
     */
    public int getSelectedSylPhrs() {
        return selectedSylPhrs;
    }

    /**
     * @param selectedSylPhrs the selectedSylPhrs to set
     */
    public void setSelectedSylPhrs(int selectedSylPhrs) {
        this.selectedSylPhrs = selectedSylPhrs;
    }

    /**
     * @return the selectedSyllable
     */
    public int getSelectedSyllable() {
        return selectedSyllable;
    }

    /**
     * @param selectedSyllable the selectedSyllable to set
     */
    public void setSelectedSyllable(int selectedSyllable) {
        this.selectedSyllable = selectedSyllable;
    }

    /**
     * @return the firstSylInLPhrs
     */
    public Syllable getFirstSylInLPhrs() {
        return firstSylInLPhrs;
    }

    /**
     * @param firstSylInLPhrs the firstSylInLPhrs to set
     */
    public void setFirstSylInLPhrs(Syllable firstSylInLPhrs) {
        this.firstSylInLPhrs = firstSylInLPhrs;
    }

    /**
     * @return the lastSylInLPhrs
     */
    public Syllable getLastSylInLPhrs() {
        return lastSylInLPhrs;
    }

    /**
     * @param lastSylInLPhrs the lastSylInLPhrs to set
     */
    public void setLastSylInLPhrs(Syllable lastSylInLPhrs) {
        this.lastSylInLPhrs = lastSylInLPhrs;
    }

    /**
     * @return the posInSen
     */
    public int getPosInSen() {
        return posInSen;
    }

    /**
     * @param posInSen the posInSen to set
     */
    public void setPosInSen(int posInSen) {
        this.posInSen = posInSen;
    }


    
}
