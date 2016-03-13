/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.datacompressor.appfeatures.huffman;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ode
 */
public class HuffmanCompressorTest {
    
    public HuffmanCompressorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setByteArray method, of class HuffmanCompressor.
     */
    @Test
    public void testSetByteArray() {
        System.out.println("setByteArray");
        byte[] byteArray = null;
        HuffmanCompressor instance = null;
        instance.setByteArray(byteArray);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of compress method, of class HuffmanCompressor.
     */
    @Test
    public void testCompress() {
        System.out.println("compress");
        HuffmanCompressor instance = null;
        instance.compress();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readDictionary method, of class HuffmanCompressor.
     */
    @Test
    public void testReadDictionary() {
        System.out.println("readDictionary");
        String fileAsString = "";
        HuffmanCompressor instance = null;
        instance.readDictionary(fileAsString);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of extractByteArray method, of class HuffmanCompressor.
     */
    @Test
    public void testExtractByteArray() {
        System.out.println("extractByteArray");
        HuffmanCompressor instance = null;
        instance.extractByteArray();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
