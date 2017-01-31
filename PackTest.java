
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A classe de teste PackTest.
 *
 * @author  (seu nome)
 * @version (um número de versão ou data)
 */
public class PackTest
{
    /**
     * Construtor default para a classe de teste PackTest
     */
    public PackTest()
    {
    }

    /**
     * Define a .
     *
     * Chamado antes de cada método de caso de teste.
     */
    @Before
    public void setUp(){
    }

    /**
     * Tears down the test fixture.
     *
     * Chamado após cada método de teste de caso.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void Pack(){
        Pack.registerProduct(-10, "A", 1,1);
        Pack.registerProduct(0, "B", 2,2);
        Pack.registerProduct(10, "C", 3,3);

        Pack pack1 = new Pack(-10, 10);
        Pack pack2 = new Pack(0, 10);
        Pack pack3 = new Pack(10, 10);        
        Pack pack4 = new Pack(10, -10);        
        Pack pack5 = new Pack(10, 0);
        Pack pack6 = new Pack(10, 10);

        assertEquals(null, pack1.getCode());
        assertEquals((long)0, (long)pack2.getCode());
        assertEquals((long)10, (long)pack3.getCode());
        assertEquals((long)1, (long)pack4.getQuantity());
        assertEquals((long)1, (long)pack5.getQuantity());
        assertEquals((long)10, (long)pack6.getQuantity());
    }

    @Test
    public void Pack2(){
        Pack pack1 = new Pack(-10, "A", 1, 1, 10);
        Pack pack2 = new Pack(0, "A", 1, 1, 10);
        Pack pack3 = new Pack(10, "A", 1, 1, 10);
        Pack pack4 = new Pack(11, "A", 1, 1, -10);
        Pack pack5 = new Pack(12, "A", 1, 1, 0);
        Pack pack6 = new Pack(13, "A", 1, 1, 10);

        assertEquals(null, pack1.getCode());
        assertEquals((long)0, (long)pack2.getCode());
        assertEquals((long)10, (long)pack3.getCode());
        assertEquals((long)1, (long)pack4.getQuantity());
        assertEquals((long)1, (long)pack5.getQuantity());
        assertEquals((long)10, (long)pack6.getQuantity());
    }

    @Test
    public void getCode(){
        Pack pack1 = new Pack(-10, "A", 1, 1, 10);
        Pack pack2 = new Pack(0, "A", 1, 1, 10);
        Pack pack3 = new Pack(10, "A", 1, 1, 10);

        assertEquals(null, pack1.getCode());
        assertEquals((long)0, (long)pack2.getCode());
        assertEquals((long)10, (long)pack3.getCode());   
    }

    @Test
    public void registerProduct(){
        Pack.registerProduct(-10, "A", 1, 1);
        Pack.registerProduct(0, "A", 1, 1);
        Pack.registerProduct(10, "A", 1, 1);  

        assertEquals(false, ProductManagement.productIsRegisted(-10));        
        assertEquals(true, ProductManagement.productIsRegisted(0));        
        assertEquals(true, ProductManagement.productIsRegisted(10));
    }

    @Test
    public void correctInvadidCode(){
        Pack pack1 = new Pack(-10, 10);
        assertEquals(null, pack1.getCode());

        pack1.correctInvadidCode(null);
        assertEquals(null, pack1.getCode());

        pack1.correctInvadidCode(10);
        assertEquals(null, pack1.getCode());

        Pack.registerProduct(10, "A", 1, 1);  

        pack1.correctInvadidCode(10);
        assertEquals((long)10, (long)pack1.getCode());  
    }

    @Test
    public void getQuantity(){
        Pack pack1 = new Pack(10, "A", 1, 1, 0);        
        Pack pack2 = new Pack(11, "A", 1, 1, 10);

        assertEquals((long)1, (long)pack1.getQuantity());
        assertEquals((long)10, (long)pack2.getQuantity());       
    }

    @Test
    public void getName(){
        Pack pack1 = new Pack(10, null, 1, 1, 0);        
        Pack pack2 = new Pack(11, "A", 1, 1, 10);

        assertEquals(null, pack1.getName());
        assertEquals("A", pack2.getName());       
    }

    @Test
    public void getWeight(){
        Pack pack1 = new Pack(10, "A", 0, 1, 10);        
        Pack pack2 = new Pack(11, "A", 1, 1, 10);

        assertEquals((long)0, (long)pack1.getWeight());
        assertEquals((long)1, (long)pack2.getWeight());       
    }

    @Test
    public void getVolume(){
        Pack pack1 = new Pack(10, "A", 0, 1, 10);        
        Pack pack2 = new Pack(11, "A", 1, 1, 10);

        assertEquals((long)0, (long)pack1.getVolume());//QUE MERDA É ESTA!?!?
        assertEquals((long)1, (long)pack2.getVolume());       
    }

    @Test
    public void addQuantity(){
        Pack pack1 = new Pack(10, "A", 1, 0, 10);

        pack1.addQuantity(10);
        assertEquals(20, pack1.getQuantity());

        pack1.addQuantity(20);
        assertEquals(40, pack1.getQuantity());        
    }
    
    @Test
    public void removeQuantity(){
        Pack pack1 = new Pack(10, "A", 1, 0, 40);

        pack1.removeQuantity(10);
        assertEquals(30, pack1.getQuantity());

        pack1.removeQuantity(20);
        assertEquals(10, pack1.getQuantity());        
    }
    
    @Test
    public void getTotalWeight(){
        Pack pack1 = new Pack(10, "A", 1, 0, 10);

        pack1.removeQuantity(5);
        assertEquals((long)5, (long)pack1.getTotalWeight());

        pack1.addQuantity(15);
        assertEquals((long)20, (long)pack1.getQuantity());  
        
        pack1.removeQuantity(10);
        assertEquals((long)10, (long)pack1.getTotalWeight());
    }
    
    @Test
    public void getTotalVolume(){
        Pack pack1 = new Pack(10, "A", 1, 0, 10);

        pack1.removeQuantity(5);
        assertEquals((long)5, (long)pack1.getTotalVolume());

        pack1.addQuantity(15);
        assertEquals((long)20, (long)pack1.getTotalVolume());  
        
        pack1.removeQuantity(10);
        assertEquals((long)10, (long)pack1.getTotalVolume());
    }
}
