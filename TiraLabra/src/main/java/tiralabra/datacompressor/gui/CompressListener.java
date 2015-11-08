package tiralabra.datacompressor.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import tiralabra.datacompressor.appfeatures.FileManager;

/**
 *
 * @author ode
 */
public class CompressListener implements ActionListener{
    FileManager fmgr;
    JComboBox cb;

    public CompressListener(FileManager fmgr, JComboBox cb) {
        this.fmgr = fmgr;
        this.cb = cb;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println("Starting compressing in: "+cb.getSelectedItem());
       }
    
}
