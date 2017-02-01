import java.util.*;//ser mais preciso
/**
 * Representa um estação de abastecimento, esta cria packs de produtos que estejam registados para serem colocados em stock. 
 * Estes packs podem posteriormente ser colocados em contentores para serem movimentados.
 * 
 * @author 160221052  Ricardo Carmo.
 * @author 160221072  Miguel Lobato.
 * @version 1/2/2017
 */
public class SupplyStation
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
    
    /*public ParkingLot getParking(){
        return  parking;
    }*/

    private void createEmptyContainers(int ammountOfEmptyContainers){
        Container importContainer = null;
        for(int i=0; i<ammountOfEmptyContainers;i++){
            importContainer = new Container(Container.getFreeContainerCode());
            parking.park(0, (Container) importContainer);                
        }
    }              

    /**
     * Permite modificar o nome da estação de abastecimento.
     * 
     * @param  name   O nome da estação de abastecimento.
     * 
     */public void setName(String name){    
        if(name != null)
            this.name = name;
    }
    
    /**
     * Devolve o nome da estação de abastecimento.
     * 
     * @return  nome da estação de abastecimento.
     */
    public String getName(){
        return name;
    }
    
    /**
     * Permite saber a latitude da estação de abastecimento e retorna um número real.
     * 
     * @return   A latitude da estação de abastecimento.
     * 
     */
    public double getLatitude(){          
        return position.getLatitude();
    }

    /**
     * Permite saber a longitude da estação de abastecimento e retorna um número real.
     * 
     * @return   A longitude da estação de abastecimento.
     * 
     */
    public double getLongitude(){        
        return position.getLongitude();
    }

    /**
     * Permite descarregar o contentor de um camião, e decidir se se quer manter o contentor no camiao ou no parque da loja e se esse contentor é para ser 
     * descarregado na sua totalidade ou apenas um pack
     * 
     * @param  lorry    O camião a descarregar.
     * @param  code     O codigo do pack a descarregar dado por um numero inteiro.
     * @param  quantity  A quantidade do produto dado por um número inteiro.
     * @param  keepContainerInLorry  Se o contentor fica ou não no camião 
     * @param  full     Se o contentor é para ser esvaziado na totalidade.
     * 
     */
    public void unloadLorry(Lorry lorry, int code, int quantity, boolean keepContainerInLorry, boolean full){
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
     * Permite carregar o camião com produtos da estação de abastecimento.
     * 
     * @param  lorry   O camião a ser carregado.
     * @param  code    O código do produto a carregar.
     * @param  quantity  A quantidade de produto que se quer carregar.
     */
    public void loadLorry(Lorry lorry, int code, int quantity){
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
     * Permite adicionar pack à estação de abastecimento.
     * 
     * @param  code   O código do produto que se quer adicionar.
     * @param  quantity  A quantidade de produto que se quer adicionar.
     */
    public void addPackToStorage(int code, int quantity){
        if(ProductManagement.productIsRegisted((Integer) code)){
            Pack importPack = new Pack(code, quantity);
            storage.importPack(importPack);
        }
        else
            System.out.println("Produto Não Registado!");
    }

    /**
     * Produz um texto com a informação da estação de abastecimento.
     * 
     * @return  Um texto com a informação sobre a estação de abastecimento.
     */
    public String toString(){         
        return "****** Lista de Informação Sobre a Estação de Abastecimento ******\n" + "Designação: " + name + "\n"
        + position.toString() + "\n\n" + parking.toString() + "\n\n" + storage.toString();
    }

    /**
     * Mostra no ecrã a informação sobre a estação de abastecimento.
     */
    public void show(){
        System.out.println(toString());
    }
}
