package tiralabra.datacompressor.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import tiralabra.datacompressor.appfeatures.FileManager;

/**
 * Extract button listener.
 */
public class ExtractListener implements ActionListener {

    FileManager fmgr;
    JLabel fileLabel;

    public ExtractListener(FileManager fmgr, JLabel jlbl) {
        this.fmgr = fmgr;
        this.fileLabel = jlbl;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!this.fmgr.extract()){
            this.fileLabel.setText(this.fmgr.fileLabel);
            this.fileLabel.setForeground(Color.red);
        }       
    }
}
