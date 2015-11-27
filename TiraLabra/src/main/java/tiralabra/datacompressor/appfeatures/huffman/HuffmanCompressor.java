package tiralabra.datacompressor.appfeatures.huffman;

import java.io.File;

public class HuffmanCompressor {
    
    private byte[] byteArray;
    private HuffmanVocabulary voc;

    public HuffmanCompressor() {
    }

    public void setByteArray(byte[] byteArray) {
        this.byteArray = byteArray;
    }
    
    public File compress(){
        File result = null;
        this.voc = new HuffmanVocabulary(byteArray);
        return result;   
    }
}
