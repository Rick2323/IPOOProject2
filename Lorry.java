/**
 * Classe utilizada para a gestão dos camiões.
 * Permite carregar e descarregar os camiões com contentores. Leva-los à inspecção
 * e saber quantos quilometros dista a ultima.
 * 
 * @author 160221052  Ricardo Carmo.
 * @author 160221072  Miguel Lobato.
 * @version 31/1/2017
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

    /**
     * Regista um camião.
     * 
     * @param lorry O camião a registar.
     */
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

    /**
     * Devolve a designação do camião.
     * 
     * @return String com a designação.
     */
    public String getDesignation(){
        return designation;
    }

    /**
     * Permite modificar a designação do camião.
     * 
     * @param name Nova designação.
     */
    public void setDesignation(String name){
        if(name != null)
            designation = name;
    }

    /**
     * Modifica se um camiao está estacionado.
     */
    public void setParked(){
        if(parked)
            parked = false;
        else
            parked = true;
    }

    /**
     * Verifica se o camião está estacionado.
     * 
     * @return Verdadeiro se estiver estacionado.
     */
    public boolean isParked(){    
        return parked;
    }   

    /**
     * Permite carregar o camião com um contentor.
     * 
     * @param importContainer    O contentor que se quer por no camião.
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
     * @return  O contentor descarregado se o camião tiver algum carregado previamente.
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
     * Permite levar um camião à inspecção(kilometersSinceLastInspection = 0).
     */
    public void inspection(){        
        kilometersSinceLastInspection = 0;
        numberOfInspections++;
    }
    
    /**
     * Devolve o número de inspeções efetuadas.
     * 
     * @return Número de inspeções efetuadas.
     */
    public int getNumberOfInspections(){
        return numberOfInspections;
    }
    
    /**
     * Devolve os Km desde a última inspeção.
     * 
     * @return Km desde a última inspeção.
     */
    public double getKilometersSinceLastInspection(){
        return kilometersSinceLastInspection;
    }

    /**
     * Permite deslocar o camião se este não estiver estacionado, para determinado sítio dando-lhe as coordenadas.
     * 
     * @param  latitude   A latitude que se deseja para o camião.
     * @param  longitude  A longitude que se deseja para o camião.
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
     * Permite saber a latitude do camião e retorna um número real.
     * 
     * @return   A latitude do camião.
     */
    public double getLatitude(){          //usado na classe shop
        return position.getLatitude();
    }

    /**
     * Permite saber a longitude do camião e retorna um número real.
     * 
     * @return   A longitude do camião.
     * 
     */
    public double getLongitude(){        //usado na classe shop
        return position.getLongitude();
    }

    /**
     * Verifica saber se um camião tem um contentor.
     * 
     * @return   Verdadeiro se Tiver contentor.
     */
    public boolean hasContainer(){
        if(container != null)
            return true;
        else
            return false;
    }

    /**
     * Devolve o contentor do camião.
     * 
     * @return O contentor se o tiver, senão retorna null.
     */
    public Container getContainer(){
        return container;
    }

    /**
     * Devolve o total de Km percorridos pelo camião.
     * 
     * @return O total de Km percorridos pelo camião.
     */
    public double getTotalKilometers(){
        return totalKilometers;
    }

    /**
     * Verifica de o camião é considerado novo.
     * 
     * @return Verdadeiro se o camião é considerado novo.
     */
    public boolean isNew(){
        return (totalKilometers < 50000);
    }

    /**
     * Verifica de o camião é considerado velho.
     * 
     * @return Verdadeiro se o camião é considerado velho.
     */
    public boolean isOld(){
        return (totalKilometers > 1000000);
    }

    /**
     * Verifica de o camião precida de inspeção.
     * 
     * @return Verdadeiro se precisar de inspeção.
     */
    public boolean needsInspection(){
        return (kilometersSinceLastInspection > 10000);
    }

    /**
     * Move o camião até passar o total de Km fornecido como parâmetro, usado para testes.
     * 
     * @param totalKm total de Km que o camião terá de fazer.
     */
    public void moveLorryToReach(double totalKm){
        double currentLatitude = getLatitude();        
        double currentLongitude = getLongitude();
        while(getTotalKilometers() < totalKm){
            moveLorry(0, 0);
            moveLorry(0, 180);
        }
        moveLorry(currentLatitude, currentLongitude);
    }

    /**
     * Produz um texto informativo sobre um camião.
     * 
     * @return String com o texto informativo.
     */
    public String toString(){
        String containerInformation = "";

        if(container != null)
            containerInformation = container.toString();
        else
            containerInformation = "Nenhum Contentor Disponível";
        return  "*** Informação Sobre o Camião ***\n" + "Designação: " + designation + "\nNúmero: " + code + "\n" + containerInformation + "\n" + position.toString() 
        + "\n" + "Total Quilómetros Percorridos: " + totalKilometers +"Km"+ "\nQuilómetros Percorridos Desde a Última Inspecção: " 
        + kilometersSinceLastInspection +"Km"+ "\nNúmero de Inspecções: " + getNumberOfInspections() + "\nEstacionado? : " + parked;
    }

    /**
     * Mostra no ecrã a informação sobre o camião. 
     */
    public void show(){
        System.out.println(toString());
    }
}  

