package tiralabra.datacompressor.appfeatures.huffman;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import tiralabra.datacompressor.appfeatures.ByteHandler;

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

    public void compress() {
        this.dictionary = new HuffmanDictionary(byteArray);
        this.bh.setInputArray(byteArray);
        this.bh.setCurrentDictionary(this.dictionary.getDictionaryByByte());
        System.out.println("Making header...");
        String header = "HUFF: "+this.bh.getFileExt()+"\n";
        header = this.dictionary.getHeaderString(header);
        this.bh.writeOutput(header);
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
        HashMap<String, Byte> dict = new HashMap<>();
        while (true) {
            String key = scanner.next();
            if (key.equals("END")) {
                int index = fileAsString.indexOf(key);
                index += 4;
                fileAsString = fileAsString.substring(index);
                byteArray = fileAsString.getBytes(StandardCharsets.ISO_8859_1);
                break;
            }
            Byte b = new Byte(scanner.next());
            dict.put(key, b);
        }
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
        
        int finalSize = this.dictionary.getExtracted().size();
        System.out.println(finalSize);
        
        byte[] outputArray = new byte[finalSize];
        for (int i = 0; i < finalSize; i++) {
            outputArray[i] = this.dictionary.getExtracted().get(i);
        }
        
        this.bh.setOutputArray(outputArray);
        try {
            this.bh.writeExtracted();
        } catch (IOException ex) {
            System.out.println("Could not write!");
            Logger.getLogger(HuffmanCompressor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
