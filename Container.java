import java.util.*;//ser mais preciso
/**
 * Container implementa um contentor a que será dado um número de codigo.
 * Cada contentor tem uma determinada capacidade em termos de peso (Kg) e volume (cm3).
 * 
 * Cada contentor estará vazio no acto de criação.
 * 
 * Esta classe utiliza um array de objectos da classe Packs, que representa os packs existemdes no contentor.
 * 
 * @author 160221052  Ricardo Carmo.
 * @author 160221072  Miguel Lobato.
 * @version 8/1/2017
 */

public class Container
{
    private static HashSet<Container> registedContainers = new HashSet<Container>();

    private Integer code;
    private ArrayList<Pack> listOfPacks;
    private double weightCapacity;
    private double volumeCapacity;

    public Container(Integer code)
    {        

        if(code != null && code >= 0 && !containerIsRegisted(code)){
            this.code = code;
            registerContainer(this);
        }
        else{
            this.code = null;
            System.out.println("O Código Introduzido É Inválido, Como Tal É Necessario Que Este Seja Corrigido!");
        }

        //listOfPacks = new Pack[10]; // Máximo 10 packs por contentor
        //numberOfPacks = 0;

        listOfPacks = new ArrayList<Pack>();
        weightCapacity = 22000; //Kg
        volumeCapacity = 33000000; //cm^3
    }

    private boolean containerHasValidCode(){
        if(code==null)
            System.out.println("Código Inválido!");
        return code!=null;
    }

    public void correctInvadidCode(Integer code){
        if(code != null || code >= 0)
            if(this.code == null)
                if(!containerIsRegisted(code))
                    this.code = code;
                else
                    System.out.println("Um COntentor Com Este Código Já Se Encontra Registado!");
            else
                System.out.println("O Código Atual Não É Inválido!");
        else
            System.out.println("Novo Código Inválido!");
    }

    /**
     * Permite obter o codigo de cada contentor que retorna como um número inteiro.
     * 
     * @return   O codigo do contentor.
     */
    public int getCode(){
        if(containerHasValidCode())
            return code;
        else
            System.out.println("Código Inválido!");
        return 0;
    }

    /**
     * Percorre o número de produtos que existe no contentor e devolve-nos um número real
     * que representa o peso  total em Kg dos produtos que estão no contentor.
     * 
     * @return    Peso total em quilogramas dos produtos que estão no contentor.
     */
    public double ocuppiedWeight(){
        if(containerHasValidCode()){
            int weight = 0;        
            for(Pack pack : listOfPacks)
                weight += pack.getTotalWeight();
            return weight;
        }
        return 0;
    }

    /**
     * Permite obter a capacidade restante de cada contentor em quilogramas (peso)e retorna-o como um número real.
     *
     *@return    A capacidade total restante do contentor em quilogramas (peso) . 
     *
     */
    public double availableWeight(){
        if(containerHasValidCode())
            return weightCapacity - ocuppiedWeight();
        return 0;
    }

    /**
     * Percorre o número de produtos existente no contentor e dá-nos o volume total ocupado do contentor em cm3. Devolve-nos um número real.
     * 
     * 
     * @return     O volume total ocupado do contentor em cm3.
     */
    public double ocuppiedVolume(){
        if(containerHasValidCode()){
            int volume = 0;
            for(Pack pack : listOfPacks)
                volume += pack.getTotalVolume();
            return volume;
        }
        return 0;
    }

    /**
     * Permite obter a capacidade restante do contentor em cm3 (volume)e devolve-nos um número real.
     * 
     * @return     A capacidade total restante do contentor em cm3 . 
     */
    public double availableVolume(){
        if(containerHasValidCode())
            return volumeCapacity - ocuppiedVolume();
        return 0;
    }

    /**  
     * Permite carregar o contentor. Faz a validação do produto e verifica se o mesmo cabe em peso e volume no contentor.
     * Devolve um texto no caso do produto não existir ou não caber no contentor.
     * 
     * @param   pack  Um objecto da classe Pack.
     */
    public void loadContainer(Pack pack){ 
        if(containerHasValidCode())
            if(listOfPacks.size() <  10){         
                if(pack != null){
                    if(ocuppiedWeight() + pack.getTotalWeight() <= weightCapacity){
                        if(ocuppiedVolume() + pack.getTotalVolume() <= volumeCapacity){
                            listOfPacks.add(pack);
                        }
                        else    System.out.println("Não existe volume disponível suficiente!");  
                    }
                    else    System.out.println("Não existe peso disponível suficiente!"); 
                }
                else    System.out.println("O pack é null!");
            }
            else  System.out.println("O contentor não tem capacidade para mais packs!");
    }

    /**
     * Permite descarregar o contentor, para tal é fornecido o código e a quantidade do pack. Só é retirado do contentor um pack que tenha exatamente o mesmo codigo e quantidade  
     * 
     * @param   code    O código do produto.
     * @param   quantity    A quantidade do produto.
     * @return  Um pack de código e quantidade igual ao que foi introduzido
     */
    public Pack unloadContainer(Integer code, int quantity){ 
        if(hasError(code, quantity))
            return null;
        Pack[] packsInContainer = new Pack[10]; 
        int numberOfElements = 0;
        boolean hasCode = false;
        if(containerHasValidCode()){
            for(Pack pack : listOfPacks)
                if(pack.getCode() == code){
                    hasCode = true;
                    if(pack.getQuantity() == quantity){                    
                        listOfPacks.remove(pack);
                        return copyPack(code, quantity);
                    }else{
                        packsInContainer[numberOfElements] = pack;
                        numberOfElements += 1;
                    }                        
                }
            if(!hasCode)
                System.out.print("O não existe pack de código " + code + " no contentor!");
        }
        if(numberOfElements > 0)
            System.out.print("Não existem packs de código: " + code + " com a quantidade: " + quantity + " ,apenas com as seguinte(s) quantidade(s): "
                + quantitysOfPacksInContainer(packsInContainer, numberOfElements));
        return null;
    }

