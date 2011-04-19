/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package XML_Processing;

import java.util.ArrayList;

/**
 *
 * @author thaodv-bkit
 */
public class SylPhrase extends Phrase {
    private ArrayList<Syllable> syllablesInPh = new ArrayList<Syllable>();

    public SylPhrase() {
    }

    public void setPhraseLen() {
        int len = 0;
        
        for (int i = 0; i < syllablesInPh.size(); i++) {
            String name = syllablesInPh.get(i).getSylName();
            if ((name.compareTo("SIL") == 0) || (name.compareTo("SILS") == 0) || (name.compareTo("SILP") == 0)) {
            } else {
                len++;
            }

        }
        this.setPhraseLen(len);
    }
    /*
     * dung cho SylPhrase
     */
    public void setContent(){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < syllablesInPh.size(); i++) {
            sb.append(syllablesInPh.get(i).getSylName()).append(" ");
        }
        this.setPhraseContent(sb.toString().trim());
    }
    /**
     * @return the syllablesInPh
     */
    public ArrayList<Syllable> getSyllablesInPh() {
        return syllablesInPh;
    }

    /**
     * @param syllablesInPh the syllablesInPh to set
     */
    public void setSyllablesInPh(ArrayList<Syllable> syllablesInPh) {
        this.syllablesInPh = syllablesInPh;
    }
}
