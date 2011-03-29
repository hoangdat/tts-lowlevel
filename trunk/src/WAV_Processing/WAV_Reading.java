/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WAV_Processing;

import WavFile.WavFile;
import WavFile.WavFileException;
import java.io.File;
import com.sun.media.sound.*;
import java.io.IOException;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioFormat.Encoding;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author thaodv-bkit
 */
public class WAV_Reading extends WaveFileReader {

    AudioFileFormat auFileFormat;
    AudioInputStream auIS;
    byte[] buffer;
    SourceDataLine srcDataLine;

    public WAV_Reading(File wavFile) throws UnsupportedAudioFileException, IOException, LineUnavailableException, WavFileException {
        auIS = AudioSystem.getAudioInputStream(wavFile);
        auFileFormat = getAudioFileFormat(wavFile);
        WavFile waveFile = WavFile.openWavFile(wavFile);

        int byteLength = auFileFormat.getByteLength();
        AudioFormat format = auFileFormat.getFormat();
        int channel = format.getChannels();
        Encoding encoding = format.getEncoding();
        float frameRate = format.getFrameRate();
        int frameSize = format.getFrameSize();
        float sampleRate = format.getSampleRate();
        int sampleSizeInBits = format.getSampleSizeInBits();
        System.out.println(byteLength);
        buffer = new byte[byteLength];
//        double r = byteLength / frameLength;
//        double lengthInSec;
//        lengthInSec = byteLength * 8 / 256 / 1000; // 256 kbps
//        System.out.println("FrameLength: " + frameLength + " " + byteLength + " " + r + " " + lengthInSec);

        auIS.read(buffer, 0, byteLength);

        ////////////////////////////////////////////////////////////////////////
        int byteWritten = AudioSystem.write(auIS, AudioFileFormat.Type.WAVE, new File("2_"+wavFile.getName()));
        //System.out.println("byteWritten: " + byteWritten);
        ////////////////////////////////////////////////////////////////////////
      
      
        

        
      
        


    }
}
