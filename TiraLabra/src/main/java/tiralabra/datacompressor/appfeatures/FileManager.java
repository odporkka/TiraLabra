package tiralabra.datacompressor.appfeatures;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import tiralabra.datacompressor.appfeatures.huffman.HuffmanCompressor;

/**
 * Class for file capsuling and conversion managing.
 *
 */
public class FileManager {

    public String fileLabel;
    private File curFile;
    private String fileAsString;
    private byte[] fileAsByteArray;
    public boolean error;
    private final HuffmanCompressor huffmanCompressor;
    private final ByteHandler bh;

    /**
     * Class constructor. Sets fileLabel to value shown when program is first
     * started.
     */
    public FileManager() {
        this.fileLabel = "File not selected!";
        this.error = false;
        this.bh = new ByteHandler();
        this.huffmanCompressor = new HuffmanCompressor(bh);

    }

    //Setters and getters
    public void setFile(File f) {
        this.curFile = f;
        String filepath = f.getAbsolutePath();
        this.fileLabel = filepath;
        System.out.println("File changed to: " + this.fileLabel);
        String ext = filepath.substring(filepath.length() - 4, filepath.length());
        this.bh.setFileExt(ext);
        readFile();
    }

    public File getFile() {
        if (curFile != null) {
            return curFile;
        }
        return null;
    }

    /**
     * Returns path to current capsuled file.
     *
     * @return String object for file path or fileLabel message if none is set.
     */
    public String getPath() {
        if (curFile != null) {
            return curFile.getAbsolutePath();
        }
        return this.fileLabel;
    }
    
    /**
     * Main method for extracting file. First tries to detect coding and if 
     * recognized calls matching class for decompressing.
     * 
     * @return True if coding is detected, false otherwise.
     */
    public boolean extract() {
        //checks if file is set
        if (this.curFile == null) {
            this.fileLabel = "No file set!";
            this.error = true;
            return false;
        }
        
        //checks coding and calls class according of it for extracting
        System.out.println("Checking coding for: " + this.fileLabel);
        String coding = this.fileAsString.substring(0, 4);
        if (coding.equals("HUFF")) {
            System.out.println("File " + this.fileLabel + " coded in Huffman...");
            this.huffmanCompressor.setByteArray(fileAsByteArray);
            this.huffmanCompressor.readDictionary(this.fileAsString);
            System.out.println("Extracting file...");
            this.huffmanCompressor.extractByteArray();
            System.out.println("Done!");
            return true;
        } else {
            this.fileLabel = "Could not detect coding!";
            this.error = true;
            return false;
        }
    }

    /**
     * Method for Huffman compressing and extracting.
     *
     * @return Boolean t, True if succeeded and false if not
     */
    public boolean huffmanCompress() {
        if (this.fileAsByteArray == null) {
            this.fileLabel = "Set file first!";
            this.error = true;
            return false;
        }
        huffmanCompressor.setByteArray(this.fileAsByteArray);
        huffmanCompressor.compress();
        return true;
    }

    /**
     * Private method for converting file to String object. First converts file
     * to byte array and then makes string object in ISO Latin 1 format out of
     * it (first 20.000 bytes). Stores made string in fileAsString variable.
     * Also prints first 100 characters of string in console.
     */
    private void readFile() {
        //read file to byte array
        try {
            this.fileAsByteArray = convertFileToByteArray();
        } catch (FileNotFoundException ex) {
            System.out.println("Could not read file to byte array!");
            this.error = true;
            this.fileLabel = "Could not read file!";
            return;
        }

        //test print byte[]
        printByteArray(this.fileAsByteArray);
        //convert byte array to string, some characters will be unknown
        //if format is different
        String s = new String(this.fileAsByteArray, 0, 20000,
                StandardCharsets.ISO_8859_1);

        if (s.length() > 100) {
            System.out.println("File starting with: \n" + s.substring(0, 100));
        } else {
            System.out.println(s);
        }
        System.out.println("-----\n");
        this.error = false;
        this.fileAsString = s;
    }

    /**
     * Private method for converting file to byte array. Prints size of made
     * array to console. Uses byte[] buffer and input/output streams to do that.
     * May cause program to run out of memory if file is big.
     *
     * @return byte[] object of file
     * @throws FileNotFoundException if curFile is invalid or not set
     */
    private byte[] convertFileToByteArray() throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(curFile);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int sum = 0;
        try {
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                //Writes to this byte array output stream
                bos.write(buf, 0, readNum);
                sum += readNum;
            }
        } catch (IOException ex) {
            return null;
        }
        System.out.println("Bytes read: " + (sum / 1000 * 1.0) + " kb");
        byte[] byteArray = bos.toByteArray();
        return byteArray;
    }

    /**
     * Prints current file. Used for testing purposes.
     *
     * @throws Exception if file is null and not set.
     */
    private void printCurFile() {
        try {
            Scanner s = new Scanner(curFile);
            while (s.hasNextLine()) {
                System.out.println(s.nextLine());
            }
        } catch (FileNotFoundException ex) {
        }

    }

    /**
     * Prints bytes in array. For testing purposes only.
     *
     * @param array Byte array
     */
    private void printByteArray(byte[] array) {
        int n = 0;
        for (byte b : array) {
            System.out.print((String.format("%4d", b)) + " ");
            n++;
            if (n % 4 == 0) {
                System.out.print("  ");
            }
            if (n % 8 == 0) {
                System.out.print("\n");
            }
            if (n == 80) {
                break;
            }
        }
    }
}
