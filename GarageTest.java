
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A classe de teste GarageTest.
 *
 * @author  (seu nome)
 * @version (um número de versão ou data)
 */
public class GarageTest
{
    /**
     * Construtor default para a classe de teste GarageTest
     */
    public GarageTest()
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
        Garage garage1 = new Garage("A", 0, 0, 3);
        Garage garage2 = new Garage(null, 1, 1, 3);

        assertEquals("A", garage1.getName());
        assertEquals("", garage2.getName());
        
        garage1.setName(null);
        garage2.setName("B");
        
        assertEquals("A", garage1.getName());
        assertEquals("B", garage2.getName());
        
        garage1.setName("C");
        assertEquals("C", garage1.getName());
    }
    
    @Test
    public void testGetName(){
        Garage garage1 = new Garage("A", 0, 0, 3);
        Garage garage2 = new Garage(null, 1, 1, 3);

        assertEquals("A", garage1.getName());
        assertEquals("", garage2.getName());
        
        garage1.setName(null);
        garage2.setName("B");
        
        assertEquals("A", garage1.getName());
        assertEquals("B", garage2.getName());
        
        garage1.setName("C");
        assertEquals("C", garage1.getName());
    }
    
    @Test
    public void testGetObjectFromSlot(){
        Garage garage1 = new Garage("A", 0, 0, 3);
        Lorry lorry1 = new Lorry("Veículo1", 1, 0.0, 0.0);
        Lorry lorry2 = new Lorry("Veículo2", 2, 1.0, 1.0);
        Lorry lorry3 = new Lorry("Veículo3", 3, 0.0, 0.0);
        
        garage1.parkLorry(lorry1, 2);
        assertEquals(true, lorry1.equals(garage1.getObjectFromSlot(2)));
    
        garage1.parkLorry(lorry2, 1);//camiao tem coordenadas diferentes da garagem
        assertEquals(false, lorry2.equals(garage1.getObjectFromSlot(1)));
    
        lorry2.moveLorry(0.0, 0.0);        
        garage1.parkLorry(lorry2, 1);//coordenadas iguais à garagem
        assertEquals(true, lorry2.equals(garage1.getObjectFromSlot(1)));
        
        garage1.parkLorry(lorry3, 2);
        assertEquals(false, lorry3.equals(garage1.getObjectFromSlot(2)));//o lugar já está ocupado por isso não estaciona lá nada
        assertEquals(true, lorry1.equals(garage1.getObjectFromSlot(2)));//mas mantêm o que lá estiver
        
        garage1.parkLorry(lorry3, 0);//estaciona num lugar livre
        assertEquals(true, lorry3.equals(garage1.getObjectFromSlot(3)));//que será o terceiro
    }
    
    @Test
    public void testParkLorry(){
        Garage garage1 = new Garage("A", 0, 0, 3);
        Lorry lorry1 = new Lorry("Veículo1", 1, 0.0, 0.0);
        Lorry lorry2 = new Lorry("Veículo2", 2, 1.0, 1.0);
        Lorry lorry3 = new Lorry("Veículo3", 3, 0.0, 0.0);
        
        garage1.parkLorry(lorry1, 2);
        assertEquals(true, lorry1.equals(garage1.getObjectFromSlot(2)));
    
        garage1.parkLorry(lorry2, 1);//camiao tem coordenadas diferentes da garagem
        assertEquals(false, lorry2.equals(garage1.getObjectFromSlot(1)));
    
        lorry2.moveLorry(0.0, 0.0);        
        garage1.parkLorry(lorry2, 1);//coordenadas iguais à garagem
        assertEquals(true, lorry2.equals(garage1.getObjectFromSlot(1)));
        
        garage1.parkLorry(lorry3, 2);
        assertEquals(false, lorry3.equals(garage1.getObjectFromSlot(2)));//o lugar já está ocupado por isso não estaciona lá nada
        assertEquals(true, lorry1.equals(garage1.getObjectFromSlot(2)));//mas mantêm o que lá estiver
        
        garage1.parkLorry(lorry3, 0);//estaciona num lugar livre
        assertEquals(true, lorry3.equals(garage1.getObjectFromSlot(3)));//que será o terceiro
    }
    
    @Test
    public void testUnparkLorry(){
        Garage garage1 = new Garage("A", 0, 0, 3);
        Lorry lorry1 = new Lorry("Veículo1", 1, 0.0, 0.0);
        Lorry lorry2 = new Lorry("Veículo2", 2, 0.0, 0.0);
        
        garage1.parkLorry(lorry1, 0);
        assertEquals(true, lorry1.equals(garage1.getObjectFromSlot(1)));
        
        garage1.parkLorry(lorry2, 0);
        assertEquals(true, lorry2.equals(garage1.getObjectFromSlot(2)));
        
        assertEquals(true, lorry1.equals(garage1.unparkLorry(1)));
        assertEquals(true, lorry2.equals(garage1.unparkLorry(2)));
    }
    
    @Test
    public void testInspection(){
        Garage garage1 = new Garage("A", 0, 1, 3);
        Lorry lorry1 = new Lorry("Veículo1", 1, 0, 0);
        
        assertEquals(0, lorry1.getNumberOfInspections());
        assertEquals(0, lorry1.getKilometersSinceLastInspection(), 0.0001);
        
        lorry1.moveLorry(0, 1);
        assertEquals(0, lorry1.getNumberOfInspections());
        assertEquals(111.12, lorry1.getKilometersSinceLastInspection(), 0.0001);
        
        garage1.parkLorry(lorry1, 0);
        assertEquals(true, lorry1.equals(garage1.getObjectFromSlot(1)));
        
        garage1.inspection(1);
        
        assertEquals(1, lorry1.getNumberOfInspections());
        assertEquals(0, lorry1.getKilometersSinceLastInspection(), 0.0001);        
    }
    
    @Test
    public void testSwitchOrLoadContainerToLorry(){
        Garage garage1 = new Garage("A", 0, 0, 2);
        Lorry lorry1 = new Lorry("Veículo1", 1, 0, 0);
        Lorry lorry2 = new Lorry("Veículo2", 2, 0, 0);
        Container container1 = new Container(1);
        Container container2 = new Container(2);
        Container container3 = new Container(3);
        
        lorry1.loadContainer(container1);        
        garage1.unloadLorry(lorry1);//está a por os contentores na garagem
        
        lorry1.loadContainer(container2);        
        garage1.unloadLorry(lorry1);//está a por os contentores na garagem
        
        lorry2.loadContainer(container3);   
        
        assertEquals(false, lorry1.hasContainer());
        assertEquals(true, lorry2.hasContainer());
        
        
        garage1.switchOrLoadContainerToLorry(lorry1, container1.getCode());
        assertEquals(true, lorry1.hasContainer());
        assertEquals(true, lorry1.getContainer().equals(container1));
        
        assertEquals(true, lorry2.getContainer().equals(container3));
        garage1.switchOrLoadContainerToLorry(lorry2, container2.getCode());
        assertEquals(true, lorry2.getContainer().equals(container2));
    }
    
    @Test
    public void testUnloadLorry(){
        Garage garage1 = new Garage("A", 0, 0, 2);
        Lorry lorry1 = new Lorry("Veículo1", 1, 0, 0);
        Container container1 = new Container(1);
        Container container2 = new Container(2);
        
        assertEquals(false, lorry1.hasContainer());
        
        lorry1.loadContainer(container1);        
        assertEquals(true, lorry1.hasContainer());
        
        garage1.unloadLorry(lorry1);       
        assertEquals(false, lorry1.hasContainer());
        
        lorry1.loadContainer(container2);        
        assertEquals(true, lorry1.hasContainer());
        
        garage1.unloadLorry(lorry1);
        assertEquals(false, lorry1.hasContainer());
    }
}
