/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WAV_Processing;

import java.io.File;
import com.sun.media.sound.*;
import java.io.IOException;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author thaodv-bkit
 */
public class WAV_Reading extends WaveFileReader {

    AudioFileFormat auFileFormat;
    AudioInputStream auIS;

    public WAV_Reading(File wavFile) throws UnsupportedAudioFileException, IOException {
        auFileFormat = getAudioFileFormat(wavFile);
        int frameLength = auFileFormat.getFrameLength();
        int byteLength = auFileFormat.getByteLength();
        double r = byteLength / frameLength;
        double lengthInSec;
        lengthInSec = byteLength * 8 / 256 / 1000; // 256 kbps
        System.out.println("FrameLength: " + frameLength + " " + byteLength + " " + r + " " + lengthInSec);

    }
}
