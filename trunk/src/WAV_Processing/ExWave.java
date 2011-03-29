/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WAV_Processing;

/**
 *
 * @author thaodv-bkit
 */
// Don't forget to save AePlayWave source to AePlayWave.java
// and compile it
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class ExWave {

    public static void main(String[] args) throws MalformedURLException {
        String rss_url = "http://rss.news.yahoo.com/rss/world";
        // Yahoo! News RSS -- default
        if (args.length > 0) {
            rss_url = args[0]; // or command line argument
        }
        int prevLen = -1;

        while (true) {
            String result = "";

            URL url = new URL(rss_url);
            InputStream in;

            try {
                System.out.println("Checking RSS feed " + rss_url);
                in = url.openStream();
                BufferedReader ureader = new BufferedReader(new InputStreamReader(in));

                int length = 0;
                String str;
                // Downloading and computing length
                while ((str = ureader.readLine()) != null) {
                    // This condition removes lines which contains RSS comments
                    // Yahoo News RSS feed contains some technical information
                    // in RSS comments
                    if (str.indexOf("<!--") != -1) {
                        continue;
                    }
                    length += str.length();
                }

                System.out.println("Length: " + String.valueOf(length) + " characters");
                if (prevLen != -1) { // first time, prevLen == -1, can't compare with previous value
                    if (prevLen != length) {
                        System.out.println("Length changed!");
                        result = "change";
                    } else {
                        result = "nochange";
                        System.out.println("No change");
                    }
                }

                prevLen = length;
                in.close();
            } catch (IOException e1) {
                System.out.println("Error");
                result = "error";
            }

            if (result != "") {
                new AePlayWave(result + ".wav").start(); // playing WAVE file
            }
            int sleep_sec = 120 + (int) (Math.random() * 60);
            System.out.println("Sleeping for " + String.valueOf(sleep_sec) + " seconds");

            try {
                Thread.sleep(sleep_sec * 1000);
            } // Sleeping
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
