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
    private HashSet<Integer> productsToSell;

    /**
     * COnstrutor para objetos da classe SalesList
     */
    public SalesList(){
        productsToSell = new HashSet<Integer>();
    }

    private boolean codeIsValid(Integer code){
        if(code >= 0 && code != null)
            return true;
        else
            return false;
    }

    public void addProductToSell(Integer code){
        if(codeIsValid(code))
            if(productManagement.productIsRegisted(code))
                productsToSell.add(code);
            else
                System.out.println("Produto Não Registado!");
        else
            System.out.println("Código Inválido!");
    }

    public boolean productCanBeSold(Integer code){
        return productsToSell.contains(code);
    }

    public void removeProductToSell(Integer code){
        if(codeIsValid(code))
            if(productCanBeSold(code))
                productsToSell.remove(code);
            else
                System.out.println("Produto Não Registado!");
        else
            System.out.println("Código Inválido!");
    }
}
