package tiralabra.datacompressor.appfeatures;

import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class FileManagerTest {
    File f = null;
    ClassLoader cl;
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
        cl = getClass().getClassLoader();
        f = new File(cl.getResource("testfile1.txt").getFile());
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setFile method, of class FileManager.
     */
    @Test
    public void testSetFile() {
        assertEquals("File not selected!", fmgr.fileLabel);
        fmgr.setFile(f);
        String ext = fmgr.fileLabel.substring(fmgr.fileLabel.length() - 4, 
                fmgr.fileLabel.length());
        assertEquals(".txt", ext);
    }
    
    @Test
    public void testReadFile(){
        
    }
    
    /**
     * Test of getPath method, of class FileManager.
     */
    @Test
    public void testGetPath() {
        fmgr.setFile(f);
        String expected = "/testfile1.txt";
        String actual = this.fmgr.getPath().substring(
                this.fmgr.getPath().length()-14,
                this.fmgr.getPath().length());
        assertEquals(expected, this.fmgr.getPath());
    }

    /**
     * Test1 of huffmanCompress method, of class FileManager.
     * Tests if file has changed at all.
     */
    @Test
    public void testHuffmanCompress1() {
             
    }
    
}
