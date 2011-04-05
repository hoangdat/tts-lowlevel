package XML_Processing;

import XML_Processing.SylPhrase;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author thaodv-bkit
 * @version 1.0
 * @created 05-Apr-2011 2:41:52 PM
 */
public class Sentence {

	private ArrayList<Syllable> syllablesInSen =new ArrayList<Syllable>();
	private ArrayList<SylPhrase> sylPhrases = new ArrayList<SylPhrase>();
	private int IDofSentence;
	private String carryingFile;
	
    




	public void Sentence(){
	}
        ///////////////////////////////////
        public void addSyllable(Syllable s){
            syllablesInSen.add(s);
        }

	

	public int getSentenceLength(){
		return 0;
	}

	/**
	 * 
	 * @param lengthOfSylPhrase
	 */
	public ArrayList<SylPhrase> getSylPharseByLength(int lengthOfSylPhrase){
		return null;
	}

	/**
	 * 
	 * @param level
	 */
	public ArrayList<SylPhrase> getSylPhraseByLevel(int level){
		return null;
	}

    /**
     * @return the syllablesInSen
     */
    public ArrayList<Syllable> getSyllablesInSen() {
        return syllablesInSen;
    }

    

    /**
     * @return the sylPhrases
     */
    public ArrayList<SylPhrase> getSylPhrases() {
        return sylPhrases;
    }

    /**
     * @param sylPhrases the sylPhrases to set
     */
    public void setSylPhrases(ArrayList<SylPhrase> sylPhrases) {
        this.sylPhrases = sylPhrases;
    }

    /**
     * @return the IDofSentence
     */
    public int getIDofSentence() {
        return IDofSentence;
    }

    /**
     * @param IDofSentence the IDofSentence to set
     */
    public void setIDofSentence(int IDofSentence) {
        this.IDofSentence = IDofSentence;
    }

    /**
     * @return the carryingFile
     */
    public String getCarryingFile() {
        return carryingFile;
    }

    /**
     * @param carryingFile the carryingFile to set
     */
    public void setCarryingFile(String carryingFile) {
        this.carryingFile = carryingFile;
    }

    

    
}