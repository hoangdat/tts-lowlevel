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

	
	private ArrayList<SylPhrase> sylPhrases = new ArrayList<SylPhrase>();
	private int iDofSentence;
        private int iDofFile;
	private String carryingFile;
        private String senContent;

	
    




	public void Sentence(){
	}
        ///////////////////////////////////
        
	

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
        this.setSylPhrases(sylPhrases);
    }

    /**
     * @return the IDofSentence
     */
    public int getIDofSentence() {
        return iDofSentence;
    }

    /**
     * @param IDofSentence the IDofSentence to set
     */
    public void setIDofSentence(int IDofSentence) {
        this.iDofSentence = IDofSentence;
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

    /**
     * @return the senContent
     */
    public String getSenContent() {
        return senContent;
    }

    /**
     * @param senContent the senContent to set
     */
    public void setSenContent(String senContent) {
        this.senContent = senContent;
    }

    /**
     * @return the iDofFile
     */
    public int getiDofFile() {
        return iDofFile;
    }

    /**
     * @param iDofFile the iDofFile to set
     */
    public void setiDofFile(int iDofFile) {
        this.iDofFile = iDofFile;
    }

    
    

    

    
}