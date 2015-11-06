package tiralabra.datacompressor.appfeatures;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ode
 */
public class FileManager {
    public String fileLabel;
    private File curFile;
    
    private String testi;

    public FileManager() {
        this.fileLabel = "File not selected";
    }
    
    public void setFile(File f){
        this.curFile = f;
        this.fileLabel = f.getAbsolutePath();
        System.out.println("File changed to: "+this.fileLabel);
        printCurFile();
    }
    
    public File getFile(){
        if (curFile != null) return curFile;
        return null;
    }
    
    public String getPath(){
        if (curFile != null) return curFile.getAbsolutePath();
        return this.fileLabel;
    }
    
    public void writeToFile(){
        
    }
    
    private void printCurFile(){
        try {
            Scanner s = new Scanner(curFile);
            while (s.hasNextLine()){
                System.out.println(s.nextLine());
            }
        } catch (FileNotFoundException ex) {
        }
        
    }
}
