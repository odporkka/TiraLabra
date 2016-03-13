package tiralabra.datacompressor.datastructures;

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
public class CustomArrayTest {
    
    public CustomArrayTest() {
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
     * Test of add method, of class CustomArray.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        Object o = null;
        CustomArray instance = new CustomArray();
        instance.add(1);
        instance.add(2);
        instance.add(3);
        instance.add(4);
        instance.add(5);
        instance.add(6);
        instance.add(7);
        instance.add(8);
        instance.add(9);
        instance.add(10);
        instance.add(1);
        instance.add(2);
        instance.add(3);
        instance.add(4);
        
        assertEquals(14, instance.size());
    }

    /**
     * Test of size method, of class CustomArray.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        CustomArray instance = new CustomArray();
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);
        
        instance.add("asdasd");
        instance.add("LASHD1243t ..");
        
        result = instance.size();
        assertEquals(2, result);
    }

    /**
     * Test of get method, of class CustomArray.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        int index = 2;
        CustomArray instance = new CustomArray();
        instance.add(5);
        instance.add(28);
        instance.add(-1);
        Object expResult = -1;
        Object result = instance.get(index);
        assertEquals(expResult, result);
    }
    
}
