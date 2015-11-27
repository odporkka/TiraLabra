    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.datacompressor.appfeatures.huffman;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author ode
 */
public class HuffmanVocabulary {

    private final HashMap<String, Byte> vocabulary;
    private final ArrayList<String> bitCombinations;
    private final byte[] byteArray;
    private final HashMap<Byte, Long> bytesByCount;

    public HuffmanVocabulary(byte[] byteArray) {
        this.vocabulary = new HashMap<>(256);
        this.bitCombinations = new ArrayList<>();
        this.byteArray = byteArray;
        this.bytesByCount = new HashMap<>();
        makeBitCombs();
        countBytes();
        makeVocabulary();
    }

    /**
     * Makes all possible bit combinations for 256 mark Huffman coding. Stored
     * as Strings in ArrayList. Each String represents one "leaf" of
     * Huffman-tree.
     */
    private void makeBitCombs() {
        String bitString = "";
        for (int i = 0; i < 127; i++) {
            int j;
            for (j = 0; j <= i - 1; j++) {
                bitString += 1;
            }
            bitString += "00";
            bitCombinations.add(bitString);
            bitString = bitString.substring(0, j);
            bitString += "01";
            bitCombinations.add(bitString);
            bitString = "";
        }

        for (int i = 0; i < 126; i++) {
            bitString += 1;
        }
        bitString += 10;
        bitCombinations.add(bitString);
        bitString = bitString.substring(0, 126);
        bitString += 11;
        bitCombinations.add(bitString);
    }

    /**
     * Counts all bytes in byteArray and puts them in HashMap.
     */
    private void countBytes() {
        for (byte b : byteArray) {
            if (bytesByCount.containsKey(b)) {
                long n = bytesByCount.get(b);
                n++;
                bytesByCount.remove(b);
                bytesByCount.put(b, n);
            } else {
                bytesByCount.put(b, (long) 1);
            }
        }
    }

    /**
     * Test method for countBytes.
     */
    private void printCount() {
        Set<Byte> all = bytesByCount.keySet();
        for (Byte c : all) {
            System.out.println(c + " = " + bytesByCount.get(c));
        }
    }

    /**
     * Method for making vocabulary for current read file (byteArray).
     */
    private void makeVocabulary() {
        for (String bitCombination : bitCombinations) {
            if (bytesByCount.isEmpty()) {
                break;
            }
            Byte b = findMostCommonByte();
            vocabulary.put(bitCombination, b);
            bytesByCount.remove(b);
        }
    }

    /**
     * Test method for printing vocabulary.
     */
    private void printVocabulary() {
        Set<String> keySet = vocabulary.keySet();
        for (String key : keySet) {
            System.out.println(vocabulary.get(key) + ": " + key);
        }
    }

    /**
     * Finds most common byte in bytesByCount. Used when making vocabulary.
     *
     * @return Byte b
     */
    private Byte findMostCommonByte() {
        Byte b;
        Map.Entry<Byte, Long> max = null;
        for (Map.Entry<Byte, Long> entry : bytesByCount.entrySet()) {
            if (max == null
                    || entry.getValue().compareTo(max.getValue()) > 0) {
                max = entry;
            }
        }
        b = max.getKey();
        return b;
    }
    
    public Byte check(BitSet bs){
        Byte b = null;
        String s = "";
        for (int i = 0; i < bs.size(); i++) {
            if (bs.get(i) == true) s+= 1;
            else s+=0;     
        }
        return b;
    }
}
