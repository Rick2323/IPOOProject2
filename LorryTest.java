

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class LorryTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class LorryTest
{
    /**
     * Default constructor for test class LorryTest
     */
    public LorryTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void testGetCode()
    {
        Lorry lorry1 = new Lorry("Veiculo1", 1, 0.0, 0.0);
        assertEquals(1, lorry1.getCode());
        Lorry lorry2 = new Lorry("Veiculo2", 2, 1.0, 1.0);
        assertEquals(2, lorry2.getCode());
        Lorry lorry3 = new Lorry("Veiculo3", 0, 1.0, 0.0);
        assertEquals(0, lorry3.getCode());
        Lorry lorry4 = new Lorry("Veiculo4", -5, 0.0, 0.0);
        assertEquals(0, lorry4.getCode());
    }

    @Test
    public void testSetDesignation()
    {
        Lorry lorry1 = new Lorry("Veiculo1", 1, 0.0, 0.0);
        assertEquals("Veiculo1", lorry1.getDesignation());
        Lorry lorry2 = new Lorry(null, 2, 0.0, 0.0);
        assertEquals("", lorry2.getDesignation());
        lorry1.setDesignation("Veiculo1");
        assertEquals("Veiculo1", lorry1.getDesignation());
        lorry2.setDesignation("");
        assertEquals("", lorry2.getDesignation());
        lorry1.setDesignation("Veiculo5");
        assertEquals("Veiculo5", lorry1.getDesignation());
    }
}


