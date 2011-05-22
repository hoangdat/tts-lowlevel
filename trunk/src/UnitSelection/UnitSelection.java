/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UnitSelection;

import Units.LevelPhrase;
import Units.Sentence;
import Units.Syllable;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author thaodv_bkit
 */
public class UnitSelection {

    private UnitSearching us;
    private ArrayList<LevelPhrase> foundLPhrs;
    private ArrayList<String> fileNames;
    private String pathFile;
    ArrayList<Sentence> allSenInTextDB;


    public UnitSelection() throws XMLStreamException, FileNotFoundException {
        us = new UnitSearching();
        foundLPhrs = us.getFoundLPhs();
        allSenInTextDB = UnitSearching.getAllSenInTextDB();
        //selectLP();
        selectLPByCost();
        setIndex();
        writeToTextFile();
        //this.printDetails();
    }

//    public void selectLP() {
//        for (int i = 0; i < getFoundLPhrs().size(); i++) {
//            System.out.println(i + getFoundLPhrs().get(i).getPhraseContent());
//            if (getFoundLPhrs().get(i).isFound()) {
//                getFoundLPhrs().get(i).setSelectedSen(getFoundLPhrs().get(i).getFoundIndexes1().get(0));
//                getFoundLPhrs().get(i).setSelectedSylPhrs(getFoundLPhrs().get(i).getFoundIndexes2().get(0));
//                getFoundLPhrs().get(i).setSelectedSyllable(getFoundLPhrs().get(i).getFoundIndexes3().get(0));
//            } else {
//                System.out.println("khong thay: " + getFoundLPhrs().get(i).getPhraseContent());
//            }
//        }
//    }

    public static void main(String[] args) throws XMLStreamException, FileNotFoundException {
        new UnitSelection();
    }

    /**
     * @return the selectedLPhrs
     */
    public ArrayList<LevelPhrase> getFoundLPhrs() {
        return foundLPhrs;
    }

    /**
     * @return the fileNames
     */
    public ArrayList<String> getFileNames() {
        return fileNames;
    }

//    /**
//     * @param fileNames the fileNames to set
//     */
//    public void setFileNames() {
//        fileNames = new ArrayList<String>();
//        for (int i = 0; i < selectedLPhrs.size(); i++) {
//            fileNames.add(UnitSearching.allSenInTextDB.get(selectedLPhrs.get(i).getSelectedSen()).getCarryingFile());
//        }
//    }
    public void setIndex() {
        fileNames = new ArrayList<String>();
        for (int i = 0; i < foundLPhrs.size(); i++) {
            fileNames.add(UnitSearching.getAllSenInTextDB().get(foundLPhrs.get(i).getSelectedSen()).getCarryingFile());
            int selectedSen = foundLPhrs.get(i).getSelectedSen();
            int selectedSylPhrs = foundLPhrs.get(i).getSelectedSylPhrs();
            int selectedSyllable = foundLPhrs.get(i).getSelectedSyllable();
            int startIndex = UnitSearching.getAllSenInTextDB().get(selectedSen).getSylPhrases().get(selectedSylPhrs).getSyllablesInPh().get(selectedSyllable).getStartIndex();
            int endIndex = UnitSearching.getAllSenInTextDB().get(selectedSen).getSylPhrases().get(selectedSylPhrs).getSyllablesInPh().get(selectedSyllable + foundLPhrs.get(i).getPhraseLen() - 1).getEndIndex();
            foundLPhrs.get(i).setStartIndex(startIndex);
            foundLPhrs.get(i).setEndIndex(endIndex);
        }
    }

    /*
     *
     */
    public void writeToTextFile() {

        File f = new File(System.getProperty("user.dir")+"\\SelectedLPhrs.txt");
        this.setPathFile(f.getAbsolutePath());
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f),"UTF8"));
            for (int i = 0; i < foundLPhrs.size(); i++) {
                bufferedWriter.write((Integer.valueOf(foundLPhrs.get(i).isFound()).toString()) + " ");
                bufferedWriter.write(fileNames.get(i) + " ");
                //bufferedWriter.write((Integer.valueOf(selectedLPhrs.get(i).getSelectedSylPhrs()).toString()) + " ");
                //bufferedWriter.write((Integer.valueOf(selectedLPhrs.get(i).getSelectedSyllable()).toString()) + " ");
                bufferedWriter.write((Integer.valueOf(foundLPhrs.get(i).getStartIndex()).toString()) + " ");
                bufferedWriter.write((Integer.valueOf(foundLPhrs.get(i).getEndIndex()).toString()) + " ");
                bufferedWriter.write(foundLPhrs.get(i).getPhraseContent().trim() + "\n");
            }
            bufferedWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(UnitSelection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void printDetails() throws FileNotFoundException {
        for (int i = 0; i < foundLPhrs.size(); i++) {
            System.out.println(foundLPhrs.get(i).getPhraseContent() + "\t" +
                    foundLPhrs.get(i).isFound() + "\t" + fileNames.get(i) +
                    "\t" + foundLPhrs.get(i).getSelectedSylPhrs() +
                    "\t" + foundLPhrs.get(i).getSelectedSyllable() +
                    "\t" + foundLPhrs.get(i).getStartIndex() + " : " +
                    foundLPhrs.get(i).getEndIndex());
        }
    }

    /**
     * @return the pathFile
     */
    public String getPathFile() {
        return pathFile;
    }

    /**
     * @param pathFile the pathFile to set
     */
    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }

    public void selectLPByCost() {
        calculateCostForCandidateUnits();
    }

    private void calculateCostForCandidateUnits() {
        for (int i = 0; i < getFoundLPhrs().size()-1; i++) {
            calculateCostFor2LP(getFoundLPhrs().get(i),getFoundLPhrs().get(i+1));
        }
    }

    private void calculateCostFor2LP(LevelPhrase leftLP, LevelPhrase rightLP) {
        for (int i = 0; i < leftLP.getFoundIndexes1().size(); i++) {
            for (int j = 0; j < rightLP.getFoundIndexes1().size(); j++) {
                calculateCostFor2CandidateUnits(leftLP,i,rightLP,j);
            }
        }
    }

    private void calculateCostFor2CandidateUnits(LevelPhrase leftLP, int i, LevelPhrase rightLP, int j) {
        //xu ly trong truong hop syllable gom 2 ban am tiet
        if(leftLP.isFound()==0){

        }
    }
}
