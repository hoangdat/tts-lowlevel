

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jh
 */
import UnitSelection.UnitSelection;
import java.io.File;
import java.io.FileNotFoundException;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author thaodv_bkit
 */
public class UnitConcatJNI {   
    String[] fileNames;
    private UnitSelection unitSelection;
    public native void UnitConcatenative(String fileLocation, String nameOfWavFile);

    static {
        System.loadLibrary("LowLevelJNI");
    }

    public UnitConcatJNI() throws XMLStreamException, FileNotFoundException {

    }
    public UnitConcatJNI(File inputXMLFile) throws XMLStreamException, FileNotFoundException {
        unitSelection = new UnitSelection(inputXMLFile);
        this.UnitConcatenative(unitSelection.getPathFile(), inputXMLFile.getName());
    }

    public static void main(String[] args) throws XMLStreamException, FileNotFoundException {
        String nameOfWavFile = "nameOfWavFile";
        UnitConcatJNI unitConcatJNI = new UnitConcatJNI();
        UnitSelection unitSelection = new UnitSelection();
        unitConcatJNI.UnitConcatenative(unitSelection.getPathFile(),nameOfWavFile);
        System.out.println("hichic");
    }

    /**
     * @return the unitSelection
     */
    public UnitSelection getUnitSelection() {
        return unitSelection;
    }
}