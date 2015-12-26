package tiralabra.datacompressor.appfeatures.huffman;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Scanner;
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
        String header = this.dictionary.getDictionaryAsString();
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
        HashMap<String, Byte> dict = new HashMap<>();
        while (true) {
            String key = scanner.next();
            if (key.equals("END")) {
                int index = fileAsString.indexOf(key);
                index += 4;
                fileAsString = fileAsString.substring(index);
                System.out.println(fileAsString);
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
                byteString = this.bh.getByteString(buffer);
                bufferIndex = 0;
                this.dictionary.extract(byteString);
                for (int j = 0; j < buffer.length; j++) {
                    buffer[j] = 0;
                }
            }
            
            buffer[bufferIndex] = byteArray[i];
            bufferIndex++;
        }

        byteString = this.bh.getByteString(buffer);
        this.dictionary.extract(byteString);
    }
}
