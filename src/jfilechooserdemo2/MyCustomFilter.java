/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jfilechooserdemo2;

import java.io.File;

/**
 *
 * @author thaodv
 */
class MyCustomFilter extends javax.swing.filechooser.FileFilter {

        @Override
        public boolean accept(File file) {
            // Allow just directories and files with ".txt" extension...
            return file.isDirectory() || file.getAbsolutePath().endsWith(".txt")|| file.getAbsolutePath().endsWith(".xml");
        }

        @Override
        public String getDescription() {
            // This description will be displayed in the dialog,
            // hard-coded = ugly, should be done via I18N
            return "Text documents (*.txt, *.xml)";
        }

    }

