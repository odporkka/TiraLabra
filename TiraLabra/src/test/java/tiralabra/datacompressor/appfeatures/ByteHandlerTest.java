package tiralabra.datacompressor.appfeatures;

import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.datacompressor.appfeatures.huffman.HuffmanDictionary;

/**
 * Tests of the ByteHandler class. Also kind of tests whole program since 
 * you cannot test ByteHandler unless you have working packing/extracting
 * algorithm with dictionary.
 * @author ode
 */
public class ByteHandlerTest {
    private ByteHandler bh;
    byte[] testArray;
    private HuffmanDictionary hfd;
    
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
        this.bh = new ByteHandler();
               
        this.testArray = new byte[256];
        byte b;
        for (int i = 0; i < 256; i++) {
            b = (byte) i;
            testArray[i] = b;
        }
        
        this.hfd = new HuffmanDictionary(testArray);
        this.bh.setCurrentDictionary(this.hfd.getDictionaryByByte());
    }
    
    @After
    public void tearDown() {
        File f1 = new File(System.getProperty("user.home") + "/packed.test");
        File f2 = new File(System.getProperty("user.home") + "/extracted.test");
        if (f1.delete() && f2.delete()){
            System.out.println("Test files deleted succesfully!");
        }
    }
    
    
    @Test
    public void getByteStringTest(){
        String result = this.bh.getByteString(testArray);
        assertEquals(2048, result.length());
        result = result.substring(0, 32);
        assertEquals(
                "00000000000000010000001000000011", 
                result);
    }
    
    @Test
    public void writePackedFileTest(){
        this.bh.setInputArray(testArray);
        this.bh.setFileExt(".test");
        String header = "HUFF: "+this.bh.getFileExt()+"\n";
        this.bh.writePackedFile(this.hfd.getHeaderString(header));
        File f = new File(System.getProperty("user.home") + "/packed.test");
        FileManager fm = new FileManager();
        fm.setFile(f);
        String path = System.getProperty("user.home") + "/packed.test";
        assertEquals(path, fm.fileLabel);
        assertEquals((long)20068, f.length());
    }
    
    @Test
    public void writeExtractedFileTest(){
        this.bh.setInputArray(testArray);
        this.bh.setFileExt(".test");
        String header = "HUFF: "+this.bh.getFileExt()+"\n";
        this.bh.writePackedFile(this.hfd.getHeaderString(header));
        FileManager fm = new FileManager();
        File f = new File(System.getProperty("user.home") + "/packed.test");
        System.out.println(f.getAbsolutePath());
        fm.setFile(f);
        boolean success = fm.extract();
        f = new File(System.getProperty("user.home") + "/extracted.test");
        assertEquals(257, f.length());
        
    }
}
