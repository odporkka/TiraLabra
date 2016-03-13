package tiralabra.datacompressor.datastructures;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests of custom "HashMap" class.s
 * @author ode
 */
public class CustomHashMapTest {

    public CustomHashMapTest() {
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
     * Test of put method, of class CustomHashMap.
     */
    @Test
    public void testPut() {
        System.out.println("put");
        Object key = "avain";
        Object value = "arvo";
        CustomHashMap instance = new CustomHashMap();
        instance.put(key, value);
        String arvo = (String) instance.get("avain");
        assertEquals("arvo", arvo);
    }

    /**
     * Test of size method, of class CustomHashMap.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        CustomHashMap instance = new CustomHashMap();
        Object key = "avain";
        Object value = "arvo";
        int expResult = 5;
        instance.put(key, value);
        instance.put(key, value);
        instance.put(key, value);
        instance.put(key, value);
        instance.put(key, value);
        int result = instance.size();
        assertEquals(expResult, result);
    }

    /**
     * Test of get method, of class CustomHashMap.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        Object key = "avain";
        Object value = "arvo";
        CustomHashMap instance = new CustomHashMap();
        instance.put(key, value);
        Object expResult = "arvo";
        Object result = instance.get(key);
        assertEquals(expResult, result);
    }

    @Test
    public void removeTest(){
        CustomHashMap instance = new CustomHashMap();
        instance.put("key", "value");
        instance.put("key1", "value2");
        instance.put("key3", "value3");
        instance.put("key4", "value4");
        instance.remove("key3");
        assertEquals(3, instance.size());
    }
}
