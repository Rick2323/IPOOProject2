import java.util.*; //ser mais preciso
/**
 * Escreva a descrição da classe ProductManagement aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class ProductManagement
{    
    private static HashMap<Integer, String> registedProducts = new HashMap<Integer, String>();

    /**
     * COnstrutor para objetos da classe ProductManagement
     */
    public ProductManagement(){}

    private boolean codeIsValid(Integer code){
        if(code >= 0 && code != null)
            return true;
        else
            return false;
    }

    public void registerProduct(Integer code, String name, double weight, double volume){
        if(isValidProductInformation(code, name, weight, volume))
            registedProducts.put(code , name + "/" + weight + "/" + volume);
        else 
            System.out.println("Parâmetros Inválidos!");
    }

    private boolean isValidProductInformation (Integer code, String name, double weight, double volume){
        if(codeIsValid(code) && name != null && weight > 0 && volume > 0)
            return true;
        else
            return false;
    }

    public String getProductInformation(Integer code){ //PRIVATE??
        if(productIsRegisted(code))
            return registedProducts.get(code);
        else
            System.out.println("O Produto Não Está Registado!");
        return null;
    }

    public boolean productIsRegisted(Integer code){
        if(codeIsValid(code) && registedProducts.containsKey(code))
            return true;
        else
            return false;
    }

    public void unregisterProduct(Integer code){
        if(codeIsValid(code))
            if(productIsRegisted(code))
                registedProducts.remove(code);
            else
                System.out.println("O Produto Não Está Registado!");
        else
            System.out.println("Código Inválido!");
    }

    private String registedProductString(){
        String exportString = "***** Lista de Produtos Registados *****\n";

        if(!registedProducts.isEmpty())
            for(Integer code : registedProducts.keySet())
                exportString += "Código: " + code + "  Nome: " + registedProducts.get(code).split("/")[0] + "  Peso Unitário: " + registedProducts.get(code).split("/")[1]
                + "  Volume Unitário: " + registedProducts.get(code).split("/")[2] + "\n";
        else
            exportString += "Nenhum Produto Registado!";

        return exportString;   
    }
    
    public void showRegistedProduct(){
        System.out.println(registedProductString());
    }
}
