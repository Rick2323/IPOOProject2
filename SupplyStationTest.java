

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A classe de teste SupplyStationTest.
 *
 * @author  (seu nome)
 * @version (um número de versão ou data)
 */
public class SupplyStationTest
{
    /**
     * Construtor default para a classe de teste SupplyStationTest
     */
    public SupplyStationTest()
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
        SupplyStation supSta1 = new SupplyStation("A", 0, 0, 5);
        SupplyStation supSta2 = new SupplyStation(null, 0, 0, 5);
        
        assertEquals("A", supSta1.getName());
        assertEquals("", supSta2.getName());
        
        supSta1.setName(null);
        supSta2.setName("B");
        
        assertEquals("A", supSta1.getName());
        assertEquals("B", supSta2.getName());
        
        supSta1.setName("C");
        assertEquals("C", supSta1.getName());
    }
    
    @Test
    public void testGetName(){
        SupplyStation supSta1 = new SupplyStation("A", 0, 0, 5);
        SupplyStation supSta2 = new SupplyStation(null, 0, 0, 5);
        
        assertEquals("A", supSta1.getName());
        assertEquals("", supSta2.getName());
        
        supSta1.setName(null);
        supSta2.setName("B");
        
        assertEquals("A", supSta1.getName());
        assertEquals("B", supSta2.getName());
        
        supSta1.setName("C");
        assertEquals("C", supSta1.getName());
    }
    
    @Test
    public void testGetLatitude(){
        SupplyStation supSta1 = new SupplyStation("A", 1, 0, 5);
        SupplyStation supSta2 = new SupplyStation(null, 3, 0, 5);
        
        assertEquals(1, supSta1.getLatitude(), 0.0001);
        assertEquals(3, supSta2.getLatitude(), 0.0001);
    }
    
    @Test
    public void testGetLongitude(){
        SupplyStation supSta1 = new SupplyStation("A", 0, 2, 5);
        SupplyStation supSta2 = new SupplyStation(null, 0, 4, 5);
        
        assertEquals(2, supSta1.getLongitude(), 0.0001);
        assertEquals(4, supSta2.getLongitude(), 0.0001);
    }
    
    @Test
    public void testUnloadLorry(){
        SupplyStation supSta1 = new SupplyStation("A", 0, 0, 5);
        Lorry lorry1 = new Lorry("Veículo1", 1, 0, 0);
        Container container1 = new Container(1);
        Container container2 = new Container(2);        
        Pack pack1 = new Pack(1, "A", 1, 1, 10);
        Pack pack2 = new Pack(2, "B", 2, 2, 20);
        
        container1.loadContainer(pack1);
        container1.loadContainer(pack2);
        
        assertEquals(false, lorry1.hasContainer());       
        
        lorry1.loadContainer(container1);        
        assertEquals(true, lorry1.hasContainer());
        
        supSta1.unloadLorry(lorry1, pack1.getCode(), pack1.getQuantity(), true, false);       
        assertEquals(true, lorry1.hasContainer());
        
        supSta1.unloadLorry(lorry1, pack2.getCode(), pack2.getQuantity(), false, false);       
        assertEquals(false, lorry1.hasContainer());
    }
    
    @Test
    public void testLoadLorry(){
        SupplyStation supSta1 = new SupplyStation("A", 0, 0, 5);
        Lorry lorry1 = new Lorry("Veículo1", 1, 0, 0);        
        Pack pack1 = new Pack(1, "A", 1, 1, 1);
        
        supSta1.addPackToStorage(pack1.getCode(), 100);
        
        assertEquals(false, lorry1.hasContainer());       
        
        supSta1.loadLorry(lorry1, pack1.getCode(), 25);
        assertEquals(true, lorry1.hasContainer());
        assertEquals(1, lorry1.getContainer().getNumberOfPacks());
        
        supSta1.loadLorry(lorry1, pack1.getCode(), 25);
        assertEquals(true, lorry1.hasContainer());
        assertEquals(2, lorry1.getContainer().getNumberOfPacks());
    }
    
    @Test
    public void testAddPackToStorage(){
        SupplyStation supSta1 = new SupplyStation("A", 0, 0, 5);
        Lorry lorry1 = new Lorry("Veículo1", 1, 0, 0);        
        Pack pack1 = new Pack(1, "A", 1, 1, 1);
        
        supSta1.addPackToStorage(pack1.getCode(), 100);
        
        assertEquals(false, lorry1.hasContainer());       
        
        supSta1.loadLorry(lorry1, pack1.getCode(), 25);
        assertEquals(true, lorry1.hasContainer());
        assertEquals(1, lorry1.getContainer().getNumberOfPacks());
    }
}

