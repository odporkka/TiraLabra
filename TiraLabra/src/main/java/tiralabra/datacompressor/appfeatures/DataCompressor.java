package tiralabra.datacompressor.appfeatures;

import javax.swing.SwingUtilities;
import tiralabra.datacompressor.gui.MainWindow;

/**
 * Data compressor/decompresser started as school project in fall 2015. 
 * Course = Data structures and algorithms lab exercise, 
 * University of Helsinki, Department of Computer Science.
 * @author Otto Porkka <otto.porkka@helsinki.fi>
 * @version 1.0
 * @since 25.10.2015
 */
public class DataCompressor {

    public static void main(String[] args) {

        System.out.println("Data compressing and decompressing program by Ode. v1.0");
        FileManager fmgr = new FileManager();
        MainWindow mw = new MainWindow(fmgr);
        SwingUtilities.invokeLater(mw);

    }

}
