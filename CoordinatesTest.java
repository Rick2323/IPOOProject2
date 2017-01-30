

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class CoordinatesTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CoordinatesTest
{
    /**
     * Default constructor for test class CoordinatesTest
     */
    public CoordinatesTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void testGetLatitude()
    {
        Coordinates coordina1 = new Coordinates(12.5, 15.2);
        assertEquals(12.5, coordina1.getLatitude(), 0.0001);
        
        Coordinates coordina2 = new Coordinates(100, 50);
        assertEquals(0.0, coordina2.getLatitude(), 0.0001);
        
        Coordinates coordina3 = new Coordinates(-100, 0);
        assertEquals(0.0, coordina3.getLatitude(), 0.0001);
        
        Coordinates coordina4 = new Coordinates(90, 0);
        assertEquals(90.0, coordina4.getLatitude(), 0.0001);
        
        Coordinates coordina5 = new Coordinates(-90.0, 0);
        assertEquals(-90.0, coordina5.getLatitude(), 0.0001);
    }
    
    @Test
    public void testGetLongitude()
    {
        Coordinates coordina1 = new Coordinates (0.0, 90.0);
        assertEquals(90.0, coordina1.getLongitude(), 0.0001);
        
        Coordinates coordina2 = new Coordinates (0.0, -180.0);
        assertEquals(-180.0, coordina2.getLongitude(), 0.0001);
        
        Coordinates coordina3 = new Coordinates (0.0, 180.0);
        assertEquals(180.0, coordina3.getLongitude(), 0.0001);
        
        Coordinates coordina4 = new Coordinates (0.0, -200.0);
        assertEquals(0.0, coordina4.getLongitude(), 0.0001);
        
        Coordinates coordina5 = new Coordinates (0.0, 200.0);
        assertEquals (0.0, coordina5.getLongitude(), 0.0001);
    }
    
    @Test
    public void testSetCoordinates()
    {
        Coordinates coordina1 = new Coordinates (90.0, 180.0);
        assertEquals(90.0, coordina1.getLatitude(), 0.0001);
        assertEquals(180.0, coordina1.getLongitude(), 0.0001);
        
        //setCoordinates(lat, lon);
        coordina1.setCoordinates(0.0, 0.0);
        assertEquals(0.0, coordina1.getLatitude(), 0.0001);
        assertEquals(0.0, coordina1.getLongitude(), 0.0001);
        
        
        coordina1.setCoordinates(-90.0, -180.0);
        assertEquals(-90.0, coordina1.getLatitude(), 0.0001);
        assertEquals(-180.0, coordina1.getLongitude(), 0.0001);
        
        
        coordina1.setCoordinates(90.0, 180.0);
        assertEquals(90.0, coordina1.getLatitude(), 0.0001);
        assertEquals(180.0, coordina1.getLongitude(), 0.0001);
        
        
        coordina1.setCoordinates(-100.0, -200.0);
        assertEquals(90.0, coordina1.getLatitude(), 0.0001);
        assertEquals(180.0, coordina1.getLongitude(), 0.0001);
        
        
        coordina1.setCoordinates(100.0, 200.0);
        assertEquals(90.0, coordina1.getLatitude(), 0.0001);
        assertEquals(180.0, coordina1.getLongitude(), 0.0001);
    }
    
    @Test
    public void testDistanceTravelled()
    {
        Coordinates pos1 = new Coordinates(0.0, 0.0);
        Coordinates pos2 = new Coordinates(1.0, 1.0);
        Coordinates pos3 = new Coordinates(-1.0, -1.0);
        
        //mandar ir p1->p2     p1->p3      p2->p3      distanceTravelled(double newLatitude, double newLongitude)
        
        assertEquals(157.1474, pos1.distanceTravelled(pos2.getLatitude(),pos2.getLongitude()), 0.0001);
        assertEquals(157.1474, pos1.distanceTravelled(pos3.getLatitude(),pos3.getLongitude()), 0.0001);
        assertEquals(314.2948, pos2.distanceTravelled(pos3.getLatitude(),pos3.getLongitude()), 0.0001);  
        
        
        
        
    }
    @Test
    public void testMoveTo(){
    
        Coordinates pos1 = new Coordinates(0.0, 0.0);
        Coordinates pos2 = new Coordinates(1.0, 1.0);
        Coordinates pos3 = new Coordinates(-1.0, -1.0);
        
        /*
           pos1->pos2
           pos1(Lat)==1.0
           pos1(Lon)==1.0
           */ 
          pos1.moveTo(pos2.getLatitude(),pos2.getLongitude());
          assertEquals(1.0, pos1.getLatitude(),0.0001);
          assertEquals(1.0, pos1.getLongitude(),0.0001);
          
          pos1.moveTo(pos3.getLatitude(), pos3.getLongitude());
          assertEquals(-1.0, pos1.getLatitude(),0.0001);
          assertEquals(-1.0, pos1.getLongitude(),0.0001);
          
          pos2.moveTo(pos3.getLatitude(), pos3.getLongitude());
          assertEquals(-1.0, pos2.getLatitude(),0.0001);
          assertEquals(-1.0, pos2.getLongitude(),0.0001);
    
    }
    @Test
    public void testCompareCoordinates()
    {
        Lorry lorry = new Lorry("Veiculo1", 1, 0.0, 0.0);
        Coordinates pos1 = new Coordinates(0.0, 0.0);
        Coordinates pos2 = new Coordinates(1.0, 1.0);
        
        //public boolean compareCoordinates(Lorry lorry){
            
        assertEquals(true, pos1.compareCoordinates(lorry));
        assertEquals(false, pos2.compareCoordinates(lorry));
    }/*Lorry(String designation, int code, double latitude, double longitude)*/
}

