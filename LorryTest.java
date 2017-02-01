import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class LorryTest.
 *
 * @author 160221052  Ricardo Carmo.
 * @author 160221072  Miguel Lobato.
 * @version 31/1/2017
 */
public class LorryTest
{
    LorryManagement lorryManagement = new LorryManagement();
    public LorryTest()
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
    public void testRegisterLorry(){//metodo usado no construtor dos Lorry
        Lorry lorry1 = new Lorry("A", 1, 0, 0);

        assertEquals(true, lorryManagement.lorryIsRegisted(lorry1.getCode()));
    }

    @Test
    public void testGetCode()
    {
        Lorry lorry1 = new Lorry("Veiculo1", 1, 0.0, 0.0);
        assertEquals(1, lorry1.getCode());

        Lorry lorry2 = new Lorry("Veiculo2", 2, 1.0, 1.0);
        assertEquals(2, lorry2.getCode());

        Lorry lorry3 = new Lorry("Veiculo3", 0, 1.0, 0.0);
        assertEquals(0, lorry3.getCode());

        Lorry lorry4 = new Lorry("Veiculo4", -5, 0.0, 0.0);
        assertEquals(0, lorry4.getCode());
    }

    @Test
    public void testGetDesignation(){
        Lorry lorry1 = new Lorry("A", 1, 0.0, 0.0);        
        assertEquals("A", lorry1.getDesignation());

        Lorry lorry2 = new Lorry("B", 2, 1.0, 1.0);        
        assertEquals("B", lorry2.getDesignation());
    }

    @Test
    public void testSetDesignation()
    {
        Lorry lorry1 = new Lorry("A", 1, 0.0, 0.0);
        assertEquals("A", lorry1.getDesignation());

        Lorry lorry2 = new Lorry(null, 2, 0.0, 0.0);        
        assertEquals("", lorry2.getDesignation());

        lorry1.setDesignation("");                
        assertEquals("", lorry1.getDesignation());

        lorry2.setDesignation("B");        
        assertEquals("B", lorry2.getDesignation());

        lorry1.setDesignation("A");
        assertEquals("A", lorry1.getDesignation());
    }

    @Test
    public void testSetParked()
    {
        Lorry lorry2 = new Lorry("Veiculo1", 1, 0.0, 0.0);
        assertEquals(false, lorry2.isParked());

        lorry2.setParked();
        assertEquals(true, lorry2.isParked());

        Lorry lorry3 = new Lorry("Veiculo2", 2, 1.1, 1.1);
        assertEquals(false, lorry3.isParked());

        lorry3.setParked();
        assertEquals(true, lorry3.isParked());
    }

    @Test
    public void testIsParked()
    {
        Lorry lorry3 = new Lorry("Veiculo1", 1, 0.0, 0.0);
        assertEquals(false, lorry3.isParked());

        lorry3.setParked();
        assertEquals(true, lorry3.isParked());
    }

    @Test
    public void testLoadContainer(){
        Lorry lorry1 = new Lorry("A", 1, 0.0, 0.0);
        Container container1 = new Container(1);

        assertEquals(false, lorry1.hasContainer());

        lorry1.loadContainer(container1);
        assertEquals(true, lorry1.hasContainer());
    }

    @Test
    public void testUnloadContainer(){
        Lorry lorry1 = new Lorry("A", 1, 0.0, 0.0);
        Container container1 = new Container(1);

        lorry1.loadContainer(container1);
        assertEquals(true, lorry1.hasContainer());

        Container container2 = lorry1.unloadContainer();         
        assertEquals(false, lorry1.hasContainer());

        assertEquals(true, container2.equals(container1));
    }

    @Test
    public void testInspection(){
        Lorry lorry1 = new Lorry("A", 1, 0.0, 0.0);

        assertEquals(0, lorry1.getNumberOfInspections());
        assertEquals((long)0, (long)lorry1.getKilometersSinceLastInspection());

        lorry1.moveLorry(0, 1);    
        assertEquals((long)111.12, (long)lorry1.getKilometersSinceLastInspection());

        lorry1.inspection();
        assertEquals(1, lorry1.getNumberOfInspections());
        assertEquals((long)0, (long)lorry1.getKilometersSinceLastInspection());
    }

    @Test
    public void testGetNumberOfInspections(){
        Lorry lorry1 = new Lorry("A", 1, 0.0, 0.0);

        assertEquals(0, lorry1.getNumberOfInspections());

        lorry1.inspection();
        assertEquals(1, lorry1.getNumberOfInspections());
    }

    @Test
    public void testGetKilometersSinceLastInspection(){
        Lorry lorry1 = new Lorry("A", 1, 0.0, 0.0);

        assertEquals(0, lorry1.getNumberOfInspections());
        assertEquals((long)0, (long)lorry1.getKilometersSinceLastInspection());

        lorry1.moveLorry(0, 1);    
        assertEquals((long)111.12, (long)lorry1.getKilometersSinceLastInspection());

        lorry1.inspection();
        assertEquals((long)0, (long)lorry1.getKilometersSinceLastInspection());
    }

