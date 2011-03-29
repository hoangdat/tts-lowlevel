package WavFile;

import java.io.*;
import javax.sound.sampled.*;

public class ReadWavExample {
    AudioInputStream auIS;

    public ReadWavExample(File file) {
        try {
            // Open the wav file specified as the first argument
            WavFile wavFile = WavFile.openWavFile(file);
            auIS = AudioSystem.getAudioInputStream(file);

            // Display information about the wav file
            wavFile.display();

            // Get the number of audio channels in the wav file
            int numChannels = wavFile.getNumChannels();
            long numFrames = wavFile.getNumFrames();
            System.out.println("numFrames: " + numFrames);

            // Create a buffer of 100 frames
            //double[] buffer = new double[100 * numChannels];
            byte[] buffer = new byte[(int) numFrames * numChannels];


            int framesRead;
            double min = Double.MAX_VALUE;
            double max = Double.MIN_VALUE;
//            int offset= 0;
//            int numFramesReaded = wavFile.readFrames(buffer, offset, (int)numFrames);
//            WavFile wavFile2 = WavFile.newWavFile(new File("WriteExample1.wav"), 2, wavFile.getNumFrames(), 16, wavFile.getSampleRate());
//            wavFile2.writeFrames(buffer, offset, numFramesReaded);
//            wavFile2.close();

            ////////////////////////////////////////////////////////////////////
            do {
                // Read frames into buffer
               // framesRead = wavFile.readFrames(buffer, 100);
                framesRead = auIS.read(buffer, 0, (int)numFrames);

                // Loop through frames and look for minimum and maximum value
                for (int s = 0; s < framesRead * numChannels; s++) {
                    if (buffer[s] > max) {
                        max = buffer[s];
                    }
                    if (buffer[s] < min) {
                        min = buffer[s];
                    }
                }
            } while (framesRead != 0);
            // Close the wavFile
            wavFile.close();
            ////////////////////////////////////////////////////////////////////
            System.out.println("buffer.length: " + buffer.length);
            ////////////////////////////////////////////////////////////////////
            WavFile wavFile2 = WavFile.newWavFile(new File("WriteExample1.wav"), wavFile.getNumChannels(), numFrames, wavFile.getValidBits(), wavFile.getSampleRate());
            int offset = 0;
            int nChannels = wavFile.getNumChannels();

            long frameCounter = 0;
            double[] bufferToWrite = new double[100*nChannels];

            // Loop until all frames written
            while (frameCounter < numFrames) {
                // Determine how many frames to write, up to a maximum of the buffer size
                long remaining = wavFile2.getFramesRemaining();
                int toWrite = (remaining > 100) ? 100 : (int) remaining;
                for (int s = 0; s < toWrite; s++, frameCounter++) {
                    bufferToWrite[s] = buffer[offset + s];
                    System.out.println(s);
                }
                try {
                    wavFile2.writeFrames(bufferToWrite,  toWrite);
                    // Write the buffer
                } catch (Exception e) {
                    System.err.println("this: " + e);
                }
                offset += toWrite;
                System.out.println("offset: " + offset + " " + frameCounter);
            }
            // Close the wavFile
            wavFile2.close();
            ///////////////////////////////////////////////////////////////////
            // Output the minimum and maximum value
            System.out.printf("Min: %f, Max: %f\n", min, max);
        } catch (Exception e) {
            System.err.println(e);
        }
    }
/////////////////////////////////////////////////////////////
    public void writeWav(){

    }
//	public static void main(String[] args)
//	{
//
//	}
}
