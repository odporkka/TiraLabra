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
public class HuffmanDictionary {

    private HashMap<String, Byte> dictionary;
    private final HashMap<Byte, String> dictionaryByByte;
    private final ArrayList<String> bitCombinations;
    private final byte[] byteArray;
    private final HashMap<Byte, Long> bytesByCount;

    public HuffmanDictionary(byte[] byteArray) {
        this.dictionary = new HashMap<>(256);
        this.dictionaryByByte = new HashMap<>(256);
        this.bitCombinations = new ArrayList<>();
        this.byteArray = byteArray;
        this.bytesByCount = new HashMap<>();
        makeBitCombs();
        countBytes();
        makeDictionary();
    }
    
    public HuffmanDictionary(HashMap<String, Byte> dict, byte [] byteArray){
        this.dictionary = dict;
        this.dictionaryByByte = new HashMap<>(256);
        this.bitCombinations = new ArrayList<>();
        this.byteArray = byteArray;
        this.bytesByCount = new HashMap<>();
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
     * Method for making dictionary for current read file (byteArray).
     */
    private void makeDictionary() {
        System.out.println("Making dictionary...");
        for (String bitCombination : bitCombinations) {
            if (bytesByCount.isEmpty()) {
                break;
            }
            Byte b = findMostCommonByte();
            dictionary.put(bitCombination, b);
            dictionaryByByte.put(b, bitCombination);
            bytesByCount.remove(b);
        }
        System.out.println("Done!");
    }

    /**
     * Test method for printing dictionary.
     */
    public void printDictionary() {
        Set<String> keySet = dictionary.keySet();
        for (String key : keySet) {
            System.out.println(dictionary.get(key) + ": " + key);
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

    public HashMap<Byte, String> getDictionaryByByte() {
        return dictionaryByByte;
    }
    
    public String getDictionaryAsString(){
        if (dictionary==null){
            return null;
        }
        String s = "HUFF:\n";
        Set<String> keySet = this.dictionary.keySet();
        for (String key : keySet) {
            s+=key;
            s+=" ";
            s+=this.dictionary.get(key);
            s+="\n";
        }
        s+="END\n";
        return s;
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

    public void setDictionary(HashMap<String, Byte> dict) {
        this.dictionary = dict;
    }

    public void extract(String byteString) {
        System.out.println(byteString);
        String current = "";
        String twoLast = "";
        for (int i = 0; i < byteString.length(); i++) {
            current += byteString.charAt(i);
            if (current.length() >=2){
                twoLast = current.substring(current.length()-2, current.length());
            }
            if (twoLast.equals("00") ||  twoLast.equals("01")){           
                System.out.println(this.dictionary.get(current));
                current = "";
                twoLast = "";
            }
        }
    }
}
