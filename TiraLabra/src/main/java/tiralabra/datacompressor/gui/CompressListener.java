package tiralabra.datacompressor.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import tiralabra.datacompressor.appfeatures.FileManager;

/**
 * Compress button listener.
 */
public class CompressListener implements ActionListener {

    FileManager fmgr;
    JComboBox cb;
    JLabel fileLabel;

    public CompressListener(FileManager fmgr, JComboBox cb, JLabel jlbl) {
        this.fmgr = fmgr;
        this.cb = cb;
        this.fileLabel = jlbl;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println("Starting compressing in: " + cb.getSelectedItem());
        if (cb.getSelectedItem().equals("Huffman")) {
            if (!fmgr.huffmanCompress()){
                this.fileLabel.setText(this.fmgr.fileLabel);
                this.fileLabel.setForeground(Color.red);
            }
        }
    }
}
