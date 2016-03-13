package tiralabra.datacompressor.appfeatures.huffman;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import tiralabra.datacompressor.appfeatures.ByteHandler;
import tiralabra.datacompressor.datastructures.CustomHashMap;

/**
 * Class that handles Huffman compressing.
 */
public class HuffmanCompressor {

    private byte[] byteArray;
    private HuffmanDictionary dictionary;
    private final ByteHandler bh; 

    public HuffmanCompressor(ByteHandler bh) {
        this.bh = bh;
    }

    public void setByteArray(byte[] byteArray) {
        this.byteArray = byteArray;
    }

    /**
     * Method for compressing. Makes new dictionary for current file. Then
     * passes made dictionary to ByteHandler class for file writing and coding.
     * Dictionary is passed as string named header.
     */
    public void compress() {
        this.dictionary = new HuffmanDictionary(byteArray);
        this.bh.setInputArray(byteArray);
        this.bh.setCurrentDictionary(this.dictionary.getDictionaryByByte());
        System.out.println("Making header...");
        String header = "HUFF: "+this.bh.getFileExt()+"\n";
        header = this.dictionary.getHeaderString(header);
        this.bh.writePackedFile(header);
    }

    /**
     * Method for reading dictionary header off from compressed file. Only
     * called when header is recognized. Changes byteArray to file without
     * header for extracting.
     *
     * @param fileAsString
     */
    public void readDictionary(String fileAsString) {
        Scanner scanner = new Scanner(fileAsString);
        System.out.println("Reading dictionary in: " + scanner.next());
        String ext = scanner.next();
        System.out.println(ext);
        this.bh.setFileExt(ext);
        CustomHashMap dict = new CustomHashMap();
        int index;
        
        //reading dictionary off from start of file
        while (true) {
            String key = scanner.next();
            if (key.equals("END")) {
                index = fileAsString.indexOf(key) +4;
                break;
            }
            Byte b = new Byte(scanner.next());
            dict.put((String)key, b);
        }
        
        //stripping dictionary off from start of file
        byte[] fileAsByteArray = new byte[this.byteArray.length-index];
        for (int i = 0; i < this.byteArray.length-index; i++) {
            fileAsByteArray[i] = this.byteArray[i+index];
        }
        this.byteArray = fileAsByteArray;
        this.dictionary = new HuffmanDictionary(dict, byteArray);
    }

    /**
     * Method for extracting byteArray back to file according to current read
     * dictionary.
     */
    public void extractByteArray() {
        String byteString = "";
        int bufferIndex = 0;
        byte[] buffer = new byte[1024];
        
        for (int i = 0; i < byteArray.length; i++) {
            
            if (i % 1024 == 0 && i != 0) {
                System.out.println(i / 1024 + " kB extracted..");
                byteString += this.bh.getByteString(buffer);
                bufferIndex = 0;
                byteString = this.dictionary.extract(byteString);
                
            }
            
            buffer[bufferIndex] = byteArray[i];
            bufferIndex++;
        }

        byte[] leftover = new byte[bufferIndex];
        for (int i = 0; i < bufferIndex; i++) {
            leftover[i] = buffer[i];
        }
        
        byteString = this.bh.getByteString(leftover);
        this.dictionary.extract(byteString);
        
        int finalSize = this.dictionary.getExtractedByteArray().size();
        System.out.println("Size: "+finalSize);
        
        byte[] outputArray = new byte[finalSize];
        for (int i = 0; i < finalSize; i++) {
            outputArray[i] = (Byte)this.dictionary.getExtractedByteArray().get(i);
        }
        
        this.bh.setOutputArray(outputArray);
        try {
            this.bh.writeExtractedFile();
        } catch (IOException ex) {
            System.out.println("Could not write!");
            Logger.getLogger(HuffmanCompressor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}