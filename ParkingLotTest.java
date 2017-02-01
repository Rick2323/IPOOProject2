
import java.util.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A classe de teste ParkingLotTest.
 *
 * @author 160221052  Ricardo Carmo.
 * @author 160221072  Miguel Lobato.
 * @version 31/1/2017
 */
public class ParkingLotTest
{
    /**
     * Construtor default para a classe de teste ParkingLotTest
     */
    public ParkingLotTest()
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
    public void testPark(){
        ParkingLot parking = new ParkingLot(3);
        Lorry lorry1 = new Lorry("A", 1, 0, 0);
        Container container1 = new Container(1); 
        Container container2 = new Container(2); 
        
        assertEquals(null, parking.getParkedObject()); //devolve o 1º objeto que encontrar e devolve null se não encontrar nenhum 
        
        parking.park(3, container1);
        assertEquals(true, container1.equals(parking.getObjectFromSlot(3)));//lugar3
        
        parking.park(1, container2);
        assertEquals(true, container2.equals(parking.getObjectFromSlot(1)));//lugar1
        
        parking.park(0, lorry1);
        assertEquals(true, lorry1.equals(parking.getObjectFromSlot(2)));//lugar2 era o próximo disponível
    }
    
    @Test
    public void testHasSpace(){
        ParkingLot parking = new ParkingLot(1);
        Container container1 = new Container(1);  
        
        assertEquals(true, parking.hasSpace());
        
        parking.park(0, container1);
        assertEquals(false, parking.hasSpace());
        
        parking.unpark(1);
        assertEquals(true, parking.hasSpace());
    }
    
    @Test
    public void testGetParkedObject(){
        ParkingLot parking = new ParkingLot(3);
        Lorry lorry1 = new Lorry("A", 1, 0, 0);
        Container container1 = new Container(1); 
        Container container2 = new Container(2); 
        
        assertEquals(null, parking.getParkedObject()); //devolve o 1º objeto que encontrar e devolve null se não encontrar nenhum 
        
        parking.park(3, container1);
        assertEquals(true, container1.equals(parking.getObjectFromSlot(3)));//lugar3
        
        parking.park(1, container2);
        assertEquals(true, container2.equals(parking.getObjectFromSlot(1)));//lugar1
        
        parking.park(0, lorry1);
        assertEquals(true, lorry1.equals(parking.getObjectFromSlot(2)));//lugar2 era o próximo disponível
    }
    
    @Test
    public void testUnpark(){
        ParkingLot parking = new ParkingLot(3);
        Lorry lorry1 = new Lorry("A", 1, 0, 0);
        Container container1 = new Container(1); 
        Container container2 = new Container(2); 
        
        parking.park(0, lorry1);
        parking.park(0, container1);
        parking.park(0, container2);
        
        assertEquals(true, lorry1.equals(parking.unpark(1)));
        assertEquals(true, container1.equals(parking.unpark(2)));
        assertEquals(true, container2.equals(parking.unpark(3)));
        
    }
    
    @Test
    public void testUnloadContainerSingle(){
        ParkingLot parking = new ParkingLot(3);
        Container container1 = new Container(1); 
        Pack pack1 = new Pack(1, "A", 1, 1, 10);
        Pack pack2 = new Pack(2, "B", 2, 2, 20);
        
        parking.park(0, container1);
        
        assertEquals(0, container1.getNumberOfPacks());
        
        container1.loadContainer(pack1);
        container1.loadContainer(pack2);
        
        assertEquals(2, container1.getNumberOfPacks());
        
        Pack pack3 = parking.unloadContainerSingle(container1.getCode(), pack1.getCode(), pack1.getQuantity());
        assertEquals(true, pack1.getCode().equals(pack3.getCode()));
        assertEquals(true, pack1.getQuantity() == pack3.getQuantity());
        assertEquals(1, container1.getNumberOfPacks());
        
        Pack pack4 = parking.unloadContainerSingle(container1.getCode(), pack2.getCode(), pack2.getQuantity());
        assertEquals(true, pack2.getCode().equals(pack4.getCode()));
        assertEquals(true, pack2.getQuantity() == pack4.getQuantity());
        assertEquals(0, container1.getNumberOfPacks());
    }
    
    @Test
    public void testUnloadContainerFully(){
        ParkingLot parking = new ParkingLot(3);
        Container container1 = new Container(1); 
        Pack pack1 = new Pack(1, "A", 1, 1, 10);
        Pack pack2 = new Pack(2, "B", 2, 2, 20);
        
        parking.park(0, container1);
        
        assertEquals(0, container1.getNumberOfPacks());
        
        container1.loadContainer(pack1);
        container1.loadContainer(pack2);
        
        assertEquals(2, container1.getNumberOfPacks());
        
        ArrayList<Pack> tempArray = parking.unloadContainerFully(container1.getCode());
        
        assertEquals(0, container1.getNumberOfPacks());
        
        assertEquals(true, tempArray.contains(pack1));
        assertEquals(true, tempArray.contains(pack2));
    }
    
    @Test
    public void testLoadContainer(){
        ParkingLot parking = new ParkingLot(3);
        Container container1 = new Container(1); 
        Pack pack1 = new Pack(1, "A", 1, 1, 10);
        
        parking.park(0, container1);
        
        assertEquals(0, container1.getNumberOfPacks());
        
        parking.loadContainer(container1.getCode(), pack1);
        
        assertEquals(1, container1.getNumberOfPacks());
    }
    
    @Test
    public void testContainerExists(){
        ParkingLot parking = new ParkingLot(3);
        Container container1 = new Container(1); 
        Container container2 = new Container(2); 
        Container container3 = new Container(3);
        
        assertEquals(null, parking.getParkedObject()); //devolve o 1º objeto que encontrar e devolve null se não encontrar nenhum 
        
        parking.park(3, container1);
        assertEquals(3, parking.containerExists(container1.getCode()));//lugar3
        
        parking.park(1, container2);
        assertEquals(1, parking.containerExists(container2.getCode()));//lugar1
        
        parking.park(0, container3);
        assertEquals(2, parking.containerExists(container3.getCode()));//lugar2, era o próximo disponível
    }
}
