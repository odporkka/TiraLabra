/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.datacompressor.appfeatures.huffman;

import java.util.HashMap;

/**
 *
 * @author ode
 */
public class HuffmanVocabulary {
    
      private final HashMap<Integer, Byte> vocabulary;

    public HuffmanVocabulary() {
        this.vocabulary = new HashMap<>(256);
    }
      
      
}
