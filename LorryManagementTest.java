
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A classe de teste LorryManagementTest.
 *
 * @author 160221052  Ricardo Carmo.
 * @author 160221072  Miguel Lobato.
 * @version 31/1/2017
 */
public class LorryManagementTest
{
    LorryManagement lorryManagement = new LorryManagement();
    /**
     * Construtor default para a classe de teste LorryManagementTest
     */
    public LorryManagementTest()
    {
    }

    @Before
    public void setUp()
    {
    }

    @After
    public void tearDown()
    {
        lorryManagement.clearRegistedProducts();
    }

    @Test
    public void testRegisterLorry(){
        Lorry lorry1 = new Lorry("A", 1, 1, 1);
        Lorry lorry2 = new Lorry("A", -1, 1, 1);

        assertEquals(true, lorryManagement.lorryIsRegisted(lorry1.getCode()));
        assertEquals(false, lorryManagement.lorryIsRegisted(lorry2.getCode()));
    }

    @Test
    public void testLorryIsRegisted(){
        Lorry lorry1 = new Lorry("A", 1, 1, 1);
        Lorry lorry2 = new Lorry("A", -1, 1, 1);

        assertEquals(true, lorryManagement.lorryIsRegisted(lorry1.getCode()));
        assertEquals(false, lorryManagement.lorryIsRegisted(lorry2.getCode()));
    }

    @Test
    public void testUnregisterLorry(){
        Lorry lorry1 = new Lorry("A", 1, 1, 1);
        Lorry lorry2 = new Lorry("A", 2, 1, 1);

        assertEquals(true, lorryManagement.lorryIsRegisted(lorry1.getCode()));
        assertEquals(true, lorryManagement.lorryIsRegisted(lorry2.getCode()));

        lorryManagement.unregisterLorry(lorry1.getCode());        
        assertEquals(false, lorryManagement.lorryIsRegisted(lorry1.getCode()));

        lorryManagement.unregisterLorry(lorry2.getCode());        
        assertEquals(false, lorryManagement.lorryIsRegisted(lorry2.getCode()));
    }

    @Test
    public void testGetNumberOfNewLorrys(){
        lorryManagement.clearRegistedProducts();
        Lorry lorry1 = new Lorry("A", 1, 0, 0);
        Lorry lorry2 = new Lorry("A", 2, 0, 0);

        assertEquals(2, lorryManagement.getNumberOfNewLorrys());

        lorry1.moveLorry(0, 180);  
        lorry1.moveLorry(0, 0); 
        lorry1.moveLorry(0, 180);  
        assertEquals(1, lorryManagement.getNumberOfNewLorrys());

        lorry2.moveLorry(0, 180);  
        lorry2.moveLorry(0, 0); 
        lorry2.moveLorry(0, 180);         
        assertEquals(0, lorryManagement.getNumberOfNewLorrys());
    }

    @Test
    public void testGetNumberOfOldLorrys(){
        Lorry lorry1 = new Lorry("A", 1, 0, 0);
        Lorry lorry2 = new Lorry("A", 2, 0, 0);

        assertEquals(0, lorryManagement.getNumberOfOldLorrys());

        lorry1.moveLorryToReach(1000000); 
        System.out.println(lorry1.getTotalKilometers());
        assertEquals(1, lorryManagement.getNumberOfOldLorrys());

        lorry2.moveLorryToReach(1000000);   
        assertEquals(2, lorryManagement.getNumberOfOldLorrys());
    }

    @Test
    public void testClearRegistedProducts(){        
        Lorry lorry1 = new Lorry("A", 1, 1, 1);
        assertEquals(true, lorryManagement.lorryIsRegisted(lorry1.getCode()));
        
        Lorry lorry2 = new Lorry("A", 2, 0, 0);
        assertEquals(true, lorryManagement.lorryIsRegisted(lorry2.getCode()));
        
        lorryManagement.clearRegistedProducts();
        assertEquals(false, lorryManagement.lorryIsRegisted(lorry1.getCode()));
        assertEquals(false, lorryManagement.lorryIsRegisted(lorry2.getCode()));
    }
}
