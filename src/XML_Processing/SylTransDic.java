/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package XML_Processing;

import java.io.File;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author thaodv-bkit
 */
public class SylTransDic {

    FileInput fi;
    ArrayList<String> listLines;
    ArrayList<Syllable> sylDic = new ArrayList<Syllable>();
    ArrayList<String> oneLine ;
    Syllable syllable;
    String[] firstCons = {"NG","th","tr","c","NJ","f","X","z","z","G","k","d","t","b","m","n","l","s","ss","h","v","zr","p"};
    String[] vowels = {"M7","uo","ie","M","7","o","e","E","7X","aX","a","EX","aX","O","OX","u","i"};//17
    String[] lastPhns = {"p","t","k","m","n","NG","w","j"};//8
    String[] csPhoneme	=           {"7X","EX","aX","OX","ie","M7","uo","NG","th","tr","NJ","ss","zr"};
    //csPhoneme la dang sampa
    String[] csPhonemeConverted  =  {"Z", "A", "B", "Q", "I", "U", "Y", "N", "T", "R", "J", "S", "r"};
    //coPhoneme la dang trong CSDL
    public SylTransDic() {
        fi = new FileInput(new File("PronunciationDictionary.dic"));
        listLines = fi.getListLines();
        ReadSylTransDic();
        System.out.println("keke");
    }

    private void ReadSylTransDic() {
        for (int i = 0; i < listLines.size(); i++) {
            oneLine = new ArrayList<String>();
            int beginIndex=0, endIndex=0;
            while(endIndex!=-1){
                endIndex = listLines.get(i).indexOf(" ", beginIndex);
                if(endIndex==-1) break;
                oneLine.add(listLines.get(i).substring(beginIndex,endIndex));
                beginIndex = endIndex+1;
            }
            oneLine.add(listLines.get(i).substring(beginIndex));
            syllable = new Syllable();
            syllable.setSylName(oneLine.get(0));
            sylDic.add(syllable);
        }
    }

    public static void main(String[] args) {
        new SylTransDic();
    }
}
