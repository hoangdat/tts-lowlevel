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
        //selectLP();//tong hop khi khong dung ham chi phi
        selectLPByCost();
        System.out.println("");
        setIndex();

        writeToTextFile();
        //this.printDetails();
    }

    public void selectLP() {
        for (int i = 0; i < getFoundLPhrs().size(); i++) {
            //System.out.println(i + getFoundLPhrs().get(i).getPhraseContent());
            if (getFoundLPhrs().get(i).isFound()==1) {
                getFoundLPhrs().get(i).setSelectedSen(getFoundLPhrs().get(i).getFoundIndexes1().get(0));
                getFoundLPhrs().get(i).setSelectedSylPhrs(getFoundLPhrs().get(i).getFoundIndexes2().get(0));
                getFoundLPhrs().get(i).setSelectedSyllable(getFoundLPhrs().get(i).getFoundIndexes3().get(0));
            } else {
                //System.out.println("khong thay: " + getFoundLPhrs().get(i).getPhraseContent());
            }
        }
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
    private void setIndex() {
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
    private void writeToTextFile() {

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
        if(leftLP.isFound()==1&rightLP.isFound()==1){
            //xy ly truong hop ca hai deu duoc tim thay trong CSDL
            //xu ly trong truong hop leftLP & rightLP != SILS, SILP
            //System.out.println(i );
            int leftIndex1 = leftLP.getFoundIndexes1().get(i);//chi so cua cau trong CSDL chua leftLP
            int leftIndex2 = leftLP.getFoundIndexes2().get(i);//chi so cua SylPhrase trong CSDL chua leftLP
            int leftIndex3 = leftLP.getFoundIndexes3().get(i);//chi so cua syllable dau tien trong CSDL cua leftLP
            String leftSylName = leftLP.getFirstSylInLPhrs().getSylName();
            String rightSylName = rightLP.getFirstSylInLPhrs().getSylName();
            int rightIndex1 = rightLP.getFoundIndexes1().get(j);//chi so cua cau trong CSDL chua rightLP
            int rightIndex2 = rightLP.getFoundIndexes2().get(j);//chi so cua SylPhrase trong CSDL chua rightLP
            int rightIndex3 = rightLP.getFoundIndexes3().get(j);//chi so cua syllable dau tien trong CSDL cua rightLP
             // Luu y truong hop dau file va cuoi file thi cac am tiet la null
            if(leftSylName.compareTo("SIL")==0){
                //am tiet ben trai la SIL
                return;
            }
            if(rightSylName.compareTo("SIL")==0){
                //am tiet ben phai la SIL
                return;
            }
            Syllable lastSylOfLeftCandUnit = allSenInTextDB.get(leftIndex1).getSylPhrases().get(leftIndex2).getSyllablesInPh().get(leftIndex3+leftLP.getNumberOfSyllables()-1);
            Syllable firstSylOfRightCandUnit = allSenInTextDB.get(rightIndex1).getSylPhrases().get(rightIndex2).getSyllablesInPh().get(rightIndex3);
           
            Syllable rightSylOfLeftCandUnit = new Syllable();
            rightSylOfLeftCandUnit.setSylTone(lastSylOfLeftCandUnit.getRightTone());
            rightSylOfLeftCandUnit.setIntialPhoneme(lastSylOfLeftCandUnit.getRightPhoneme());
            rightSylOfLeftCandUnit.setInitialType(lastSylOfLeftCandUnit.getRightPhonemeType());
            rightSylOfLeftCandUnit.setSylName(lastSylOfLeftCandUnit.getRightSyl());
            Syllable leftSylOfRightCandUnit = new Syllable();
            leftSylOfRightCandUnit.setSylTone(firstSylOfRightCandUnit.getSylTone());
            leftSylOfRightCandUnit.setFinalPhoneme(firstSylOfRightCandUnit.getFinalPhoneme());
            leftSylOfRightCandUnit.setFinalType(firstSylOfRightCandUnit.getFinalType());
            leftSylOfRightCandUnit.setSylName(firstSylOfRightCandUnit.getLeftSyl());
            // Doi voi LeftCandUnit, ta can lay am tiet cuoi cung cua no la LastSylOfLeftCand, va am tiet dau tien ben phai CandUnitLeft la rightSylOfLeftCandUnit
            // Doi voi RightCandUnit, ta can lay am tiet dau tien cua no la FirstSylOfRightCand, va am tiet cuoi cung ben trai CandUnitRight la leftSylOfRightCandUnit
            // Can so sanh LastSylOfLeftCandUnit voi leftSylOfRightCandUnit; so sanh am cuoi va thanh dieu
            // Can so sanh rightSylOfLeftCandUnit voi FirstSylOfRightCand; so sanh am dau va thanh dieu
            //System.out.println(lastSylOfLeftCandUnit.getSylName()+" -- "+firstSylOfRightCandUnit.getSylName());
            if(lastSylOfLeftCandUnit.getSylName().compareTo(leftLP.getLastSylInLPhrs().getSylName())!=0){
                System.out.println("khac tai i = "+i+" : "+j);
            }
            if(firstSylOfRightCandUnit.getSylName().compareTo(rightLP.getFirstSylInLPhrs().getSylName())!=0){
                System.out.println("khac tai j = "+j+" : "+i);
            }
            //System.out.println(rightSylOfLeftCandUnit.getSylName()+" -- "+leftSylOfRightCandUnit.getSylName());
            
            //can so sanh LastSylOfLeftLP voi leftSylOfRightLP
            //can so sanh FirstSylOfRightLP voi rightSylOfLeftLP

        }else{
            System.out.println("hien tai chua xu ly buoc nay");
        }
    }
     public static void main(String[] args) throws XMLStreamException, FileNotFoundException {
        new UnitSelection();
         
    }
}
