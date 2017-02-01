

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A classe de teste SalesListTest.
 *
 * @author  (seu nome)
 * @version (um número de versão ou data)
 */
public class SalesListTest
{
    /**
     * Construtor default para a classe de teste SalesListTest
     */
    public SalesListTest()
    {
    }

    @Before
    public void setUp()
    {
    }

    @After
    public void tearDown()
    {
    }
    
    @Test
    public void testAddProductToSell(){
        SalesList salesList = new SalesList();
        Pack pack1 = new Pack(1, "B", 1 ,1, 10);
        
        assertEquals(false, salesList.productCanBeSold(pack1.getCode()));  
        
        salesList.addProductToSell(pack1.getCode());
        assertEquals(true, salesList.productCanBeSold(pack1.getCode()));  
    }
    
    @Test
    public void testProductCanBeSold(){
        SalesList salesList = new SalesList();
        Pack pack1 = new Pack(1, "B", 1 ,1, 10);
        
        assertEquals(false, salesList.productCanBeSold(pack1.getCode()));  
        
        salesList.addProductToSell(pack1.getCode());
        assertEquals(true, salesList.productCanBeSold(pack1.getCode()));  
    }
    
    @Test
    public void testRemoveProductToSell(){
        SalesList salesList = new SalesList();
        Pack pack1 = new Pack(1, "B", 1 ,1, 10);
        
        assertEquals(false, salesList.productCanBeSold(pack1.getCode()));  
        
        salesList.addProductToSell(pack1.getCode());
        assertEquals(true, salesList.productCanBeSold(pack1.getCode())); 
        
        salesList.removeProductToSell(pack1.getCode());
        assertEquals(false, salesList.productCanBeSold(pack1.getCode())); 
    }
}
