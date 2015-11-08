package tiralabra.datacompressor.appfeatures;

import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class FileManagerTest {
    File f;
    private FileManager fmgr;
    
    public FileManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        fmgr = new FileManager();
        f = new File("/home/ode/Documents/TiraLabra15/TiraLabra/src/main/resources/testfile1.txt");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setFile method, of class FileManager.
     */
    @Test
    public void testSetFile() {
        fmgr.setFile(f);
        assertEquals("/home/ode/Documents/TiraLabra15/TiraLabra/src/main/resources/testfile1.txt", 
                this.fmgr.fileLabel);
    }

    /**
     * Test of getPath method, of class FileManager.
     */
    @Test
    public void testGetPath() {
        fmgr.setFile(f);
        String expected = "/home/ode/Documents/TiraLabra15/TiraLabra/src/main/resources/testfile1.txt";
        assertEquals(expected, this.fmgr.getPath());
    }

    /**
     * Test1 of huffmanCompress method, of class FileManager.
     * Tests if file has changed at all.
     */
    @Test
    public void testHuffmanCompress1() {
        fmgr.setFile(f);
        String expected = fmgr.getFileAsString();
        fmgr.huffmanCompress();
        assertNotEquals(expected, fmgr.getFileAsString());      
    }
    
}
