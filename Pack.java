/**
 * Pack representa um conjunto de unidades de um produto;
 * Este produto é designado por código, peso (por unidade) e volume (por unidade) fixo;
 * E por um nome e quantidade (de unidades) variavél. 
 *
 * @author 160221052  Ricardo Carmo.
 * @author 160221072  Miguel Lobato.
 * @version 8/1/2017
 */
public class Pack
{    
    private Integer code;
    private int quantity;
    private static ProductManagement productManagement = new ProductManagement();

    public Pack(Integer code, int quantity){
        if(codeIsValid(code) && productManagement.productIsRegisted(code))
            this.code = code;
        else{
            this.code = null;
            System.out.println("O Código Introduzido É Inválido, Como Tal É Necessario Que Este Seja Corrigido!");
        }

        if(quantityIsValid(quantity))
            this.quantity = quantity;
        else
            this.quantity = 1;
    }

    public Pack(Integer code, String name, double weight, double volume, int quantity){
        registerProduct(code, name, weight, volume);
        if(code != null && code >= 0 && productManagement.productIsRegisted(code))
            this.code = code;
        else{
            this.code = null;
            System.out.println("O Código Introduzido É Inválido, Como Tal É Necessario Que Este Seja Corrigido!");
        }

        if(quantity > 0)
            this.quantity = quantity;
        else
            this.quantity = 1;
    }
    
    private boolean codeIsValid(Integer code){
        return (code != null && code >= 0);
    }
    
    private boolean quantityIsValid(int quantity){
        return quantity >= 0;
    }
    
    /**
     * Permite obter o código do produto;
     * 
     * @return    O código do produto.
     */
    public int getCode(){
        return code;
    }

    public static void registerProduct(Integer code, String name, double weight, double volume){
        productManagement.registerProduct(code, name, weight, volume);
    }

    private boolean packHasValidCode(){
        return code != null;
    }

    public void correctInvadidCode(Integer code){
        if(code != null && code >= 0)
            if(this.code == null)
                this.code = code;
            else
                System.out.println("O Código Atual Não É Inválido!");
        else
            System.out.println("Novo Código Inválido!");
    }

    /**
     * Permite obter a quantidade em unidades do produto.
     * 
     * @return  A quantidade do produto.
     */
    public int getQuantity(){
        if(packHasValidCode())
            return quantity;
        else
            System.out.println("Código Inválido!");
        return 0;
    }

    public String getName(){
        if(packHasValidCode())
            return productManagement.getProductInformation(code).split("/")[0]; 
        else 
            System.out.println("Código Inválido!");
        return null;
    }

    public double getWeight(){
        if(packHasValidCode())
            return Double.parseDouble(productManagement.getProductInformation(code).split("/")[1]);
        else 
            System.out.println("Código Inválido!");
        return 0.0;
    }

    public double getVolume(){
        if(packHasValidCode())
            return Double.parseDouble(productManagement.getProductInformation(code).split("/")[2]); 
        else 
            System.out.println("Código Inválido!");
        return 0.0;
    }

    /**
     * Permite aumentar a quantidade do produto.
     * É feito através de um número inteiro maior que zero.
     * 
     * @param  numberToAdd   Um inteiro que designa a quantidade a adicionar.
     */
    public void addQuantity(int numberToAdd){
        if(packHasValidCode()){
            if(numberToAdd > 0)
                quantity += numberToAdd;
        }else 
            System.out.println("Código Inválido!");
    }

    /**
     * Permite reduzir a quantidade do produto.
     * É feito através de um número inteiro maior que zero.
     * 
     * @param  numberToRemove  Um inteiro que designa a quantidade a remover de um produto.
     */
    public void removeQuantity(int numberToRemove){
        if(packHasValidCode()){
            if(numberToRemove > 0)
                quantity -= numberToRemove;
        }else 
            System.out.println("Código Inválido!");
    }

    /**
     * Permite obter o peso total do produto em quilogramas que existe no pack e retorna-o como um número real.
     * 
     * 
     * @return   O peso unitário em quilogramas do produto vezes a quantidade de unidades do produto.
     * 
     */
    public double getTotalWeight(){
        return getWeight() * quantity;
    }

    /**
     * Permite obter o volume total do produto em cm3 que existe no pack e retorna-o como um número real.
     * 
     * @return  O volume em cm3 de cada unidade vezes a sua quantidade.
     */
    public double getTotalVolume(){
        return getVolume() * quantity;
    }

    /**
     * Cria uma String com a lista de informação sobre o pack.
     * 
     */
    public String toString(){
        if(packHasValidCode())
            return "Código: " + code + "  Nome: " + getName() + "  Quantidade: " + getQuantity() + "  Peso: " + getTotalWeight() + "Kg" + "  Volume: " + getTotalVolume() + "cm^3"; 
        else 
            System.out.println("Código Inválido!");
        return null;
    }

    public void show(){
        if(packHasValidCode())
            System.out.println(toString());
        else 
            System.out.println("Código Inválido!");
    }
}