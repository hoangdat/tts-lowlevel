/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package XML_Processing;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author thaodv_bkit
 */
public class LevelPhrase extends Phrase {
    private int level;
    private ArrayList<Integer> subLevel = new ArrayList<Integer>();
    private ArrayList<String> syllableIn = new ArrayList<String>();
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
    public ArrayList<Integer> getSubLevel() {
        return subLevel;
    }

    /*
     * kiem tra xem phrase co subLevel hay khong
     */
    public boolean haveSubLevel() {
        if (subLevel.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /*
     *
     */
     public void addIndexOfSubLevel(int indexOfPhrase){
         subLevel.add(indexOfPhrase);
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


    
}
