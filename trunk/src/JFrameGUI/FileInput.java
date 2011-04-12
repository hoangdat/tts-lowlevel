package JFrameGUI;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author thaodv
 */
import XML_Processing.XML_Creator;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.xml.stream.XMLStreamException;

/**
 * This program reads a text file line by line and print to the console. It uses
 * FileOutputStream to read the file.
 *
 */
public class FileInput {

    String inputText;
    StringBuffer sbResult = new StringBuffer();
    ArrayList<String> listLines = new ArrayList();

    public ArrayList<String> getListLines() {
        return listLines;
    }

    
   

    public StringBuffer getSbResult() {
        return sbResult;
    }
    ////////////////////////////////////////////////////////////////////////////
    public FileInput(File inputFile){
        FileInputStream fis = null;
        InputStreamReader isr = null;

        BufferedReader br;
        String eachline = new String();

        try {
            fis = new FileInputStream(inputFile);
            isr = new InputStreamReader(fis,"UTF-8");
            br = new BufferedReader(isr);

            eachline = br.readLine();
            while(eachline!=null){
                listLines.add(eachline);
                 sbResult.append(eachline);
                eachline = br.readLine();
            }

            // dispose all the resources after using them.
            fis.close();
            isr.close();
            br.close();


        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }
    public FileInput(String file)  {
        FileInputStream fis = null;
        InputStreamReader isr = null;
       
        BufferedReader br;
        String eachline = new String();

        try {
            fis = new FileInputStream(new File(file));
            isr = new InputStreamReader(fis,"UTF-8");
            br = new BufferedReader(isr);
            
            eachline = br.readLine();
            while(eachline!=null){
                listLines.add(eachline);
                 sbResult.append(eachline);
                eachline = br.readLine();
            }
            
            // dispose all the resources after using them.
            fis.close();
            isr.close();
            br.close();
            

        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }
}
