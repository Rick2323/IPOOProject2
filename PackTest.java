
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A classe de teste PackTest.
 *
 * @author 160221052  Ricardo Carmo.
 * @author 160221072  Miguel Lobato.
 * @version 31/1/2017
 */
public class PackTest
{
    ProductManagement productManagement= new ProductManagement();
    public PackTest()
    {
    }

    @Before
    public void setUp(){
    }

    @After
    public void tearDown()
    {
        productManagement.clearRegistedProducts();
    }

    @Test
    public void testPack(){
        Pack.registerProduct(10, "A", 1, 1);
        
        Pack pack1 = new Pack(null, 10);
        Pack pack2 = new Pack(0, 10);
        Pack pack3 = new Pack(10, 10);        
        Pack pack4 = new Pack(10, -10);        
        Pack pack5 = new Pack(10, 0);
        Pack pack6 = new Pack(10, 10);

        assertEquals(null, pack1.getCode());
        assertEquals(null, pack2.getCode());
        assertEquals(10, pack3.getCode(), 0.0001);
        assertEquals(1, pack4.getQuantity(), 0.0001);
        assertEquals(1, pack5.getQuantity(), 0.0001);
        assertEquals(10, pack6.getQuantity(), 0.0001);
    }

    @Test
    public void testPack2(){//segundo construtor
        Pack pack1 = new Pack(-10, "A", 1, 1, 10);
        Pack pack2 = new Pack(0, "A", 1, 1, 10);
        Pack pack3 = new Pack(10, "A", 1, 1, 10);
        Pack pack4 = new Pack(11, "A", 1, 1, -10);
        Pack pack5 = new Pack(12, "A", 1, 1, 0);
        Pack pack6 = new Pack(13, "A", 1, 1, 10);

        assertEquals(null, pack1.getCode());
        assertEquals(0, pack2.getCode(), 0.0001);
        assertEquals(10, pack3.getCode(), 0.0001);
        assertEquals(1, pack4.getQuantity(), 0.0001);
        assertEquals(1, pack5.getQuantity(), 0.0001);
        assertEquals(10, pack6.getQuantity(), 0.0001);
    }

    @Test
    public void testGetCode(){
        Pack pack1 = new Pack(-10, "A", 1, 1, 10);
        Pack pack2 = new Pack(0, "A", 1, 1, 10);
        Pack pack3 = new Pack(10, "A", 1, 1, 10);

        assertEquals(null, pack1.getCode());
        assertEquals(0, pack2.getCode(), 0.0001);
        assertEquals(10, pack3.getCode(), 0.0001);   
    }

    @Test
    public void testRegisterProduct(){
        Pack.registerProduct(-10, "A", 1, 1);
        Pack.registerProduct(0, "A", 1, 1);
        Pack.registerProduct(10, "A", 1, 1);  

        assertEquals(false, ProductManagement.productIsRegisted(-10));        
        assertEquals(true, ProductManagement.productIsRegisted(0));        
        assertEquals(true, ProductManagement.productIsRegisted(10));
    }

    @Test
    public void testCorrectInvadidCode(){
        Pack pack1 = new Pack(-10, 10);
        assertEquals(null, pack1.getCode());

        pack1.correctInvadidCode(null);
        assertEquals(null, pack1.getCode());

        pack1.correctInvadidCode(10);
        assertEquals(null, pack1.getCode());

        Pack.registerProduct(10, "A", 1, 1); 

        pack1.correctInvadidCode(10);
        assertEquals(10, pack1.getCode(), 0.0001);  
    }

    @Test
    public void testetQuantity(){
        Pack pack1 = new Pack(10, "A", 1, 1, 0);        
        Pack pack2 = new Pack(11, "A", 1, 1, 10);

        assertEquals(1, pack1.getQuantity(), 0.0001);
        assertEquals(10, pack2.getQuantity(), 0.0001);       
    }

    @Test
    public void testGetName(){
        Pack pack1 = new Pack(10, null, 1, 1, 10);        
        Pack pack2 = new Pack(11, "A", 1, 1, 10);

        assertEquals(null, pack1.getName());
        assertEquals("A", pack2.getName());       
    }

    @Test
    public void testGetWeight(){
        Pack pack1 = new Pack(10, "A", 0, 1, 10);        
        Pack pack2 = new Pack(11, "A", 1, 1, 10);

        assertEquals(0, pack1.getWeight(), 0.0001);
        assertEquals(1, pack2.getWeight(), 0.0001);       
    }

    @Test
    public void testGetVolume(){
        Pack pack1 = new Pack(10, "A", 0, 1, 10);        
        Pack pack2 = new Pack(11, "A", 1, 1, 10);

        assertEquals(0, pack1.getVolume(), 0.0001);
        assertEquals(1, pack2.getVolume(), 0.0001);       
    }

    @Test
    public void testAddQuantity(){
        Pack pack1 = new Pack(10, "A", 1, 0, 10);

        pack1.addQuantity(10);
        assertEquals(20, pack1.getQuantity());

        pack1.addQuantity(20);
        assertEquals(40, pack1.getQuantity());        
    }
    
    @Test
    public void testRemoveQuantity(){
        Pack pack1 = new Pack(10, "A", 1, 0, 40);

        pack1.removeQuantity(10);
        assertEquals(30, pack1.getQuantity());

        pack1.removeQuantity(20);
        assertEquals(10, pack1.getQuantity());        
    }
    
    @Test
    public void testGetTotalWeight(){
        Pack pack1 = new Pack(10, "A", 2, 1, 10);

        assertEquals(20, pack1.getTotalWeight(), 0.0001);
        
        pack1.removeQuantity(5);
        assertEquals(10, pack1.getTotalWeight(), 0.0001);

        pack1.addQuantity(15);
        assertEquals(40, pack1.getTotalWeight(), 0.0001);  
        
        pack1.removeQuantity(10);
        assertEquals(20, pack1.getTotalWeight(), 0.0001);
    }
    
    @Test
    public void testGetTotalVolume(){
        Pack pack1 = new Pack(10, "A", 1, 4, 10);

        assertEquals(40, pack1.getTotalVolume(), 0.0001);
        
        pack1.removeQuantity(5);
        assertEquals(20, pack1.getTotalVolume(), 0.0001);

        pack1.addQuantity(15);
        assertEquals(80, pack1.getTotalVolume(), 0.0001);  
        
        pack1.removeQuantity(10);
        assertEquals(40, pack1.getTotalVolume(), 0.0001);
    }
}
