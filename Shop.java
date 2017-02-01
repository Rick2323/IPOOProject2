import java.util.*; //ser mais preciso
/**
 * Representa uma loja, sendo que esta pode receber packs, que venham de contentores, guardar e vende-los posteriormente,
 * se estes estiverem registados na lista de vendas.
 * 
 * @author 160221052  Ricardo Carmo.
 * @author 160221072  Miguel Lobato.
 * @version 1/2/2017
 */
public class Shop{

    private String name;
    private Storage storage;
    private ParkingLot parking;
    private Coordinates position;
    private SalesList salesList;
    
    public Shop(String name, double latitude, double longitude)
    {
        if(name != null)
            this.name = name;

        else
            this.name = "";

        position = new Coordinates (latitude, longitude);
        storage = new Storage(); 
        parking = new ParkingLot(2); 
        salesList = new SalesList();
    }   

    /**
     * Modificar o nome da loja
     * 
     * @param  name   O nome da loja.
     */
    public void setName(String name){    
        if(name != null)
            this.name = name;
    }
    
    /**
     * Devolve o nome da loja.
     * 
     * @return nome da loja.
     */
    public String getName(){
        return name;
    }

    /**
     * Devolve a latitude da Loja.
     * 
     * @return   A latitude da Loja.
     */
    public double getLatitude(){        
        return position.getLatitude();
    }

    /**
     * Dovolve a longitude da Loja e retorna um número real.
     * 
     * @return   A longitude da Loja.
     */
    public double getLongitude(){       
        return position.getLongitude();
    }

    /**
     * Permite descarregar o contentor de um camião para um lugar de estacionamento.
     * 
     * @param   O camião a descarregar.
     */
    public void unloadLorry(Lorry lorry){
        if(lorry != null){
            if(parking.hasSpace()){
                if(position.compareCoordinates(lorry))

                    parking.park(0, lorry.unloadContainer()); 
                else
                    System.out.println("Coordenadas Inválidas");
            }
            else
                System.out.println("Não há espaço disponível no parque!");
        }
        else
            System.out.println("Camião Inválido!");      
    }

    /**
     * Permite carregar um dado camião con um contentor que esteja no parque.
     * 
     * @param lorry Camião a carregar.
     * @param containerCode código do contentor que se que carregar.
     */
    public void loadLorryWithContainer(Lorry lorry, int containerCode){
        if(lorry != null && containerCode >= 0){
            int index = parking.containerExists(containerCode);
            if(index != -1)
                lorry.loadContainer((Container) parking.getObjectFromSlot(index));        
        }
    }

    /**
     * Permite descarregar completamente um contentor que esteja no parque da loja.
     * 
     * @param   containerCode    O codigo do contentor a descarregar.
     */
    public void unloadContainerFully(int containerCode){
        ArrayList<Pack> packsToStorage = new ArrayList<>();        
        packsToStorage.addAll(parking.unloadContainerFully(containerCode));

        ArrayList<Pack> packsToRemove = new ArrayList<>();

        if(packsToStorage != null && !packsToStorage.isEmpty())
            for(Pack pack: packsToStorage)
                if(salesList.productCanBeSold(pack.getCode())){
                    storage.importPack(pack);
                    packsToRemove.add(pack);                    
                }

        packsToStorage.removeAll(packsToRemove);

        if(!packsToStorage.isEmpty())
            for(Pack pack: packsToStorage)
                storage.importPack(pack);
    }

    /**
     * Permite descarregar um pack do contentor para o armazém da loja.
     * 
     * @param containerCode    O codigo do contentor dado por um número inteiro.
     * @param packCode         O codigo do produto dado por um número inteiro.
     * @param quantity         A quantidade de um produto dado por um número inteiro.
     */
    public void unloadContainerSingle(int containerCode,int packCode,int quantity){ 
        Pack packToStorage = parking.unloadContainerSingle(containerCode, packCode, quantity);

        if(packToStorage != null)
            if(salesList.productCanBeSold(packToStorage.getCode()))
                storage.importPack(packToStorage);
    }

    /**
     * Permite vender determinado produto retiramdo-o do armazem
     * 
     * @param  packCode    O codigo do produto dado por um número inteiro.
     * @param  quantity    A quantidade do produto por um número inteiro.
     * @return     O produto para vender.
     */
    public Pack sell(int packCode,int quantity){
        return storage.exportPack(packCode, quantity);        
    }

    /**
     * Adiciona um produto à lista de vendas, utilizando o seu código.
     * 
     * @param O código do produto a adicionar.
     */
    public void addProductToSell(Integer code){
        salesList.addProductToSell(code);
    }
    
    /**
     * Verifica se o código so produto existe na lista de vendas, e consequentemente
     * se este pode ser vendido. (está nesta classe para ser usado em testes)
     * 
     * @param O código do produto que se quer verificar.
     */
    public boolean productCanBeSold(Integer code){
        return salesList.productCanBeSold(code);
    }
    
    /**
     * Remove um produto da lista de vendas, utilizando o seu código.
     * 
     * @param O código do produto a remover.
     */
    public void removeProductToSell(Integer code){
        salesList.removeProductToSell(code);
    }

    /**
     * Produz uma String com uma lista de informação sobre a loja
     * 
     * @return  O texto com a lista de informação sobre a loja.
     */
    public String toString(){ 
        return "****** Lista de Informação Sobre a Loja ******\n" + "Designação: " + name + "\n"
        + position.toString() + "\n\n" + parking.toString() + "\n\n" + storage.toString();
    }

    /**
     * Mostra no ecrã a lista de informação sobre a loja.
     */
    public void show(){
        System.out.println(toString());
    }
}
