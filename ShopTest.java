
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A classe de teste ShopTest.
 *
 * @author 160221052  Ricardo Carmo.
 * @author 160221072  Miguel Lobato.
 * @version 31/1/2017
 */
public class ShopTest
{
    /**
     * Construtor default para a classe de teste ShopTest
     */
    public ShopTest()
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
    public void testSetName(){
        Shop shop1 = new Shop("A", 0, 0);
        Shop shop2 = new Shop(null, 0, 0);
        
        assertEquals("A", shop1.getName());
        assertEquals("", shop2.getName());
        
        shop1.setName(null);
        shop2.setName("B");
        
        assertEquals("A", shop1.getName());
        assertEquals("B", shop2.getName());
        
        shop1.setName("C");
        assertEquals("C", shop1.getName());
    }
    
    @Test
    public void testGetName(){
        Shop shop1 = new Shop("A", 0, 0);
        Shop shop2 = new Shop(null, 0, 0);
        
        assertEquals("A", shop1.getName());
        assertEquals("", shop2.getName());
        
        shop1.setName(null);
        shop2.setName("B");
        
        assertEquals("A", shop1.getName());
        assertEquals("B", shop2.getName());
        
        shop1.setName("C");
        assertEquals("C", shop1.getName());
    }
    
    @Test
    public void testGetLatitude(){
        Shop shop1 = new Shop("A", 1, 0);
        Shop shop2 = new Shop(null, 3, 0);
        
        assertEquals(1, shop1.getLatitude(), 0.0001);
        assertEquals(3, shop2.getLatitude(), 0.0001);
    }
    
    @Test
    public void testGetLongitude(){
        Shop shop1 = new Shop("A", 0, 2);
        Shop shop2 = new Shop(null, 0, 4);
        
        assertEquals(2, shop1.getLongitude(), 0.0001);
        assertEquals(4, shop2.getLongitude(), 0.0001);
    }
    
    @Test
    public void testUnloadLorry(){
        Shop shop1 = new Shop("A", 0, 0);
        Lorry lorry1 = new Lorry("Veículo1", 1, 0, 0);
        Container container1 = new Container(1);
        Container container2 = new Container(2);
        
        assertEquals(false, lorry1.hasContainer());
        
        lorry1.loadContainer(container1);        
        assertEquals(true, lorry1.hasContainer());
        
        shop1.unloadLorry(lorry1);       
        assertEquals(false, lorry1.hasContainer());
        
        lorry1.loadContainer(container2);        
        assertEquals(true, lorry1.hasContainer());
        
        shop1.unloadLorry(lorry1);
        assertEquals(false, lorry1.hasContainer());
    }
    
    @Test
    public void testLoadLorryWithCotntainer(){
        Shop shop1 = new Shop("A", 0, 0);
        Lorry lorry1 = new Lorry("Veículo1", 1, 0, 0);
        Container container1 = new Container(1);
        
        assertEquals(false, lorry1.hasContainer());
        
        lorry1.loadContainer(container1);        
        assertEquals(true, lorry1.hasContainer());
        
        shop1.unloadLorry(lorry1);     
        assertEquals(false, lorry1.hasContainer());
        
        shop1.loadLorryWithContainer(lorry1, container1.getCode());
        assertEquals(true, lorry1.hasContainer());
    }
    
    @Test
    public void testUnloadContainerFully(){
        Shop shop1 = new Shop("A", 0, 0);
        Lorry lorry1 = new Lorry("Veículo1", 1, 0, 0);
        Container container1 = new Container(1);
        Pack pack1 = new Pack(1, "B", 1 ,1, 10);        
        Pack pack2 = new Pack(2, "A", 1 ,1, 10);
        
        assertEquals(0, container1.getNumberOfPacks());
        
        container1.loadContainer(pack1);        
        assertEquals(1, container1.getNumberOfPacks());
        
        container1.loadContainer(pack2);        
        assertEquals(2, container1.getNumberOfPacks());
        
        lorry1.loadContainer(container1); 
        shop1.unloadLorry(lorry1);
        
        shop1.unloadContainerFully(container1.getCode());            
        assertEquals(0, container1.getNumberOfPacks());
    }
    
    @Test
    public void testUnloadContainerSingle(){
        Shop shop1 = new Shop("A", 0, 0);
        Lorry lorry1 = new Lorry("Veículo1", 1, 0, 0);
        Container container1 = new Container(1);
        Pack pack1 = new Pack(1, "B", 1 ,1, 10);        
        Pack pack2 = new Pack(2, "A", 1 ,1, 10);
        
        assertEquals(0, container1.getNumberOfPacks());
        
        container1.loadContainer(pack1);        
        assertEquals(1, container1.getNumberOfPacks());
        
        container1.loadContainer(pack2);        
        assertEquals(2, container1.getNumberOfPacks());
        
        lorry1.loadContainer(container1); 
        shop1.unloadLorry(lorry1);
        
        shop1.unloadContainerSingle(container1.getCode(), pack2.getCode(), pack2.getQuantity());            
        assertEquals(1, container1.getNumberOfPacks());
        
        shop1.unloadContainerSingle(container1.getCode(), pack1.getCode(), pack1.getQuantity());            
        assertEquals(0, container1.getNumberOfPacks());
    }
    
    @Test
    public void testSell(){
        Shop shop1 = new Shop("A", 0, 0);
        Lorry lorry1 = new Lorry("Veículo1", 1, 0, 0);
        Container container1 = new Container(1);
        Pack pack1 = new Pack(1, "B", 1 ,1, 10);    
        
        container1.loadContainer(pack1);        
        lorry1.loadContainer(container1); 
        shop1.unloadLorry(lorry1);
        
        shop1.unloadContainerFully(container1.getCode()); 
        Pack pack2 = shop1.sell(pack1.getCode(), pack1.getQuantity());
        
        assertEquals(true, pack1.getCode().equals(pack2.getCode()));        
        assertEquals(true, pack1.getQuantity() == pack2.getQuantity());
    }
    
    @Test
    public void testAddProductToSell(){
        Shop shop1 = new Shop("A", 0, 0);
        Pack pack1 = new Pack(1, "B", 1 ,1, 10);
        
        assertEquals(false, shop1.productCanBeSold(pack1.getCode()));  
        
        shop1.addProductToSell(pack1.getCode());
        assertEquals(true, shop1.productCanBeSold(pack1.getCode()));  
    }
    
    @Test
    public void testProductCanBeSold(){
        Shop shop1 = new Shop("A", 0, 0);
        Pack pack1 = new Pack(1, "B", 1 ,1, 10);
        
        assertEquals(false, shop1.productCanBeSold(pack1.getCode()));  
        
        shop1.addProductToSell(pack1.getCode());
        assertEquals(true, shop1.productCanBeSold(pack1.getCode()));  
    }
    
    @Test
    public void testRemoveProductToSell(){
        Shop shop1 = new Shop("A", 0, 0);
        Pack pack1 = new Pack(1, "B", 1 ,1, 10);
        
        assertEquals(false, shop1.productCanBeSold(pack1.getCode()));  
        
        shop1.addProductToSell(pack1.getCode());
        assertEquals(true, shop1.productCanBeSold(pack1.getCode())); 
        
        shop1.removeProductToSell(pack1.getCode());
        assertEquals(false, shop1.productCanBeSold(pack1.getCode())); 
    }
}
