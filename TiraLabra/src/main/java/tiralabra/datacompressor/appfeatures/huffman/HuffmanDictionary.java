    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.datacompressor.appfeatures.huffman;

import tiralabra.datacompressor.datastructures.CustomArray;
import tiralabra.datacompressor.datastructures.CustomHashMap;
import tiralabra.datacompressor.datastructures.KeyValuePair;

/**
 * Class that contains Huffman dictionary for compressing and decompressing.
 */
public class HuffmanDictionary {

    private CustomHashMap dictionary;
    private final CustomHashMap dictionaryByByte;
    private final CustomArray bitCombinations;
    private byte[] byteArray;
    private final CustomHashMap bytesByCount;
    private final CustomArray extracted;

    /**
     * First constructor for dictionary. Called when packing. Takes byte[] of
     * file as parameter and then constructs dictionary from it.
     *
     * @param byteArray
     */
    public HuffmanDictionary(byte[] byteArray) {
        this.dictionary = new CustomHashMap();
        this.dictionaryByByte = new CustomHashMap();
        this.bitCombinations = new CustomArray();
        this.byteArray = byteArray;
        this.bytesByCount = new CustomHashMap();
        this.extracted = new CustomArray();
        makeBitCombs();
        countBytes();
        makeDictionary();
    }

    /**
     * Second constructor for dictionary. Called when extracting. Takes byte[]
     * of file and dictionary read from start of file as parameters.
     *
     * @param dict Dictionary stripped off from start of file
     * @param byteArray File as byte array
     */
    public HuffmanDictionary(CustomHashMap dict, byte[] byteArray) {
        this.dictionary = dict;
        this.dictionaryByByte = new CustomHashMap();
        this.bitCombinations = new CustomArray();
        this.byteArray = byteArray;
        this.bytesByCount = new CustomHashMap();
        this.extracted = new CustomArray();
    }

    //setters and getters
    public void setByteArray(byte[] byteArray) {
        this.byteArray = byteArray;
    }

    public void setDictionary(CustomHashMap dict) {
        this.dictionary = dict;
    }

    public CustomArray getExtractedByteArray() {
        return extracted;
    }

    public CustomHashMap getDictionaryByByte() {
        return dictionaryByByte;
    }

    /**
     * Returns header for packed file. Contains dictionary for decoding.
     *
     * @param s Start of string to which dictionary is appended
     * @return String for header
     */
    public String getHeaderString(String s) {
        if (dictionary == null) {
            return null;
        }

        Object[] keySet = this.dictionary.keySet();
        for (Object key : keySet) {
            key = (String) key;
            s += key;
            s += " ";
            s += this.dictionary.get(key);
            s += "\n";
        }
        s += "END\n";
        return s;
    }

    /**
     * Method for decoding Huffman coded file. Reads bytes from parameter String
     * containing coded bytes in a row. When full byte is found its translated
     * and added to byte array called extracted. If last byte isn't completed
     * it's returned.
     *
     * @param byteString String containing coded bytes in a string form
     * @return String containing leftover if last byte wasn't complete
     */
    public String extract(String byteString) {
        String current = "";
        String twoLast = "";
        Byte b;
        for (int i = 0; i < byteString.length(); i++) {
            current += byteString.charAt(i);
            if (current.length() >= 2) {
                twoLast = current.substring(current.length() - 2, current.length());
            }
            if (twoLast.equals("00") || twoLast.equals("01")) {
                b = (Byte)this.dictionary.get(current);
                extracted.add(b);

                if (this.dictionary.get(current) == null) {
                    System.out.println("Could not find: "+current);
                }

                current = "";
                twoLast = "";
            }
            if (current.length() == 128) {
                extracted.add(this.dictionary.get(current));
                current = "";
                twoLast = "";
            }
        }
        return current;
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
                long n = (long) bytesByCount.get(b);
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
        Object[] all = bytesByCount.keySet();
        for (Object c : all) {
            System.out.println(c + " = " + bytesByCount.get(c));
        }
    }

    /**
     * Method for making dictionary for current read file (byteArray).
     */
    private void makeDictionary() {
        System.out.println("Making dictionary...");

        for (int i = 0; i < bitCombinations.size(); i++) {
            if (bytesByCount.isEmpty()) {
                break;
            }
            Byte b = findMostCommonByte();
            dictionary.put((String) bitCombinations.get(i), b);
            dictionaryByByte.put(b, (String) bitCombinations.get(i));
            bytesByCount.remove(b);
        }
        System.out.println("Done!");
    }

    /**
     * Finds most common byte in bytesByCount. Used when making dictionary.
     *
     * @return Byte b
     */
    private Byte findMostCommonByte() {
        Byte b;
        KeyValuePair max = null;
        for (KeyValuePair entry : bytesByCount.entrySet()) {
            if (max == null
                    ||  (long)entry.getValue() > (long)max.getValue()) {
                max = entry;
            }
        }
        b = (Byte)max.getKey();
        return b;
    }

    //Test methods:
    /**
     * Test method for printing dictionary.
     */
    public void printDictionary() {
        Object[] keySet = dictionary.keySet();
        for (Object key : keySet) {
            System.out.println(dictionary.get(key) + ": " + key);
        }
    }
}
