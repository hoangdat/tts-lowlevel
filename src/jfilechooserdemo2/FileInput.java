package jfilechooserdemo2;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author thaodv
 */
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This program reads a text file line by line and print to the console. It uses
 * FileOutputStream to read the file.
 *
 */
public class FileInput {

    public FileInput(File file) {


        FileInputStream fis = null;
        BufferedInputStream bis = null;
        DataInputStream dis = null;

        try {
            fis = new FileInputStream(file);

            // Here BufferedInputStream is added for fast reading.
            bis = new BufferedInputStream(fis);
            dis = new DataInputStream(bis);
            //System.out.println(dis.readByte());
            // dis.available() returns 0 if the file does not have more lines.
//      while (dis.available() != 0) {
//
//      // this statement reads the line from the file and print it to
//        // the console.
//       // System.out.println(dis.readUTF());
//          String str = dis.readUTF();
//          System.out.println(str);
//          System.out.println("hehe");
//      }
            System.out.println("hehe");
            try {
                System.out.println(dis.readUTF());
            } catch (Exception e) {
                System.out.println(e.toString());
            }


            System.out.println("hehe");

            // dispose all the resources after using them.
            fis.close();
            bis.close();
            dis.close();

        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }
}
