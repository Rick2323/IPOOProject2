import java.util.*;//ser mais preciso
/**
 * Esta classe representa um estação de abastecimento
 * 
 * Esta classe utiliza um objecto da classe Coordinates para definir a sua posição.
 * 
 * Esta classe utiliza um objecto da classe Storage para armazenar os seus produtos.
 * 
 * Esta classe utiliza um objecto da classe Parking para criar e gerir o seu parque de estacionamento..
 * 
 * Produz um texto com a informação sobre o centro de abastecimento.
 * 
 * @author 160221052  Ricardo Carmo.
 * @author 160221072  Miguel Lobato.
 * @version 8/1/2017
 * 
 */public class SupplyStation
{
    private String name;
    private Coordinates position;
    private Storage storage;
    private ParkingLot parking;

    public SupplyStation(String name, double latitude, double longitude, int ammountOfEmptyContainers)
    {
        if(name != null)
            this.name = name;

        else
            this.name = "";

        position = new Coordinates (latitude, longitude);
        storage = new Storage(); 

        if(ammountOfEmptyContainers > 0){
            parking = new ParkingLot(ammountOfEmptyContainers);
            createEmptyContainers(ammountOfEmptyContainers);
        }
        else{
            parking = new ParkingLot(1);
            createEmptyContainers(1);
        }

    }

    private void createEmptyContainers(int ammountOfEmptyContainers){
        for(int i=0; i<ammountOfEmptyContainers;i++){
            Container importContainer = new Container(Container.getFreeContainerCode());
            parking.park(0, (Container) importContainer);                
        }
    }              

    /**
     * Permite modificar o nome da estação de abastecimento
     * 
     * @param  name   O nome da estação de abastecimento
     * 
     */public void setName(String name){    
        if(name != null)
            this.name = name;
    }

    /**
     * Permite descarregar um camião, e decidir se se manter o contentor no camiao ou no parque da loja e se esse contentor é para se descarregado na sua 
     * totalidade ou apenas um pack
     * 
     * @param  lorry    O camião a descarregar.
     * @param  code     O codigo do pack a descarregar dado por um numero inteiro.
     * @param  quantity  A quantidade do produto dado por um número inteiro.
     * @param  keepContainerInLorry  Se o contentor fica ou não no camião 
     * @param  full     Se o contentor é para ser esvaziado na totalidade.
     * 
     */public void unloadLorry(Lorry lorry, int code, int quantity, boolean keepContainerInLorry, boolean full){
        if(lorry != null)
            if(position.compareCoordinates(lorry)){ 
                if(lorry.hasContainer()){
                    Container containerFromLorry = lorry.unloadContainer();

                    if(!full)
                        storage.importPack(containerFromLorry.unloadContainer(code, quantity));
                    else{
                        ArrayList<Pack> packsInContainer = containerFromLorry.unloadContainerFully();

                        if(packsInContainer != null)
                            for(Pack pack: packsInContainer)
                                storage.importPack(pack); 
                    }                        

                    if(keepContainerInLorry){                    
                        lorry.loadContainer(containerFromLorry);
                    }
                    else{
                        if(containerFromLorry.getNumberOfPacks() == 0)
                            parking.park(0, containerFromLorry);
                        else{
                            System.out.println("O contentor tem de estar completamente vazio!");
                            lorry.loadContainer(containerFromLorry);
                        }
                    }
                }
                else
                    System.out.println("Não está atribuido contentor ao camião");
            }
            else
                System.out.println("Coordenadas Inválidas");
        else
            System.out.println("Camião Inválido!"); 
    }

    /**
     * Permite descarregar completamente o contentor no armazem da loja.
     * 
     * @param   containerCode    O codigo do contentor dado por um número inteiro.
     * 
     *//*public void unloadContainerFully(int containerCode){
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
    }*/

    /**
     * Permite carregar um camião com produtos da estaçao de abastecimento.
     * 
     * @param  lorry   Um camião a ser carregado.
     * @param  code    O codigo de um produto dado por um numero real.
     * @param  quantity  A quantidade de um produto dado por um número real.
     * 
     */public void loadLorry(Lorry lorry, int code, int quantity){
        if(lorry != null)
            if(position.compareCoordinates(lorry)){ 
                if(lorry.hasContainer()){
                    Container containerFromLorry = lorry.unloadContainer();

                    containerFromLorry.loadContainer(storage.exportPack(code, quantity));

                    lorry.loadContainer(containerFromLorry);
                }
                else{
                    Container containerForLorry = (Container) parking.getParkedObject();
                    if(containerForLorry != null){  //sao todos vazios 
                        containerForLorry.loadContainer(storage.exportPack(code, quantity)); //getParkedObject()

                        lorry.loadContainer(containerForLorry);
                    }
                    else
                        System.out.println("Não há contentores vazios no parque!");
                }
            }
            else
                System.out.println("Coordenadas Inválidas");
        else
            System.out.println("Camião Inválido!"); 
    }

    /**
     * Permite adicionar produtos ao centro de distribuição.
     * 
     * @param  code   O codigo do produto dado por um numero inteiro.
     * @param  quantity  A quantidade de um produto dado por um número inteiro.
     */public void addPackToStorage(int code, int quantity){
        if(ProductManagement.productIsRegisted((Integer) code)){
            Pack importPack = new Pack(code, quantity);
            storage.importPack(importPack);
        }
        else
            System.out.println("Produto Não Registado!");
    }

    /**
     * Produz um texto da informação sobre a estação de abastecimento.
     * 
     * @return  Um texto com a informação sobre a estação de abastecimento.
     * 
     */public String toString(){         
        return "****** Lista de Informação Sobre a Estação de Abastecimento ******\n" + "Designação: " + name + "\n"
        + position.toString() + "\n\n" + parking.toString() + "\n\n" + storage.toString();
    }

    /**
     * Mostra no ecrã a informação sobre a estação de abastecimento.
     * 
     */public void show(){
        System.out.println(toString());
    }
}
