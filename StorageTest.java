

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
        assertEquals((long)1, (long)pack2.getCode());
        assertEquals((long)5, (long)pack2.getQuantity());
        
        assertEquals(1, storage.getNumberOfStoredPacks());
        
        Pack pack3 = storage.exportPack(pack1.getCode(), 10);        
        assertEquals((long)1, (long)pack3.getCode());
        assertEquals((long)10, (long)pack3.getQuantity());
        
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
