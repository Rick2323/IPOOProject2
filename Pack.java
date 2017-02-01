/**
 * Pack representa um conjunto de unidades de um produto e o código deste;
 *
 * @author 160221052  Ricardo Carmo.
 * @author 160221072  Miguel Lobato.
 * @version 31/1/2017
 */
public class Pack
{    
    private Integer code;
    private int quantity;
    private static ProductManagement productManagement = new ProductManagement();

    public Pack(Integer code, int quantity){ //cria um pack de um produto já registado
        if(codeIsValid(code) && productManagement.productIsRegisted(code))
            this.code = code;
        else{
            this.code = null;
            System.out.println("O Código Introduzido É Inválido, Como Tal É Necessário Que Este Seja Corrigido!");
        }

        if(quantityIsValid(quantity))
            this.quantity = quantity;
        else
            this.quantity = 1;
    }

    public Pack(Integer code, String name, double weight, double volume, int quantity){//cria um pack e regista o produto no ato da criação
        if(codeIsValid(code)){            
            registerProduct(code, name, weight, volume);
            this.code = code;            
        }
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
        return quantity > 0;
    }

    /**
     * Permite obter o código do produto;
     * 
     * @return    O código do produto.
     */
    public Integer getCode(){
        return code;
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
        productManagement.registerProduct(code, name, weight, volume);
    }

    private boolean packHasValidCode(){
        return code != null;
    }

    /**
     * Permite corrigir o código do pack, caso o atual seja inválido e o novo válido.
     * 
     * @param   code    Código do produto.
     */
    public void correctInvadidCode(Integer code){
        if(code != null && code >= 0)
            if(this.code == null){
                if(productManagement.productIsRegisted(code))
                    this.code = code;
                else
                    System.out.println("Código Introduzido Inválido!");
            }
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

    /**
     * Permite obter o nome do produto.
     * 
     * @return  O nome do produto.
     */
    public String getName(){
        if(packHasValidCode()){
            if(productManagement.getProductInformation(code) != null)
                return productManagement.getProductInformation(code).split("/")[0]; 
        }
        else 
            System.out.println("Código Inválido!");
        return null;
    }

    /**
     * Permite obter o peso unitário em Kg do produto.
     * 
     * @return  O peso unitário do produto.
     */
    public double getWeight(){
        if(packHasValidCode()){
            if(productManagement.getProductInformation(code) != null)
                return Double.parseDouble(productManagement.getProductInformation(code).split("/")[1]);
        }
        else 
            System.out.println("Código Inválido!");
        return 0.0;
    }

    /**
     * Permite obter o volume unitário em cm^3 do produto.
     * 
     * @return  O volume unitário do produto.
     */
    public double getVolume(){
        if(packHasValidCode()){
            if(productManagement.getProductInformation(code) != null)
                return Double.parseDouble(productManagement.getProductInformation(code).split("/")[2]);
        }
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