    private boolean hasError(Integer code, int quantity){
        boolean error=false;
        if(code == null || code < 0){
            System.out.print("Código Inválido!");
            error= true;
        }
        if(quantity <= 0){
            System.out.print("Quantidade Inválida!");
            error= true;
        }

        return error;
    }

    private String quantitysOfPacksInContainer(Pack[] packsInContainer, int numberOfElements){
        if(containerHasValidCode()){
            String availableQuantity = "";
            if(packsInContainer != null && numberOfElements > 0){            
                for(int j=0; j < numberOfElements; j++)
                    if(j != numberOfElements - 1)
                        availableQuantity += packsInContainer[j].getQuantity() + ", ";                        
                    else
                        availableQuantity += "" + packsInContainer[j].getQuantity();
            }
            return availableQuantity;
        }
        return "";
    }

    private Pack copyPack(Integer code, int quantity){ 
        return new Pack(code, quantity);    
    }

    /**
     * Descarrega totalmente o contentor. 
     * 
     * @return devolve um array com os packs que estavam dentro do contentor
     */    
    public ArrayList<Pack> unloadContainerFully(){
        if(containerHasValidCode()){
            ArrayList<Pack> packsInContainer = new ArrayList<Pack>();

            if(!listOfPacks.isEmpty()){                
                packsInContainer.addAll(listOfPacks);
                listOfPacks.clear();
            }                
            else
                System.out.print("Contentor Vazio!");

            return packsInContainer; 
        }
        return null;
    }

    /**
     * Permite obter o número de produtos no contentor. Retorna-o como um número inteiro.
     * 
     * @return   O número de produtos.
     */public int getNumberOfPacks(){ //ver se faz alguma coisa
        return listOfPacks.size();
    }

    /**
     * Cria uma String com a lista de informação sobre o conteudo do contentor.
     * 
     */
    public String toString(){
        String exportString = null;
        if(containerHasValidCode()){
            exportString = "*** Lista de Informação dos Conteúdos do Contentor nº" + (int) code + " ***\n";

            if(!listOfPacks.isEmpty())           
                for(Pack pack : listOfPacks){
                    exportString += pack.toString() + "\n";

                    /*"Código: " + pack.getCode() + "\t\tNome: " + pack.getName() + "\t\tQuantidade: " + pack.getQuantity()
                    + "\t\tPeso: " + pack.getTotalWeight() + "Kg" + "\t\tVolume: " + pack.getTotalVolume() + "cm^3"; */
                }
            else
                exportString += "Contentor Vazio!";

            return exportString;
        }
        else
            exportString = "Contentor Com Código Inválido!";

        return exportString;
    }

    /**
     * Mostra no ecrã toda informação de determinado contentor.
     * 
     */public void show(){
        if(containerHasValidCode())
            System.out.println(toString());
    }

    //Código dos Contentores

    private boolean codeIsValid(Integer code){
        if(code >= 0 && code != null)
            return true;
        else
            return false;
    }

    public void registerContainer(Container container){
        if(container != null)
            if(registedContainers.isEmpty())//se o HashMap estiver vazio adiciona-o logo 
                registedContainers.add(container);

            else{ 
                if(!containerIsRegisted(container) && !containerIsRegisted(container.getCode()))//vai ver se não há contentores repetidos
                    registedContainers.add(container);
                else
                    System.out.println("O Contentor Já Se Encontra Registado!"); 
            }
        else
            System.out.println("Contentor Inválido");
    }

    private boolean containerIsRegisted(Integer code){
        if(codeIsValid(code))
            for(Container container: registedContainers)
                if(container.getCode() == code)
                    return true;
        return false;
    }

    private boolean containerIsRegisted(Container container){
        if(container != null && registedContainers.contains(container))
            return true;
        else
            return false;
    }

    public void unregisterContainer(Integer code){
        if(codeIsValid(code))
            if(containerIsRegisted(code))
                registedContainers.remove(code);
            else
                System.out.println("O Contentor Não Está Registado!");
        else
            System.out.println("Código Inválido!");        
    }

    public static int getFreeContainerCode(){
        ArrayList<Container> arrayCopy = new ArrayList<Container>(registedContainers);
        ArrayList<Integer> integersFromCopy = new ArrayList<Integer>();

        for(Container container: arrayCopy)
            integersFromCopy.add(container.getCode());

        Integer i = 1;

        while(integersFromCopy.contains(i))
            i++;

        return i;
    }

    private String containerString(){
        String exportString = "***** Lista de Contentores Registados *****\n";

        if(!registedContainers.isEmpty())        
            for(Container container : registedContainers)
                exportString += container.toString() + "\n";
        else
            exportString += "Nenhum Contentor Registado!";

        return exportString;
    }

    /**
     * Mostra no ecrã toda informação sobre os contentores registados.
     * 
     */public void showRegistedContainers(){
        System.out.println(containerString());
    }
}

	