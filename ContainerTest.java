import java.util.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A classe de teste ContainerTest.
 *
 * @author 160221052  Ricardo Carmo.
 * @author 160221072  Miguel Lobato.
 * @version 31/1/2017
 */
public class ContainerTest
{
    /**
     * Construtor default para a classe de teste ContainerTest
     */
    public ContainerTest()
    {
    }

    /**
     * Define a .
     *
     * Chamado antes de cada método de caso de teste.
     */
    @Before
    public void setUp()
    {       
    }

    /**
     * Tears down the test fixture.
     *
     * Chamado após cada método de teste de caso.
     */
    @After
    public void tearDown()
    {        
        Container.clearRegistedContainers();
    }

    @Test
    public void testCorrectInvadidCode(){   
        Container container1 = new Container(-2);        
        Container container2 = new Container(-1);        
        Container container3 = new Container(1);        
        Container container4 = new Container(2);

        container1.correctInvadidCode(6);
        container2.correctInvadidCode(0);
        container3.correctInvadidCode(0);
        container4.correctInvadidCode(4);

        assertEquals(6, container1.getCode());
        assertEquals(0, container2.getCode());
        assertEquals(1, container3.getCode());
        assertEquals(2, container4.getCode()); 
    }

    @Test
    public void testGetCode(){               
        Container container1 = new Container(-1);        
        Container container2 = new Container(0);        
        Container container3 = new Container(1);        
        Container container4 = new Container(2); 

        assertEquals(0, container1.getCode());
        assertEquals(0, container2.getCode());
        assertEquals(1, container3.getCode());
        assertEquals(2, container4.getCode());
    }

    @Test
    public void testOcuppiedWeight(){
        Container container1 = new Container(1);
        Pack pack1 = new Pack(1, "A", 1, 1, 5);
        Pack pack2 = new Pack(2, "B", 2, 2, 10);

        container1.loadContainer(pack1);        
        assertEquals(5, container1.ocuppiedWeight(), 0.0001);

        container1.loadContainer(pack2); 
        assertEquals(25, container1.ocuppiedWeight(), 0.0001);
    }

    @Test
    public void testAvailableWeight(){
        Container container1 = new Container(1);
        Pack pack1 = new Pack(1, "A", 200, 1, 10);
        Pack pack2 = new Pack(2, "B", 1000, 2, 10);

        container1.loadContainer(pack1);        
        assertEquals(20000, container1.availableWeight(), 0.0001);

        container1.loadContainer(pack2); 
        assertEquals(10000, container1.availableWeight(), 0.0001);
    }

    @Test
    public void testOcuppiedVolume(){
        Container container1 = new Container(1);
        Pack pack1 = new Pack(1, "A", 1, 1, 5);
        Pack pack2 = new Pack(2, "B", 2, 2, 10);

        container1.loadContainer(pack1);        
        assertEquals(5, container1.ocuppiedVolume(), 0.0001);

        container1.loadContainer(pack2); 
        assertEquals(25, container1.ocuppiedVolume(), 0.0001);
    }

    @Test
    public void testAvailableVolume(){
        Container container1 = new Container(1);
        Pack pack1 = new Pack(1, "A", 1, 3000, 1000);
        Pack pack2 = new Pack(2, "B", 2, 20000, 1000);

        container1.loadContainer(pack1);        
        assertEquals(30000000, container1.availableVolume(), 0.0001);

        container1.loadContainer(pack2); 
        assertEquals(10000000, container1.availableVolume(), 0.0001);
    }

    @Test
    public void testLoadContainer(){
        Container container1 = new Container(1);
        Pack pack1 = new Pack(1, "A", 1, 1, 1);
        Pack pack2 = new Pack(2, "B", 2, 2, 2);

        container1.loadContainer(pack1);
        assertEquals(1, container1.getNumberOfPacks());

        container1.loadContainer(pack2);
        assertEquals(2, container1.getNumberOfPacks());        
    }

    @Test
    public void testUnloadContainer(){
        Container container1 = new Container(1);
        Pack pack1 = new Pack(1, "A", 1, 1, 1);
        Pack pack2 = new Pack(2, "B", 2, 2, 2);

        container1.loadContainer(pack1);
        container1.loadContainer(pack2); 

        Pack fromContainer1 = container1.unloadContainer(pack1.getCode(), pack1.getQuantity());    
        assertEquals(1, fromContainer1.getCode(), 0.0001); 
        assertEquals(1, fromContainer1.getQuantity(), 0.0001);  
        assertEquals(1, container1.getNumberOfPacks());

        Pack fromContainer2 = container1.unloadContainer(pack2.getCode(), pack2.getQuantity());    
        assertEquals(2, fromContainer2.getCode(), 0.0001); 
        assertEquals(2, fromContainer2.getQuantity(), 0.0001);  
        assertEquals(0, container1.getNumberOfPacks());
    }

    @Test
    public void testUnloadContainerFully(){
        Container container1 = new Container(1);
        Pack pack1 = new Pack(1, "A", 1, 1, 1);
        Pack pack2 = new Pack(2, "B", 2, 2, 2);

        container1.loadContainer(pack1);
        container1.loadContainer(pack2);
        assertEquals(2, container1.getNumberOfPacks());

        ArrayList<Pack> packsInContainer = container1.unloadContainerFully();
        assertEquals(0, container1.getNumberOfPacks());

        assertEquals(true, packsInContainer.contains(pack1)); 
        assertEquals(true, packsInContainer.contains(pack2)); 
    }
    
    @Test
    public void testGetNumberOfPacks(){
        Container container1 = new Container(1);
        Pack pack1 = new Pack(1, "A", 1, 1, 1);
        Pack pack2 = new Pack(2, "B", 2, 2, 2);

        container1.loadContainer(pack1);
        assertEquals(1, container1.getNumberOfPacks());

        container1.loadContainer(pack2);
        assertEquals(2, container1.getNumberOfPacks()); 
        
        container1.unloadContainer(pack1.getCode(), pack1.getQuantity());  
        assertEquals(1, container1.getNumberOfPacks());
        
        container1.unloadContainer(pack2.getCode(), pack2.getQuantity()); 
        assertEquals(0, container1.getNumberOfPacks());
    }

    @Test
    public void testRegisterContainer(){
        Container container1 = new Container(1);
        Container container2 = new Container(2);
        Container.clearRegistedContainers();
        
        assertEquals(0, container1.getNumberOfRegistedContainers());
        Container.registerContainer(container1);
        assertEquals(1, container1.getNumberOfRegistedContainers());
        
        Container.registerContainer(container2);
        assertEquals(2, container1.getNumberOfRegistedContainers());
        
    }
    
    @Test
    public void testGetNumberOfRegistedContainers(){
        Container container1 = new Container(1);
        Container container2 = new Container(2);
        Container.clearRegistedContainers();
        
        assertEquals(0, container1.getNumberOfRegistedContainers());
        Container.registerContainer(container1);
        assertEquals(1, container1.getNumberOfRegistedContainers());
        
        Container.registerContainer(container2);
        assertEquals(2, container1.getNumberOfRegistedContainers());
    }
    
    @Test
    public void testUnregisterContainer(){
        Container container1 = new Container(1);
        Container container2 = new Container(2);
        Container.clearRegistedContainers();
        
        Container.registerContainer(container1);
        Container.registerContainer(container2);
        
        assertEquals(2, container1.getNumberOfRegistedContainers());
        container1.unregisterContainer(container1);
        assertEquals(1, container1.getNumberOfRegistedContainers());
        
        container1.unregisterContainer(container2);
        assertEquals(0, container1.getNumberOfRegistedContainers());
    }    
}
