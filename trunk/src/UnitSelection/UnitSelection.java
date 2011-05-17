/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UnitSelection;

import XML_Processing.LevelPhrase;
import XML_Processing.Sentence;
import XML_Processing.Syllable;
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
    private ArrayList<LevelPhrase> selectedLPhrs;
    private ArrayList<String> fileNames;
    private String pathFile;
    ArrayList<Sentence> allSenInTextDB;


    public UnitSelection() throws XMLStreamException, FileNotFoundException {
        us = new UnitSearching();
        selectedLPhrs = us.getFoundLPhs();
        allSenInTextDB = UnitSearching.getAllSenInTextDB();
        //selectLP();
        selectLPByCost();

        setIndex();
        writeToTextFile();

        //this.printDetails();
    }

//    public void selectLP() {
//        for (int i = 0; i < getSelectedLPhrs().size(); i++) {
//            System.out.println(i + getSelectedLPhrs().get(i).getPhraseContent());
//            if (getSelectedLPhrs().get(i).isFound()) {
//                getSelectedLPhrs().get(i).setSelectedSen(getSelectedLPhrs().get(i).getFoundSen().get(0));
//                getSelectedLPhrs().get(i).setSelectedSylPhrs(getSelectedLPhrs().get(i).getFoundSylPhrs().get(0));
//                getSelectedLPhrs().get(i).setSelectedSyllable(getSelectedLPhrs().get(i).getFoundSyllable().get(0));
//            } else {
//                System.out.println("khong thay: " + getSelectedLPhrs().get(i).getPhraseContent());
//            }
//        }
//    }

    public static void main(String[] args) throws XMLStreamException, FileNotFoundException {
        new UnitSelection();
    }

    /**
     * @return the selectedLPhrs
     */
    public ArrayList<LevelPhrase> getSelectedLPhrs() {
        return selectedLPhrs;
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
        for (int i = 0; i < selectedLPhrs.size(); i++) {
            fileNames.add(UnitSearching.getAllSenInTextDB().get(selectedLPhrs.get(i).getSelectedSen()).getCarryingFile());
            int selectedSen = selectedLPhrs.get(i).getSelectedSen();
            int selectedSylPhrs = selectedLPhrs.get(i).getSelectedSylPhrs();
            int selectedSyllable = selectedLPhrs.get(i).getSelectedSyllable();
            int startIndex = UnitSearching.getAllSenInTextDB().get(selectedSen).getSylPhrases().get(selectedSylPhrs).getSyllablesInPh().get(selectedSyllable).getStartIndex();
            int endIndex = UnitSearching.getAllSenInTextDB().get(selectedSen).getSylPhrases().get(selectedSylPhrs).getSyllablesInPh().get(selectedSyllable + selectedLPhrs.get(i).getPhraseLen() - 1).getEndIndex();
            selectedLPhrs.get(i).setStartIndex(startIndex);
            selectedLPhrs.get(i).setEndIndex(endIndex);
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
            for (int i = 0; i < selectedLPhrs.size(); i++) {
                bufferedWriter.write((Boolean.valueOf(selectedLPhrs.get(i).isFound()).toString()) + " ");
                bufferedWriter.write(fileNames.get(i) + " ");
                //bufferedWriter.write((Integer.valueOf(selectedLPhrs.get(i).getSelectedSylPhrs()).toString()) + " ");
                //bufferedWriter.write((Integer.valueOf(selectedLPhrs.get(i).getSelectedSyllable()).toString()) + " ");
                bufferedWriter.write((Integer.valueOf(selectedLPhrs.get(i).getStartIndex()).toString()) + " ");
                bufferedWriter.write((Integer.valueOf(selectedLPhrs.get(i).getEndIndex()).toString()) + " ");
                bufferedWriter.write(selectedLPhrs.get(i).getPhraseContent().trim() + "\n");
            }
            bufferedWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(UnitSelection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void printDetails() throws FileNotFoundException {
        for (int i = 0; i < selectedLPhrs.size(); i++) {
            System.out.println(selectedLPhrs.get(i).getPhraseContent() + "\t" +
                    selectedLPhrs.get(i).isFound() + "\t" + fileNames.get(i) +
                    "\t" + selectedLPhrs.get(i).getSelectedSylPhrs() +
                    "\t" + selectedLPhrs.get(i).getSelectedSyllable() +
                    "\t" + selectedLPhrs.get(i).getStartIndex() + " : " +
                    selectedLPhrs.get(i).getEndIndex());
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
        for (int i = 0; i < getSelectedLPhrs().size(); i++) {
            LevelPhrase desiredUnit = getSelectedLPhrs().get(i);
            ArrayList<Integer> candidateUnitsSen = desiredUnit.getFoundSen();//cau chua candidate units
            ArrayList<Integer> candidateUnitSylPhrs = desiredUnit.getFoundSylPhrs();
            ArrayList<Integer> candidateUnitFirstSyl = desiredUnit.getFoundSyllable();
            int numOfCandidateUnit = candidateUnitFirstSyl.size();
            
            for (int j = 0; j < numOfCandidateUnit; j++) {
                DistanceVector dv = new DistanceVector();
                Syllable firstSyllableOfCandidate = allSenInTextDB.get(candidateUnitsSen.get(j)).getSylPhrases().get(candidateUnitSylPhrs.get(j)).getSyllablesInPh().get(candidateUnitFirstSyl.get(j));
                // set posInPhrsDistance
                if(desiredUnit.getPosInSen() == candidateUnitFirstSyl.get(j)){
                    dv.setPosInPhrsDis(0);
                }else{
                    dv.setPosInPhrsDis(1);
                }
            
                
            }




        }
    }
}
