package tiralabra.datacompressor.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import tiralabra.datacompressor.appfeatures.FileManager;

/**
 * Action listener for choosing file button.
 */
public class SelectFileListener implements ActionListener{
    Container parentComponent;
    private final JFileChooser fc;
    FileManager fmgr;
    JLabel fileLabel;

    /**
     * Class constructor.
     * @param parentComponent Container that is being refreshed after action.
     * @param fmgr File manager containing file and file path for current file.
     * @param l Label in container showing current file or message.
     */
    public SelectFileListener(Container parentComponent, FileManager fmgr, JLabel l) {
        this.parentComponent = parentComponent;
        this.fc = new JFileChooser();
        this.fmgr = fmgr;
        this.fileLabel = l;
    }

    /**
     * File choosing method. Opens Java File Chooser and gives file to FileManager -
     * object. Then refreshes parent container and label for current file.
     * 
     * @param ae 
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        int returnVal = fc.showOpenDialog((Component)parentComponent);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            //this.fmgr.setFile(fc.getSelectedFile().getAbsolutePath());
            this.fileLabel.setText("Reading file...");
            parentComponent.revalidate();
            parentComponent.repaint();
            this.fmgr.setFile(fc.getSelectedFile());
            this.fileLabel.setForeground(Color.BLACK);
            if (this.fmgr.error == true) this.fileLabel.setForeground(Color.red);
        } else {
            if (this.fmgr.getFile() == null){
                this.fileLabel.setForeground(Color.red);
            }
        }
        this.fileLabel.setText(this.fmgr.fileLabel);
        parentComponent.revalidate();
        parentComponent.repaint();
    }
    
}
