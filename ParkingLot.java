import java.util.*; //ser mais preciso
/**
 * Escreva a descrição da classe Parking aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class ParkingLot
{
    private static HashMap<Integer, ParkingSpot> parkingLot;
    private final int PARKING_SPACE;
    /**
     * COnstrutor para objetos da classe Parking
     */
    public ParkingLot(int parkingSpace){
        if(parkingSpace > 0)
            this.PARKING_SPACE = parkingSpace;

        else
            this.PARKING_SPACE = 1;

        parkingLot = new HashMap<Integer, ParkingSpot>();
    }

    private boolean isValidParkingSlot(Integer parkingSlot){
        return (parkingSlot != null && parkingSlot > 0 && parkingSlot <= PARKING_SPACE);
    }

    public void park(Integer parkingSlot, Object object){
        if(!hasError(parkingSlot, object))
            if(parkingSlot > 0 ){
                ParkingSpot spot = new ParkingSpot(object);
                parkingLot.put(parkingSlot,spot);
            }

            else if (parkingSlot == 0){
                ParkingSpot spot = new ParkingSpot(object);
                parkingLot.put(getFreeSpotNumber(),spot);
            }       
    }

    private boolean hasError(Integer parkingSlot, Object object){
        if(hasSpace())
            if(object != null)
                if((object.getClass().getName().equals("Container")) || (object.getClass().getName().equals("Lorry")))
                    if(parkingSlot != null && parkingSlot >= 0 && parkingSlot <= PARKING_SPACE) //nao se pode usar o metodo isValidParkingSlot pq pode ser 0
                        if(!isRepeated(parkingSlot, object))
                            return false;
                        else
                            System.out.println("Lugar Ocupado e/ou Camião/Contentor Já Estacionado!");
                    else
                        System.out.println("Lugar Inválido");
                else
                    System.out.println("Camião/Contentor Inválido!");
            else
                System.out.println("Camião/Contentor Inválido!");
        else
            System.out.println("Não Há Espaço Disponível No Parque!");
        return true;
    }

    private boolean hasSpace(){
        return parkingLot.size() < PARKING_SPACE;
    }

    private boolean isRepeated(Integer parkingSlot, Object object){
        if(parkingLot.containsKey(parkingSlot))
            return true;

        if(!object.getClass().getName().equals("Container") && !object.getClass().getName().equals("Lorry"))
            return true; //diz que o objeto ja existe, como este metodo é usado para verificar se um objeto existe antes de o criar, ao dizer que já existe não o cria    

        for(ParkingSpot slot: parkingLot.values())
            if(slot.getParked().equals(object)) 
                return true;            

        return false;
    }

    private Integer getFreeSpotNumber(){
        for(int i=1; i <= PARKING_SPACE; i++)
            if(!parkingLot.containsKey(i))
                return i;
        return -1;
    }

    private Integer getOccupiedSpotNumber(){
        for(int i=1; i <= PARKING_SPACE; i++)
            if(parkingLot.containsKey(i))
                return i;
        return -1;
    }

    public Object getParkedObject(){
        if(getOccupiedSpotNumber() != -1)
            return unpark(getOccupiedSpotNumber());
        return null;
    }

    public Object unpark(Integer parkingSlot){
        if(!parkingLot.isEmpty())
            if(parkingSlot != null && parkingSlot >= 0 && parkingSlot <= PARKING_SPACE)
                if(parkingSlot > 0)
                    if(parkingLot.containsKey(parkingSlot))
                        return parkingLot.remove(parkingSlot).getParked();       
                    else
                        System.out.println("Lugar Vazio!");

                else
                    return parkingLot.remove(getOccupiedSpotNumber()).getParked();

            else
                System.out.println("Lugar Inválido!");
        else
            System.out.println("Parque Vazio!");

        return null;
    }

    public Lorry getLorryFromSlot(Integer parkingSlot){
        if(parkingLot.containsKey(parkingSlot) && isValidParkingSlot(parkingSlot))
            if(parkingLot.get(parkingSlot).getParked().getClass().getName().equals("Container"))
                return (Lorry) parkingLot.get(parkingSlot).getParked();
        return null;
    }

    /**
     * permite descarregar isoladamente cada pack de produto.
     * 
     * @param  containerCode   O codigo do contentor.
     * @param  packCode        O codigo do produto.
     * @param  quantity        A quantidade do produto.
     * @return     O produto escolhido para descarregar.
     * 
     */
    public Pack unloadContainerSingle(int containerCode,int packCode,int quantity){ ////descarrega um pack, que tenha o codigo e quantidade introduzida, do contentor   
        Pack pack = null;
        boolean found = false;
        if(containerCode >= 0){
            for(ParkingSpot slot: parkingLot.values())
            //for(int i=0; i<numberOfContainers; i++){
                if(slot.getParked().getClass().getName().equals("Container"))
                    if(((Container) slot.getParked()).getCode() == containerCode){
                        pack = ((Container) slot.getParked()).unloadContainer((Integer) packCode, quantity);
                        found = true;
                        break;
                    }

            if(!found)
                System.out.print("Não existe o contentor com código " + containerCode + " no parque!");
        }
        else
            System.out.print("Código Inválido!"); 

        return pack;  
    }

    /**
     * Descarrega completamente os produtos do contentor e devolve um array de produtos.
     * 
     * @param  containerCode    O codigo do contentor.
     * @return     Um array de ojbetos da classe pack. 
     *
     * 
     */
    public ArrayList<Pack> unloadContainerFully(int containerCode){ //descarrega todos os packs de um contentor, devolve um array
        ArrayList<Pack> packsInContainer = null;
        boolean found = false;
        if(containerCode >= 0){
            //for(int i=0; i<numberOfContainers; i++){ //percorre o parque
            for(ParkingSpot slot: parkingLot.values())
                if(slot.getParked().getClass().getName().equals("Container"))
                    if(((Container) slot.getParked()).getCode() == containerCode){ //à procura de um com o mesmo codigo
                        packsInContainer = ((Container) slot.getParked()).unloadContainerFully();  //este metodo vem da classe dos contentores e descarrega todos os packs de um contentor, devolve um array        
                        found = true;
                        break;
                    }

            if(!found) //se não encontrou faz print da mensagem,
                System.out.print("Não existe o contentor com código " + containerCode + " no parque!");
        }
        else
            System.out.print("Código Inválido!"); 

        return packsInContainer;
    }

    /**
     * Permite saber se determinado contentor existe no parque e devolve um numero inteiro.
     * 
     * @param  containerCode   O codigo do contentor.
     * 
     */
    public int containerExists(int containerCode){  //se ve um contentor(codigo) ja existe no parque, se sim dá a sua posisão, usado na garagem
        for(Map.Entry<Integer, ParkingSpot> entry:parkingLot.entrySet())
            if(((Container)entry.getValue().getParked()).getCode()==containerCode)
                return(int)entry.getKey();        

        return -1;
    }

    /**
     * Produz uma String com informação sobre os parque.
     *    
     * 
     */
    public String toString(){
        String exportString = "";

        if(!parkingLot.isEmpty()){
            exportString = "****** Informação Sobre Camiões e/ou Contentores ******\n\n"; 
            ParkingSpot spotTemp=null;
            for(int i=1 ; i<=PARKING_SPACE ; i++){
                spotTemp=parkingLot.get((Integer)i);
                exportString += "Lugar " + i + " => " + (spotTemp!=null ? spotTemp.getParked().toString() : "Vazio") + "\n\n";  
            }    
        }
        else
            exportString = "Parque Vazio!";

        return  exportString;
    }

    /**
     * Mostra no ecrã a informação sobre os camiões e contentores estacionados no parque.
     * 
     */public void show(){
        System.out.print(toString());
    }

}
