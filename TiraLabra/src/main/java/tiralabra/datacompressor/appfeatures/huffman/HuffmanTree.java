package tiralabra.datacompressor.appfeatures.huffman;

import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author ode
 */
public class HuffmanTree {

    private final byte[] byteArray;
    private final HashMap<Byte, Long> bytesByCount;

    HuffmanTree(byte[] byteArray) {
        this.byteArray = byteArray;
        this.bytesByCount = new HashMap<>();
    }

    public void makeTree() {
        countBytes();
    }

    private void countBytes() {
        for (byte b : byteArray) {
            if (bytesByCount.containsKey(b)) {
                long n = bytesByCount.get(b);
                n++;
                bytesByCount.remove(b);
                bytesByCount.put(b, n);
            } else {
                bytesByCount.put(b, (long)1);
            }
        }
        printCount();
    }

    private void printCount() {
        Set<Byte> all = bytesByCount.keySet();
        for (Byte c : all) {
            System.out.println(c + " = " + bytesByCount.get(c));
        }
    }

}
