

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jh
 */
import UnitSelection.UnitSelection;
import java.io.FileNotFoundException;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author thaodv_bkit
 */
public class UnitConcatJNI {

   
    String[] fileNames;
    public native void UnitConcatenative(String fileLocation, String nameOfWavFile);

    static {
        System.loadLibrary("LowLevelJNI");
    }

    public UnitConcatJNI() throws XMLStreamException, FileNotFoundException {

    }

    public static void main(String[] args) throws XMLStreamException, FileNotFoundException {
        String nameOfWavFile = "nameOfWavFile";
        UnitConcatJNI unitConcatJNI = new UnitConcatJNI();
        UnitSelection us = new UnitSelection();
        unitConcatJNI.UnitConcatenative(us.getPathFile(),nameOfWavFile);
        System.out.println("hichic");
    }
}