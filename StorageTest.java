

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A classe de teste StorageTest.
 *
 * @author 160221052  Ricardo Carmo.
 * @author 160221072  Miguel Lobato.
 * @version 31/1/2017
 */
public class StorageTest
{
    /**
     * Construtor default para a classe de teste StorageTest
     */
    public StorageTest()
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
    public void testImportPack(){
        Storage storage = new Storage();
        Pack pack1 = new Pack(1, "A", 1, 1, 10);        
        Pack pack2 = new Pack(2, "B", 1, 1, 10);
        
        assertEquals(0, storage.getNumberOfStoredPacks());
        
        storage.importPack(pack1);
        assertEquals(1, storage.getNumberOfStoredPacks());
        
        storage.importPack(null);
        assertEquals(1, storage.getNumberOfStoredPacks());
        
        storage.importPack(pack2);
        assertEquals(2, storage.getNumberOfStoredPacks());
    }
    
    @Test
    public void testExportPack(){
        Storage storage = new Storage();
        Pack pack1 = new Pack(1, "A", 1, 1, 15);   
        storage.importPack(pack1);
        
        assertEquals(1, storage.getNumberOfStoredPacks());
        
        Pack pack2 = storage.exportPack(pack1.getCode(), 5);        
        assertEquals(1, pack2.getCode(), 0.0001);
        assertEquals(5, pack2.getQuantity(), 0.0001);
        
        assertEquals(1, storage.getNumberOfStoredPacks());
        
        Pack pack3 = storage.exportPack(pack1.getCode(), 10);        
        assertEquals(1, pack3.getCode(), 0.0001);
        assertEquals(10, pack3.getQuantity(), 0.0001);
        
        assertEquals(0, storage.getNumberOfStoredPacks());
    }
    
    @Test
    public void testGetNumberOfStoredPacks(){
        Storage storage = new Storage();
        Pack pack1 = new Pack(1, "A", 1, 1, 10);        
        Pack pack2 = new Pack(2, "B", 1, 1, 10);
        
        assertEquals(0, storage.getNumberOfStoredPacks());
        
        storage.importPack(pack1);
        assertEquals(1, storage.getNumberOfStoredPacks());
        
        storage.importPack(null);
        assertEquals(1, storage.getNumberOfStoredPacks());
        
        storage.importPack(pack2);
        assertEquals(2, storage.getNumberOfStoredPacks());
    }
}
