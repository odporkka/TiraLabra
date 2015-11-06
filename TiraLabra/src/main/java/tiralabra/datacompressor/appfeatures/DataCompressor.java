package tiralabra.datacompressor.appfeatures;

import javax.swing.SwingUtilities;
import tiralabra.datacompressor.gui.MainWindow;

/**
* @author       Otto Porkka <otto.porkka@helsinki.fi>
* Data compressor/decompresser made as school project in fall 2015
* Course = Data structures and algorithms lab exercise 
* 
*/

public class DataCompressor {
    
    public static void main(String[] args) {
        
        System.out.println("Data compressing and decompressing program by Ode. v1.0");
        FileManager fmgr = new FileManager();
        MainWindow mw = new MainWindow(fmgr);
        SwingUtilities.invokeLater(mw);
        
    }
    
}
