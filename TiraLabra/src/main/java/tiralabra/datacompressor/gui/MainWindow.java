package tiralabra.datacompressor.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import tiralabra.datacompressor.appfeatures.FileManager;

/**
 * Class containing JFrame and components of GUI.
 */
public class MainWindow implements Runnable {

    private JFrame frame;
    private final FileManager fmgr;

    public MainWindow(FileManager fmgr) {
        this.fmgr = fmgr;
    }
    
    /**
     * Method to run GUI.
     * Invoked in DataCompressor Main-class.
     */
    @Override
    public void run() {
        frame = new JFrame("Data Compressor v1.0 - by Ode");
        frame.setPreferredSize(new Dimension(500, 250));
        frame.setLocation(500, 250);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createComponents(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Creates components for JFrame.
     * @param contentPane 
     */
    private void createComponents(Container contentPane) {
        //Making layout and main Panel
        GridLayout gl = new GridLayout(4, 2);
        JPanel mainPanel = new JPanel(gl);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        gl.setHgap(10);
        gl.setVgap(10);
        
        //Adding all content
        JLabel currentFile = new JLabel("Current file: ");
        JLabel currentFileLabel = new JLabel(fmgr.getPath());
        SelectFileListener selectListener = new SelectFileListener(
                contentPane, this.fmgr, currentFileLabel);
        JButton changeFile = new JButton("Choose file!");
        changeFile.addActionListener(selectListener);
        JLabel currentCoding = new JLabel("Coding:");
        String[] options = {"Huffman"};
        JComboBox<String> selectCoding = new JComboBox<>(options);
        JButton deCompressButton = new JButton("Extract!");
        JButton compressButton = new JButton("Compress!");        
        
        //adding content to main panel
        mainPanel.add(currentFile);
        mainPanel.add(currentFileLabel);
        mainPanel.add(new JLabel(""));
        mainPanel.add(changeFile);
        mainPanel.add(currentCoding);
        mainPanel.add(selectCoding);
        mainPanel.add(deCompressButton);
        mainPanel.add(compressButton);
        
        //adding main panel to frame
        contentPane.add(mainPanel);   
    }

    /**
     *Returns JFrame capsuled in class.
     * @return JFrame of MainWindow
     */
    public JFrame getFrame() {
        return frame;
    }
}
