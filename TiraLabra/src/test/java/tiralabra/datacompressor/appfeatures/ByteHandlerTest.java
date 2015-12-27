/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.datacompressor.appfeatures;

import java.util.HashMap;
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
public class ByteHandlerTest {
    
    public ByteHandlerTest() {
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
     * Test of setInputArray method, of class ByteHandler.
     */
    @Test
    public void testSetInputArray() {
        System.out.println("setInputArray");
        byte[] inputArray = null;
        ByteHandler instance = new ByteHandler();
        instance.setInputArray(inputArray);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCurrentDictionary method, of class ByteHandler.
     */
    @Test
    public void testSetCurrentDictionary() {
        System.out.println("setCurrentDictionary");
        HashMap<Byte, String> currentDictionary = null;
        ByteHandler instance = new ByteHandler();
        instance.setCurrentDictionary(currentDictionary);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of writePackedFile method, of class ByteHandler.
     */
    @Test
    public void testWriteOutput() {
        System.out.println("writeOutput");
        String header = "";
        ByteHandler instance = new ByteHandler();
        instance.writePackedFile(header);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getByteString method, of class ByteHandler.
     */
    @Test
    public void testGetByteString() {
        System.out.println("getByteString");
        byte[] byteArray = null;
        ByteHandler instance = new ByteHandler();
        String expResult = "";
        String result = instance.getByteString(byteArray);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
