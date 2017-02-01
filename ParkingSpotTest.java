
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A classe de teste ParkingSpotTest.
 *
 * @author 160221052  Ricardo Carmo.
 * @author 160221072  Miguel Lobato.
 * @version 31/1/2017
 */
public class ParkingSpotTest
{
    /**
     * Construtor default para a classe de teste ParkingSpotTest
     */
    public ParkingSpotTest()
    {
    }

    @Before
    public void setUp()
    {
    }

    public void tearDown()
    {
    }

    @Test
    public void testGetParked(){
        Lorry lorry1 = new Lorry("A", 1, 0, 0);
        Container container1 = new Container (1);
        
        ParkingSpot parkingSpot1 = new ParkingSpot(lorry1);
        assertEquals(true, parkingSpot1.getParked().equals(lorry1));
        
        ParkingSpot parkingSpot2 = new ParkingSpot(container1);
        assertEquals(true, parkingSpot2.getParked().equals(container1));        
    }
}
