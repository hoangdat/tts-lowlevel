package XML_Processing;

/**
 * @author thaodv-bkit
 * @version 1.0
 * @created 05-Apr-2011 2:35:52 PM
 */
public class SylPhrase {

	private int phraseLen;
        private int id_phrase;
	private int phraseLevel;
	private String phraseContent;

	/**
	 * vi tri bat dau cua cum tu trong file am thanh
	 */
	private int startIndex;

	/**
	 * vi tri ket thuc cua cum tu trong file am thanh
	 */
	private int endIndex;



	public SylPhrase(){

	}

    /**
     * @return the numberOfSyllables
     */
    public int getNumberOfSyllables() {
        return phraseLen;
    }

    /**
     * @param numberOfSyllables the numberOfSyllables to set
     */
    public void setNumberOfSyllables(int numberOfSyllables) {
        this.phraseLen = numberOfSyllables;
    }

    /**
     * @return the phraseLevel
     */
    public int getPhraseLevel() {
        return phraseLevel;
    }

    /**
     * @param phraseLevel the phraseLevel to set
     */
    public void setPhraseLevel(int phraseLevel) {
        this.phraseLevel = phraseLevel;
    }

    /**
     * @return the text
     */
    public String getContent() {
        return phraseContent;
    }

    /**
     * @param text the text to set
     */
    public void setContent(String text) {
        this.phraseContent = text;
    }

    /**
     * @return the startIndex
     */
    public int getStartIndex() {
        return startIndex;
    }

    /**
     * @param startIndex the startIndex to set
     */
    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    /**
     * @return the endIndex
     */
    public int getEndIndex() {
        return endIndex;
    }

    /**
     * @param endIndex the endIndex to set
     */
    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }



}