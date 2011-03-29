/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WAV_Processing;

import com.sun.media.sound.WaveFileReader;
import com.sun.media.sound.WaveFileWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.Buffer;
import javax.print.DocFlavor.INPUT_STREAM;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author thaodv-bkit
 */
public class WAV_Processing extends WaveFileReader{
    AudioFileFormat audioFileFormat;
    AudioInputStream audioInputStream;
    FileOutputStream fileOutStrm;
    public WAV_Processing(File wfile,int offset, int len) throws UnsupportedAudioFileException, IOException{
        ////////////////////////////////////////////////////////////////////////
        //read file
        audioFileFormat = getAudioFileFormat(wfile);
        audioInputStream = getAudioInputStream(wfile);
        int byteLength = audioFileFormat.getByteLength();
        byte[] buffer = new byte[byteLength];
        
        int framesRead = audioInputStream.read(buffer, offset, byteLength);
        System.out.println("read: "+framesRead);
        ////////////////////////////////////////////////////////////////////////
        //WaveFileWriter waveFileWriter = new WaveFileWriter();
        //waveFileWriter.write(audioInputStream, AudioFileFormat.Type.WAVE, new File("2_"+wfile.getName()));
        fileOutStrm = new FileOutputStream(new File("out_"+wfile.getName()), false);
        fileOutStrm.write(buffer, offset, byteLength);
        ///////////////////////
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer);
        AudioInputStream audioInputStream2 = new AudioInputStream(,audioFileFormat.getFormat(), len);
        

    }
    ////////////////////////////////////////////////////////////////////////////


}
