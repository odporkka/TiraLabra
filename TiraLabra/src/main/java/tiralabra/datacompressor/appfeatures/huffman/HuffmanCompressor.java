package tiralabra.datacompressor.appfeatures.huffman;

import java.io.File;
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
    
    public File compress(){
        File result = null;
        this.dictionary = new HuffmanDictionary(byteArray);
        this.bh.setInputArray(byteArray);
        this.bh.setCurrentDictionary(this.dictionary.getDictionaryByByte());
        this.bh.writeOutput();
        return result;   
    }
}
