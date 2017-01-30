/**
 * Classe utilizada para a gestão dos camiões.
 * Permite carregar e descarregar os camiões com contentores. Leva-los à inspecção
 * e saber quantos quilometros dista a ultima.
 * 
 * @author 160221052  Ricardo Carmo.
 * @author 160221072  Miguel Lobato.
 * @version 8/1/2017
 * 
 */public class Lorry
{
    private String designation;
    private int code;
    private Container container;
    private Coordinates position;
    private double totalKilometers;
    private double kilometersSinceLastInspection;
    private int numberOfInspections;
    private boolean parked;
    private static LorryManagement lorryManagement = new LorryManagement();

    public Lorry(String designation, int code, double latitude, double longitude)
    {
        if(designation != null)
            this.designation = designation;

        else
            this.designation = "";

        if(code>=0){
            this.code = code;
            registerLorry(this);
        }

        else
            this.code = 0;

        container = null;
        position = new Coordinates (latitude, longitude);
        totalKilometers = 0.0;
        kilometersSinceLastInspection = 0.0;
        numberOfInspections = 0;
        parked = false;
    }

    public static void registerLorry(Lorry lorry){
        lorryManagement.registerLorry(lorry);
    }

    /**
     * Permite obter o codigo do camião e retorna-o como um número inteiro.
     * 
     * @return   O codigo do camião.
     * 
     */
    public int getCode(){
        return code;
    }

    public void setDesignation(String name){
        if(name != null)
            designation = name;
    }

    /**
     * Permite modificar se um camiao está estacionado.
     */
    public void setParked(){
        if(parked)
            parked = false;
        else
            parked = true;
    }

    /**
     * Permite carregar o camião com um contentor.
     * 
     * @param importContainer    O contentor que se quer por no camião.
     * 
     */
    public void loadContainer(Container importContainer){        
        if(container == null){
            if(importContainer != null){
                container = importContainer;
            }
            else
                System.out.println("O Contentor Não Existe!");
        }
        else
            System.out.println("Já Está Atribuído Um Contentor ao Camião!");
    }

    /**
     * Permite descarregar o contentor de um camião.
     * 
     * @return  O contentor descarregado se ele existir.
     * 
     */
    public Container unloadContainer(){        
        if(container != null){
            Container exportContainer = container;
            container = null;
            return exportContainer;         
        }

        else
            System.out.println("Não está atribuido contentor ao camião");
        return null;            
    }

    /**
     * Permite levar um camião à inspecção.
     * 
     */public void inspection(){        
        kilometersSinceLastInspection = 0;
        numberOfInspections++;
    }

    /**
     * Permite deslocar o camião se nao estiver estacionado para determinado sitio dando-lhe as coordenadas.
     * 
     * @param  latitude   A latitude que se deseja para o camião.
     * @param  longitude  A longitude que se deseja para o camião.
     * 
     */
    public void moveLorry(double latitude, double longitude){
        if(!parked){
            totalKilometers += position.distanceTravelled(latitude,longitude);
            kilometersSinceLastInspection += position.distanceTravelled(latitude,longitude);
            position.moveTo(latitude,longitude);
        }
        else
            System.out.println("O camião está estacionado!");
    } 

    /**
     * Permite saber a latitude de um camião e retorna um número real.
     * 
     * @return   A latitude de um camião.
     * 
     */
    public double getLatitude(){          //usado na classe shop
        return position.getLatitude();
    }

    /**
     * Permite saber a longitude de um camião e retorna um número real.
     * 
     * @return   A longitude de um camião.
     * 
     */
    public double getLongitude(){        //usado na classe shop
        return position.getLongitude();
    }

    /**
     * Permite saber se um camião tem um contentor e retorna um valor boleano.
     * 
     * @return   Se tem ou não tem contentor.
     * 
     */
    public boolean hasContainer(){
        if(container != null)
            return true;
        else
            return false;
    }

    public double getTotalKilometers(){
        return totalKilometers;
    }
    
    public boolean isNew(){
        return (totalKilometers < 50000);
    }
    
    public boolean isOld(){
        return (totalKilometers > 1000000);
    }
    
    public boolean needsInspection(){
        return (kilometersSinceLastInspection > 10000);
    }

    /**
     * Produz um texto informativo sobre um camião.
     * 
     */
    public String toString(){
        String containerInformation = "";

        if(container != null)
            containerInformation = container.toString();
        else
            containerInformation = "Nenhum Contentor Disponível";
        return  "*** Informação Sobre o Camião ***\n" + "Designação: " + designation + "\nNúmero: " + code + "\n" + containerInformation + "\n" + position.toString() 
        + "\n" + "Total Quilómetros Percorridos: " + totalKilometers +"Km"+ "\nQuilómetros Percorridos Desde a Última Inspecção: " 
        + kilometersSinceLastInspection +"Km"+ "\nNúmero de Inspecções: " + numberOfInspections + "\nEstacionado? : " + parked;
    }

    /**
     * Mostra no ecrã a informação sobre o camião.
     * 
     */
    public void show(){
        System.out.println(toString());
    }
}  

