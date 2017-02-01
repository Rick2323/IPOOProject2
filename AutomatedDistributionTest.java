

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A classe de teste AutomatedDistributionTest.
 *
 * @author  (seu nome)
 * @version (um número de versão ou data)
 */
public class AutomatedDistributionTest
{
    /**
     * Construtor default para a classe de teste AutomatedDistributionTest
     */
    public AutomatedDistributionTest()
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
    public void testDistribute(){
        Lorry lorry1 = new Lorry("A", 1, 1, 1);
        Shop shop1 = new Shop("B", 2, 2);
        SupplyStation supplyStation1 = new SupplyStation("C", 3, 3, 3);
        Pack pack1 = new Pack(4, "D", 4, 4, 4);
        shop1.addProductToSell(pack1.getCode());
        AutomatedDistribution autoDistribution = new AutomatedDistribution();   
        
        assertEquals(null, shop1.sell(pack1.getCode(), 100));
        
        autoDistribution.distribute(lorry1, supplyStation1, shop1, pack1.getCode(), 100, false);  
        
        Pack pack2 = shop1.sell(pack1.getCode(), 100);
        
        assertEquals(true, pack1.getCode().equals(pack2.getCode()));
    }
}
