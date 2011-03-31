/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WAV_Processing;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFileFormat.Type;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.tritonus.share.sampled.AudioSystemShadow;
import org.tritonus.share.sampled.file.AudioOutputStream;
import org.tritonus.share.sampled.file.TDataOutputStream;
import org.tritonus.share.sampled.file.TSeekableDataOutputStream;

/**
 *
 * @author thaodv-bkit
 */
public class TestAOS {

    AudioInputStream audioInputStream;
    AudioFileFormat audioFileFormat;
    AudioFormat audioFormat;
    private static final int BUFFER_SIZE = 128000;
    private static boolean DEBUG = false;

    public TestAOS(File wavFile) throws UnsupportedAudioFileException, IOException {
        audioInputStream = AudioSystem.getAudioInputStream(wavFile);
        audioFileFormat = AudioSystem.getAudioFileFormat(wavFile);

        audioFormat = audioFileFormat.getFormat();
        Type type = audioFileFormat.getType();
        float fAmplitude = 0.7F;
        float fSampleRate = audioFormat.getSampleRate();
        int channels = audioFormat.getChannels();
        float frameRate = audioFormat.getFrameRate();
        int frameSize = audioFormat.getFrameSize();
        int sampleSizeInBits = audioFormat.getSampleSizeInBits();
        long frameLength = audioInputStream.getFrameLength();
        long lLengthInBytes = AudioSystem.NOT_SPECIFIED;
        if (frameLength != AudioSystem.NOT_SPECIFIED) {
            lLengthInBytes = frameLength * frameSize;
        }

        ////////////////////////////////////////////////////////////////////////TDataOutputStream
        File outputFile = new File("TestAOS" + wavFile.getName());
        TDataOutputStream dataOutputStream = null;
        dataOutputStream = new TSeekableDataOutputStream(outputFile);
        dataOutputStream = AudioSystemShadow.getDataOutputStream(outputFile);
        ////////////////////////////////////////////////////////////////////////AudioOutputStream

        /*
         *	Ok, finally the line is prepared. Now comes the real
         *	job: we have to write data to the line. We do this
         *	in a loop. First, we read data from the
         *	AudioInputStream to a buffer. Then, we write from
         *	this buffer to the Line. This is done until the end
         *	of the file is reached, which is detected by a
         *	return value of -1 from the read method of the
         *	AudioInputStream.
         */
        int nBytesRead = 0;
        int nBytesHaveToRead = 120000;
        byte[] abData = new byte[BUFFER_SIZE];
        if (nBytesHaveToRead >= BUFFER_SIZE) {
            System.out.println(">=");
            AudioOutputStream audioOutputStream = null;
            while (nBytesRead != -1) {
                if (DEBUG) {
                    System.out.println("OscillatorFileAOS.main(): trying to read (bytes): " + abData.length);
                }
                nBytesRead = audioInputStream.read(abData, 0, abData.length);
                if (DEBUG) {
                    System.out.println("OscillatorFileAOS.main(): read (bytes): " + nBytesRead);
                }

                audioOutputStream = AudioSystemShadow.getAudioOutputStream(
                        type,
                        audioFormat,
                        lLengthInBytes,
                        dataOutputStream);
                ////////////////////////////////////////////////////////////////////////
                if (nBytesRead >= 0) {
                    int nBytesWritten = audioOutputStream.write(abData, 0, nBytesRead);
                    if (DEBUG) {
                        System.out.println("OscillatorFileAOS.main(): written: " + nBytesWritten);
                    }
                }
            }
            audioOutputStream.close();
        } else {
            System.out.println("<");
            if (DEBUG) {
                System.out.println("OscillatorFileAOS.main(): trying to read (bytes): " + abData.length);
            }
            nBytesRead = audioInputStream.read(abData, 0, nBytesHaveToRead);
            if (DEBUG) {
                System.out.println("OscillatorFileAOS.main(): read (bytes): " + nBytesRead);
            }
            ////////////////////////////////////////////////////////////////////
            ArrayList arrayListByte= new ArrayList();
            for (int i = nBytesRead / 3; i < nBytesRead / 2; i++) {
                arrayListByte.add(abData[i]);
            }
            lLengthInBytes = arrayListByte.size();
            byte[] byteArrToWrite = new byte[(int) lLengthInBytes];             
            for (int i = 0; i < byteArrToWrite.length; i++) {
                byteArrToWrite[i] = ((Byte)(arrayListByte.get(i))).byteValue();

            }
           ////////////////////////////////////////////////////////////////////
            AudioOutputStream audioOutputStream = null;
            audioOutputStream = AudioSystemShadow.getAudioOutputStream(
                    type,
                    audioFormat,
                    lLengthInBytes,
                    dataOutputStream);
            ////////////////////////////////////////////////////////////////////////
            if (nBytesRead >= 0) {
                //int nBytesWritten = audioOutputStream.write(abData,0, (int)nBytesRead/2);
                int nBytesWritten = audioOutputStream.write(byteArrToWrite, 0, (int)lLengthInBytes);
                // ghi tu vi tri offset cua buffer, so byte la len
                if (DEBUG) {
                    System.out.println("OscillatorFileAOS.main(): written: " + nBytesWritten);
                }
            }
            audioOutputStream.close();
        }



        /*
         *	All data are transfered. We can close the shop.
         *	Note that this is important to do backpatching of the
         *	header, if possible.
         */




    }
}
