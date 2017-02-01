
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A classe de teste ProductManagementTest.
 *
 * @author 160221052  Ricardo Carmo.
 * @author 160221072  Miguel Lobato.
 * @version 31/1/2017
 */
public class ProductManagementTest
{
    public ProductManagementTest()
    {
    }
    
    ProductManagement productManagement= new ProductManagement();
    
    @Before
    public void setUp()
    {                
        productManagement.clearRegistedProducts();
        productManagement.registerProduct(1, "A", 1.0, 1.0);        
        productManagement.registerProduct(-1, "A", 1.0, 1.0);        
        productManagement.registerProduct(2, null, 1.0, 1.0);        
        productManagement.registerProduct(3, "A", -1.0, 1.0);        
        productManagement.registerProduct(4, "A", 1.0, -1.1);
    }

    @After
    public void tearDown()
    {        
        productManagement.clearRegistedProducts();
    }

    @Test
    public void testRegisterProduct(){
        assertEquals(true, productManagement.productIsRegisted(1));        
        assertEquals(false, productManagement.productIsRegisted(-1));        
        assertEquals(false, productManagement.productIsRegisted(2));        
        assertEquals(false, productManagement.productIsRegisted(3));        
        assertEquals(false, productManagement.productIsRegisted(4));
    }
    
    @Test
    public void testGetProductInformation(){
        assertEquals("A/1.0/1.0", productManagement.getProductInformation(1));        
        assertEquals(null, productManagement.getProductInformation(-1));        
        assertEquals(null, productManagement.getProductInformation(2));        
        assertEquals(null, productManagement.getProductInformation(3));        
        assertEquals(null, productManagement.getProductInformation(4));        
    }
    
    @Test
    public void testProductIsRegisted(){
        assertEquals(true, productManagement.productIsRegisted(1));        
        assertEquals(false, productManagement.productIsRegisted(-1));        
        assertEquals(false, productManagement.productIsRegisted(2));        
        assertEquals(false, productManagement.productIsRegisted(3));        
        assertEquals(false, productManagement.productIsRegisted(4));         
    }
    
    @Test
    public void testUnregisterProduct(){
        productManagement.unregisterProduct(1);
        assertEquals(false, productManagement.productIsRegisted(1)); 
        productManagement.unregisterProduct(-1);
        assertEquals(false, productManagement.productIsRegisted(-1));
        productManagement.unregisterProduct(2);
        assertEquals(false, productManagement.productIsRegisted(2)); 
        productManagement.unregisterProduct(3);
        assertEquals(false, productManagement.productIsRegisted(3));  
        productManagement.unregisterProduct(4);
        assertEquals(false, productManagement.productIsRegisted(4));
    }
}
