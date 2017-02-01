import java.util.*; //ser mais preciso
/**
 * Implementa uma loja dando-lhe um nome, armazem, coordenadas e parque de estacionamento.
 * 
 * Esta classe utiliza um objecto da classe Storage. 
 * Esta classe utiliza um objecto da classe Parking.
 * Esta classe utiliza um objeto da classe Coordinates,
 * 
 * Dá-nos um texto com toda a informação da loja.
 * 
 * @author 160221052  Ricardo Carmo.
 * @author 160221072  Miguel Lobato.
 * @version 8/1/2017
 * 
 */public class Shop
{

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
     * Permite modificar o nome da loja
     * 
     * @param  name   O nome da loja.
     * 
     */public void setName(String name){    
        if(name != null)
            this.name = name;
    }

    /**
     * Permite saber a latitude da Loja e retorna um número real.
     * 
     * @return   A latitude da Loja.
     * 
     */
    public double getLatitude(){        
        return position.getLatitude();
    }

    /**
     * Permite saber a longitude da Loja e retorna um número real.
     * 
     * @return   A longitude da Loja.
     * 
     */
    public double getLongitude(){       
        return position.getLongitude();
    }

    /**
     * Permite descarregar o contentor de um camião para um lugar de estacionamento.
     * 
     * @param   O camião a descarregar na loja.
     * 
     */ public void unloadLorry(Lorry lorry){
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

    public void loadLorryWithContainer(Lorry lorry, int containerCode){
        if(lorry != null && containerCode >= 0){
            int index = parking.containerExists(containerCode);
            if(index != -1)
                lorry.loadContainer((Container) parking.getObjectFromSlot(index));        
        }
    }

    /**
     * Permite descarregar completamente o contentor no armazem da loja.
     * 
     * @param   containerCode    O codigo do contentor dado por um número inteiro.
     * 
     */public void unloadContainerFully(int containerCode){
        ArrayList<Pack> packsToStorage = new ArrayList<Pack>();        
        packsToStorage.addAll(parking.unloadContainerFully(containerCode));

        ArrayList<Pack> packsToRemove = new ArrayList<Pack>();

        if(packsToStorage != null && !packsToStorage.isEmpty())
            for(Pack pack: packsToStorage)
                if(salesList.productCanBeSold(pack.getCode())){
                    storage.importPack(pack);
                    packsToRemove.add(pack);                    
                }

        packsToStorage.removeAll(packsToRemove);

        if(!packsToStorage.isEmpty())
            for(Pack pack: packsToStorage)
                parking.loadContainer(containerCode, pack);
    }

    /**
     * Permite descarregar um pack do contentor para o armazem da loja.
     * 
     * @param containerCode    O codigo do contentor dado por um número inteiro.
     * @param packCode         O codigo do produto dado por um número inteiro.
     * @param quantity         A quantidade de um produto dado por um número inteiro.
     * 
     */public void unloadContainerSingle(int containerCode,int packCode,int quantity){ 
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
     * 
     */public Pack sell(int packCode,int quantity){
        return storage.exportPack(packCode, quantity);        
    }

    public void addProductToSell(Integer code){
        salesList.addProductToSell(code);
    }

    public void removeProductToSell(Integer code){
        salesList.removeProductToSell(code);
    }

    /**
     * Produz uma lista de informação sobre a loja
     * 
     * @return  O texto com a lista de informação sobre a loja.
     * 
     */public String toString(){ 
        return "****** Lista de Informação Sobre a Loja ******\n" + "Designação: " + name + "\n"
        + position.toString() + "\n\n" + parking.toString() + "\n\n" + storage.toString();
    }

    /**
     * Mostra no ecrã a lista de informação sobre a loja.
     * 
     */public void show(){
        System.out.println(toString());
    }
}
