import java.util.*;//ser mais preciso
/**
 * Escreva a descrição da classe SalesList aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class SalesList
{
    private static ProductManagement productManagement = new ProductManagement();
    private HashSet<Lorry> ProductsToSell;

    /**
     * COnstrutor para objetos da classe SalesList
     */
    public SalesList()
    {
        ProductsToSell = new HashSet<Lorry>();
    }

    
}
