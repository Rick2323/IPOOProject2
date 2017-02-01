/**
 * Implementa as garagens
 * 
 * Esta classe utiliza um objecto da classe Coordinates para dar uma posição às garagens.
 * 
 * Esta classe utiliza um objecto da classe Parking para criar e gerir o parqueamento.
 * 
 * @author 160221052  Ricardo Carmo.
 * @author 160221072  Miguel Lobato.
 * @version 31/1/2017
 * 
 */
public class Garage
{
    private String name;
    private Coordinates position;
    private ParkingLot parkingLot;

    public Garage(String name, double latitude, double longitude, int parkingSpace)
    {
        if(name != null)
            this.name = name;

        else
            this.name = "";
        position = new Coordinates (latitude, longitude);
        parkingLot = new ParkingLot(parkingSpace);
    }

    /**
     * Permite modificar o nome da garagem
     * 
     * @param  name   O nome da garagem
     * 
     */public void setName(String name){    
        if(name != null)
            this.name = name;
    }

    private boolean lorryHasContainer(Lorry lorry){//permite verificar se o camião tem contentor.
        return lorry.hasContainer();
    }

    private void setParked(Lorry lorry){//permite parquear um camião na garagem.
        lorry.setParked();
    }

    /**
     * Permite estacionar um camiao num determinado lugar.
     * 
     * 
     * @param lorry    O camião a estacionar na garagem.
     * @param parkingSlot    O lugar de estacionamento.
     * 
     */public void parkLorry(Lorry lorry, int parkingSlot){
        if(lorry != null)
            if(position.compareCoordinates(lorry)){
                parkingLot.park(parkingSlot, lorry);
                setParked(lorry);
            }
            else
                System.out.println("Coordenadas Inválidas");
        else
            System.out.println("Camião Inválido!"); 
    }

    /**
     * Permite retirar um camião de um determinado lugar de estacionamento.
     * 
     * @param  parkingSlot    O lugar de estacionamento.
     * @return    um objeto da classe camiao.
     * 
     */public Lorry unparkLorry(int parkingSlot){
        if(parkingLot.getObjectFromSlot(parkingSlot) != null){
            setParked((Lorry) parkingLot.getObjectFromSlot(parkingSlot));            
            return (Lorry) parkingLot.unpark(parkingSlot);
        }
        return null;
    }        

    /**
     * Permite fazer a inspecao de um camiao um dado lugar de estacionamento.
     * 
     * @param   parkingSlot   O lugar de estacionamento na garagem.
     * 
     */public void inspection(int parkingSlot){
        if(parkingLot.getObjectFromSlot(parkingSlot) != null)
            ((Lorry)parkingLot.getObjectFromSlot(parkingSlot)).inspection();
    }

    /**
     * Permite trocar e ou carregar um camião com um contentor.
     * 
     * @param   lorry    O camião na garagem.
     * @param   containerCode    O codigo do camião.
     * 
     */public void SwitchOrLoadContainerToLorry(Lorry lorry, int containerCode){
        int containerInParkIndex = parkingLot.containerExists(containerCode); //ve se existe o contentor do codigo que entra no parque e da a sua posisão
        if(lorry != null)
            if(containerCode >= 0)
                if(position.compareCoordinates(lorry)) //compara coordenadas
                    if(lorryHasContainer(lorry)){   //camiao tem contentor
                        if(containerInParkIndex != -1){  //existe contentor no parque, logo troca-os
                            Container containerInExchange = lorry.unloadContainer();  //descarrega e guarda o contentor do camiao
                            lorry.loadContainer((Container) parkingLot.unpark((Integer) containerInParkIndex));  //descarrega o contentor do parque para carregar o camiao       
                            parkingLot.park((Integer) containerInParkIndex, containerInExchange); //carrega o parque com o contentor guardado
                        }
                    }
                    else{ //camiao não tem contentor
                        if(containerInParkIndex != -1) //existe contentor no parque, logo carrega o camiao
                            lorry.loadContainer((Container) parkingLot.unpark((Integer) containerInParkIndex)); 
                        else //não há contentor no parque, como também nao ha no camiao não se faz nada
                            System.out.println("Não existe contentor no camião nem no parque");
                    }

                else
                    System.out.println("Coordenadas Inválidas!");
            else
                System.out.println("Código Inválido!"); 
        else
            System.out.println("Camião Inválido!"); 
    }

    /**
     * Permite descarregar o contentor de um camião para um lugar de estacionamento.
     * 
     * @param  lorry   O cammião a descarregar.
     * 
     */public void unloadLorry(Lorry lorry){ 
        if(lorry != null)
            if(position.compareCoordinates(lorry)) //compara coordenadas
                if(lorryHasContainer(lorry)){   //camiao tem contentor                                      
                    parkingLot.park(0, lorry.unloadContainer());  //não existe contentor no parque, logo descarrega o contentor do camiao para o parque  
                }
                else //não há contentor no camiao logo não se faz nada
                    System.out.println("Não é existe contentor no camião!");
            else
                System.out.println("Coordenadas Inválidas!");
        else
            System.out.println("Camião Inválido!"); 
    }

    /**
     * Produz um texto com a informação sobre da denominação da garagem, a sua posição e o que se passa no seu parque de estacionamento.
     * 
     * @return  Informação sobre a garagem.
     * 
     */public String toString(){  

        return "****** Lista de Informação Sobre a Garagem ******\n" + "Designação: " + name + "\n"
        + position.toString() + "\n\n" + parkingLot.toString() + "\n\n";
    }

    /**
     * Mostra no ecrã toda a informação disponivel sobre a garagem.
     * 
     */public void show(){
        System.out.println(toString());
    }
}
