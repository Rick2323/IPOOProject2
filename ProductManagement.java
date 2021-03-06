import java.util.*; //ser mais preciso
/**
 * ProductManagement representa uma lista de todos os produtos registados para circulação, 
 * sendo que a cada código (code) está associado um nome (String), peso unitário (double)em Kg e volume unitário (double)em Kg, ou seja (code - name/weight/volume).
 * 
 * @author 160221052  Ricardo Carmo.
 * @author 160221072  Miguel Lobato.
 * @version 31/1/2017
 */
public class ProductManagement{    
    private static HashMap<Integer, String> registedProducts = new HashMap<Integer, String>();

    /**
     * COnstrutor para objetos da classe ProductManagement
     */
    public ProductManagement(){}

    private static boolean codeIsValid(Integer code){
        if(code != null && code >= 0)
            return true;
        else
            return false;
    }    

    /**
     * Regista o código do produto e a este fica associado um nome, peso e volume.
     * 
     * @param   code    Código do produto.
     * @param   name    Nome do produto.
     * @param   weight  Peso Unitário.
     * @param   volume  Volume Unitário.
     */  
    public static void registerProduct(Integer code, String name, double weight, double volume){
        if(isValidProductInformation(code, name, weight, volume))
            registedProducts.put(code , name + "/" + weight + "/" + volume);
        else 
            System.out.println("Parâmetros Inválidos!");
    }

    private static boolean isValidProductInformation (Integer code, String name, double weight, double volume){
        if(codeIsValid(code) && name != null && weight > 0 && volume > 0)
            return true;
        else
            return false;
    }

    /**
     * Devolve uma String com a informação do produto no formato nome/peso/volume.    
     * 
     * @param code Código do produto.
     */
    public String getProductInformation(Integer code){
        if(productIsRegisted(code))
            return registedProducts.get(code);

        System.out.println("O Produto Não Está Registado!");
        return null;
    }

    /**
     * Devolve verdadeiro ou falso, repetivamente, caso o produto com o código introduzido esteja registado.
     * 
     * @param   code    Código do produto
     */
    public static boolean productIsRegisted(Integer code){
        if(codeIsValid(code) && registedProducts.containsKey(code))
            return true;
        else
            return false;
    }

    /**
     * Anula-se o registo de um produto se este estiver previamente registado.
     * 
     * @param   code    Código do produto
     */
    public void unregisterProduct(Integer code){
        if(codeIsValid(code))
            if(productIsRegisted(code))
                registedProducts.remove(code);
            else
                System.out.println("O Produto Com O Código " + code + " Não Está Registado!");
        else
            System.out.println("Código Inválido!");
    }
    
    /**
     * Limpa o HasMap dos produtos registados, é usado para testes.
     */
    public static void clearRegistedProducts(){
        registedProducts.clear();
    }

    /**
     * Devolve a informação dos produtos registados.
     * 
     * @return Devolve uma String com a informação.
     */
    public String registedProductString(){
        String exportString = "***** Lista de Produtos Registados *****\n";

        if(!registedProducts.isEmpty())
            for(Integer code : registedProducts.keySet())
                exportString += "Código: " + code + "  Nome: " + registedProducts.get(code).split("/")[0] + "  Peso Unitário: " + registedProducts.get(code).split("/")[1]
                + "  Volume Unitário: " + registedProducts.get(code).split("/")[2] + "\n";
        else
            exportString += "Nenhum Produto Registado!";

        return exportString;   
    }

    /**
     * Mostra no ecrã informação dos produtos registados. 
     */
    public void showRegistedProduct(){
        System.out.println(registedProductString());
    }
}