    @Test
    public void testMoveLorry(){        
        Lorry lorry1 = new Lorry("A", 1, 0.0, 0.0);

        assertEquals((long)0, (long)lorry1.getLatitude());
        assertEquals((long)0, (long)lorry1.getLongitude());
        assertEquals((long)0, (long)lorry1.getKilometersSinceLastInspection());

        lorry1.moveLorry(0, 1);
        assertEquals((long)0, (long)lorry1.getLatitude());
        assertEquals((long)1, (long)lorry1.getLongitude());
        assertEquals((long)111.12, (long)lorry1.getKilometersSinceLastInspection());

        lorry1.moveLorry(1, 1);
        assertEquals((long)1, (long)lorry1.getLatitude());
        assertEquals((long)1, (long)lorry1.getLongitude());
        assertEquals((long)222.24, (long)lorry1.getKilometersSinceLastInspection());
    }

    @Test
    public void testGetLatitude(){
        Lorry lorry1 = new Lorry("A", 1, 0.0, 0.0);        
        assertEquals((long)0, (long)lorry1.getLatitude());

        lorry1.moveLorry(5, 0);
        assertEquals((long)5, (long)lorry1.getLatitude());

        lorry1.moveLorry(30, 0);
        assertEquals((long)30, (long)lorry1.getLatitude());

        lorry1.moveLorry(-1000, 0);
        assertEquals((long)30, (long)lorry1.getLatitude());
    }
    
    @Test
    public void testGetLongitude(){
        Lorry lorry1 = new Lorry("A", 1, 0.0, 0.0);        
        assertEquals((long)0, (long)lorry1.getLongitude());

        lorry1.moveLorry(0, 5);
        assertEquals((long)5, (long)lorry1.getLongitude());

        lorry1.moveLorry(0, 30);
        assertEquals((long)30, (long)lorry1.getLongitude());

        lorry1.moveLorry(0, 1000);
        assertEquals((long)30, (long)lorry1.getLongitude());
    }
    
    @Test
    public void testHasContainer(){
        Lorry lorry1 = new Lorry("A", 1, 0.0, 0.0);
        Container container1 = new Container(1);
        
        assertEquals(false, lorry1.hasContainer());
        
        lorry1.loadContainer(container1);
        assertEquals(true, lorry1.hasContainer());
        
        lorry1.unloadContainer();
        assertEquals(false, lorry1.hasContainer());
    }
    
    @Test
    public void testGetContainer(){        
        Lorry lorry1 = new Lorry("A", 1, 0.0, 0.0);
        Container container1 = new Container(1);
        
        assertEquals(null, lorry1.getContainer());
        
        lorry1.loadContainer(container1);
        assertEquals(true, container1.equals(lorry1.getContainer()));
    }
    
    @Test
    public void testGetTotalKilometers(){
        Lorry lorry1 = new Lorry("A", 1, 0.0, 0.0);

        assertEquals((long)0, (long)lorry1.getTotalKilometers());

        lorry1.moveLorry(0, 1);
        assertEquals((long)111.12, (long)lorry1.getTotalKilometers());

        lorry1.inspection();
        
        assertEquals((long)111.12, (long)lorry1.getTotalKilometers());
        
        lorry1.moveLorry(1, 1);
        assertEquals((long)222.24, (long)lorry1.getTotalKilometers());
    }
    
    @Test
    public void testIsNew(){
        Lorry lorry1 = new Lorry("A", 1, 0, 0);

        assertEquals(true, lorry1.isNew());

        lorry1.moveLorryToReach(50000); 
        System.out.println(lorry1.getTotalKilometers());
        assertEquals(false, lorry1.isNew());
    }
    
    @Test
    public void testIsOld(){
        Lorry lorry1 = new Lorry("A", 1, 0, 0);

        assertEquals(false, lorry1.isOld());

        lorry1.moveLorryToReach(1000000); 
        System.out.println(lorry1.getTotalKilometers());
        assertEquals(true, lorry1.isOld());
    }
    
    @Test
    public void testNeedsInspection(){
        Lorry lorry1 = new Lorry("A", 1, 0, 0);

        assertEquals(false, lorry1.needsInspection());

        lorry1.moveLorryToReach(10000); 
        System.out.println(lorry1.getTotalKilometers());
        assertEquals(true, lorry1.needsInspection());
    }
    
    @Test
    public void testMoveLorryToReach(){
        Lorry lorry1 = new Lorry("A", 1, 0, 0);

        assertEquals(false, (lorry1.getTotalKilometers() >= 5555));

        lorry1.moveLorryToReach(5555); 
        System.out.println(lorry1.getTotalKilometers());
        assertEquals(true, (lorry1.getTotalKilometers() >= 5555));
    }
}

