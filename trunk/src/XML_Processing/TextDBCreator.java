/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package XML_Processing;

import JFrameGUI.FileInput;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 *
 * @author thaodv-bkit
 */
public class TextDBCreator {

    XMLOutputFactory outputFactory;
    XMLStreamWriter streamWriter;
    FileWriter fileWriter;
    String fileName = "Text_DB_Creator.xml";
    ArrayList<Syllable> syls;

    public TextDBCreator() throws XMLStreamException, FileNotFoundException {
        outputFactory = XMLOutputFactory.newInstance();
        streamWriter = null;
        ////////////////////////////////////////////////////////////////////////
        XMLTextDBSylReader xMLTextDBSylReader = new XMLTextDBSylReader("Text_DB_Syllable.xml");
        xMLTextDBSylReader.ReadDetails();
        FileInput f = new FileInput("ListOfSyllable.tdd");
        xMLTextDBSylReader.ReadListOfSyllable(f.getListLines());
        syls = xMLTextDBSylReader.getSylArray();
        ////////////////////////////////////////////////////////////////////////
        try {
            fileWriter = new FileWriter(fileName);
            try {
                streamWriter = outputFactory.createXMLStreamWriter(fileWriter);
            } catch (XMLStreamException ex) {
                Logger.getLogger(TextDBCreator.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(XML_Creator.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            streamWriter.flush();
            streamWriter.close();
        } catch (XMLStreamException ex) {
            Logger.getLogger(TextDBCreator.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
    ////

    public void WriteTextDB() throws XMLStreamException {

        boolean isStartDocument, isStartFile, isStartSentence, isStartSylPhrase, isStartSyllable, isEndFile = false, isEndSentence, isEndSylPhrase, isEndSyllable;
        streamWriter = outputFactory.createXMLStreamWriter(fileWriter);
        String carryingFile = syls.get(0).getCarryingFile();
        String sylName;
        boolean startSylPh = false, startSen = false, startFile = false;

        for (int i = 0; i < syls.size(); i++) {
            System.out.println("this: " + i);
            //kiem tra bat dau va ket thuc 1 file
            //bat dau 1 file khi i = 0 hoac carryingFile lien tiep khac nhau
            if (syls.get(i).getStartIndex() == 0) {
                isStartFile = true;
                carryingFile = syls.get(i).getCarryingFile();
            } else {
                isStartFile = false;
            }
            if (i == (syls.size() - 1)) {
                isEndFile = true;
            } else if (i < syls.size() - 2) {
                if (syls.get(i + 1).getCarryingFile().compareTo(carryingFile) != 0) {
                    isEndFile = true;
                }
            } else {
                isEndFile = false;
            }
            sylName = syls.get(i).getSylName();
            ////////////////////////////////////////////////////////////////////
            //kiem tra bat dau va ket thuc 1 cau
            //bat dau 1 cau khi bat dau 1 file hoac phia truoc la SILS
            //ket thuc 1 cau khi gap SILS va khong phai bat dau 1 file hoac khi ket thuc 1 file
            if (isStartFile || syls.get(i - 1).getSylName().compareTo("SILS") == 0) {
                isStartSentence = true;
            } else {
                isStartSentence = false;
            }
            if ((isEndFile) || (sylName.compareTo("SILS") == 0 && !isStartFile)) {
                isEndSentence = true;
            } else {
                isEndSentence = false;
            }

            ////////////////////////////////////////////////////////////////////
            //kiem tra bat dau va ket thuc 1 doan
            //bat dau 1 doan khi bat dau 1 cau hoac phia truoc la SIL
            //ket thuc 1 doan khi gap SIL hoac khi ket thuc 1 cau
            if (isStartSentence || syls.get(i - 1).getSylName().compareTo("SIL") == 0) {
                isStartSylPhrase = true;
            } else {
                isStartSylPhrase = false;
            }
            if ((isEndSentence) || (sylName.compareTo("SIL") == 0)) {
                isEndSylPhrase = true;
            } else {
                isEndSylPhrase = false;
            }
            //ket thuc kiem tra
            ////////////////////////////////////////////////////////////////////
            if (isStartFile) {
                streamWriter.writeStartElement("file");
                streamWriter.writeAttribute("file_name", syls.get(i).getCarryingFile());
                startFile = !startFile;
            }
            //
            if (isStartSentence) {
                streamWriter.writeStartElement("sentence");
                startSen = !startSen;
                // streamWriter.writeAttribute("", syls.get(i).getCarryingFile());
            }
            //

            if (isStartSylPhrase) {
                streamWriter.writeStartElement("phrase");
                startSylPh = !startSylPh;
            }
            //
            WriteSyl(syls.get(i), i);
            //
            if (isEndSylPhrase && startSylPh) {
                streamWriter.writeEndElement();
                startSylPh = !startSylPh;
            }
            //
            if (isEndSentence && startSen) {
                streamWriter.writeEndElement();
                startSen = !startSen;
            }
            //
            if (isEndFile&&startFile) {
                streamWriter.writeEndElement();
                startFile = !startFile;
            }

        }//end for
        streamWriter.flush();
        streamWriter.close();

    }
    ////////////////////////////////////////////////////////////////////////////

    public void WriteSyl(Syllable syl, int id) throws XMLStreamException {
        streamWriter.writeStartElement("syllable");
        /////////////////////////
        {
            streamWriter.writeAttribute("id_syl", ((Integer) id).toString());
            streamWriter.writeAttribute("name", syl.getSylName());
            streamWriter.writeAttribute("start_index", ((Integer) syl.getStartIndex()).toString());
            streamWriter.writeAttribute("end_index", ((Integer) syl.getEndIndex()).toString());
            streamWriter.writeAttribute("end_index", ((Integer) syl.getSylPosition()).toString());
            streamWriter.writeAttribute("end_index", ((Integer) syl.getNumOfPhone()).toString());
            streamWriter.writeAttribute("end_index", ((Float) syl.getEnergy()).toString());
        }
        ////////////////////////////////////////////////////////////////////////
        {
            streamWriter.writeStartElement("initial");
            streamWriter.writeAttribute("type", syl.getInitialType());
            streamWriter.writeCharacters(syl.getIntialPhoneme());
            streamWriter.writeEndElement();
            ////////////////////////////////////////////////////////////////////
            streamWriter.writeStartElement("middle");
            streamWriter.writeAttribute("type", syl.getMiddleType());
            streamWriter.writeCharacters(syl.getMiddlePhoneme());
            streamWriter.writeEndElement();
            ////////////////////////////////////////////////////////////////////
            streamWriter.writeStartElement("initial");
            streamWriter.writeAttribute("type", syl.getNucleusType());
            streamWriter.writeCharacters(syl.getNucleusPhoneme());
            streamWriter.writeEndElement();
            ////////////////////////////////////////////////////////////////////
            streamWriter.writeStartElement("initial");
            streamWriter.writeAttribute("type", syl.getFinalType());
            streamWriter.writeCharacters(syl.getFinalPhoneme());
            streamWriter.writeEndElement();
            ////////////////////////////////////////////////////////////////////
        }
        ////////////////////////////////////////////////////////////////////////
        {
            streamWriter.writeStartElement("tone");
            streamWriter.writeCharacters(((Integer) syl.getSylTone()).toString());
            streamWriter.writeEndElement();
        }
        ////////////////////////////////////////////////////////////////////////
        streamWriter.writeEndElement();
    }

    public static void main(String[] args) throws XMLStreamException, FileNotFoundException {

        TextDBCreator textDBCreator = new TextDBCreator();
        textDBCreator.WriteTextDB();
    }
}
