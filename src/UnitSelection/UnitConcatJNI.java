package UnitSelection;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jh
 */
import java.io.FileNotFoundException;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author thaodv_bkit
 */
public class UnitConcatJNI {

   
    String[] fileNames;
    public native void UnitConcatenative(String fileLocation);

    static {
        System.loadLibrary("LowLevelJNI");
    }

    public UnitConcatJNI() throws XMLStreamException, FileNotFoundException {

    }

    public static void main(String[] args) throws XMLStreamException, FileNotFoundException {
        UnitConcatJNI unitConcatJNI = new UnitConcatJNI();
        UnitSelection us = new UnitSelection();
        System.out.println("PathFile: "+us.getPathFile());
        unitConcatJNI.UnitConcatenative(us.getPathFile());
    }
}