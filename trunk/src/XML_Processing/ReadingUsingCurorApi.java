/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package XML_Processing;

/**
 *
 * @author thaodv_bkit
 */
import java.io.*;
import javax.xml.stream.*;
import javax.xml.stream.events.*;

public class ReadingUsingCurorApi {

    private XMLInputFactory inputFactory = null;
    private XMLStreamReader xmlReader = null;

    public ReadingUsingCurorApi() {
        inputFactory = XMLInputFactory.newInstance();
    }

    public void read() throws Exception{

        xmlReader = inputFactory.createXMLStreamReader(
            new FileReader(".\\src\\myCalendar.xml"));

        while (xmlReader.hasNext()){

            Integer eventType = xmlReader.next();
            if (eventType.equals(XMLEvent.START_ELEMENT)){
                System.out.print(" " + xmlReader.getName() + " ");
            }else if (eventType.equals(XMLEvent.CHARACTERS)){
                System.out.print(" " + xmlReader.getText() + " ");
            }else if (eventType.equals(XMLEvent.ATTRIBUTE)){
                System.out.print(" " + xmlReader.getName() + " ");
            }else if (eventType.equals(XMLEvent.END_ELEMENT)){
                System.out.print(" " + xmlReader.getName() + " ");
            }
        }
        xmlReader.close();
    }

    public static void main(String args[]){
        try{
            ReadingUsingCurorApi obj = new ReadingUsingCurorApi();
            obj.read();
        }catch(Exception exception){
            exception.printStackTrace();
        }
    }
}

