import java.util.*;//ser mais preciso
/**
 * Escreva a descrição da classe LorryManagement aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class LorryManagement
{
    private static HashSet<Lorry> registedLorrys = new HashSet<Lorry>();

    /**
     * COnstrutor para objetos da classe LorryManagement
     */
    public LorryManagement()
    {
    }

    public void registerLorry(Lorry lorry){
        if(lorry != null)
            if(registedLorrys.isEmpty())//se o HashMap estiver vazio adiciona-o logo 
                registedLorrys.add(lorry);

            else{ 
                if(!lorryIsRegisted(lorry) && !lorryIsRegisted(lorry.getCode()))//vai ver se não há camioes repetidos
                    registedLorrys.add(lorry);
                else
                    System.out.println("O Contentor Já Se Encontra Registado!"); 
            }
        else
            System.out.println("Contentor Inválido");
    }

    private boolean lorryIsRegisted(Integer code){
        if(codeIsValid(code))
            for(Lorry lorry: registedLorrys)
                if(lorry.getCode() == code)
                    return true;
        return false;
    }

    private boolean lorryIsRegisted(Lorry lorry){
        if(lorry != null && registedLorrys.contains(lorry))
            return true;
        else
            return false;
    }   

    private boolean codeIsValid(Integer code){
        if(code >= 0 && code != null)
            return true;
        else
            return false;
    }

    private Lorry getLorryByCode(int code){
        for(Lorry lorry: registedLorrys)
            if(lorry.getCode() == code)
                return lorry;
        return null;
    }

    public void unregisterLorry(Integer code){
        if(codeIsValid(code))
            if(lorryIsRegisted(code))
                registedLorrys.remove(getLorryByCode(code));
            else
                System.out.println("O Contentor Não Está Registado!");
        else
            System.out.println("Código Inválido!");        
    }

    /**
     * Permite calcular a distância entre dois pontos.
     * 
     * @param  newLatitude   A nova latitude de um objecto dado por um número real.
     * @param  newLongitude  A nova longitude de um objecto dado por um número real.
     * 
     * @return   Um número real  da distancia percorrida.
     * 
     */
    private double distanceBetweenPoints(Lorry lorry, double newLatitude, double newLongitude){ 
        if(lorry != null){
            if(coordinatesAreCorrect(newLatitude, newLongitude))        
                return (Math.sqrt(Math.pow(lorry.getLatitude() - newLatitude,2) + Math.pow(lorry.getLongitude() - newLongitude,2)) * (60*1852)) / 1000;  
        }else
            System.out.println("Camião Inválido!");
        return 0.0;
    }

    private boolean coordinatesAreCorrect(double latitude,double longitude){
        if(latitude >= -90.0 && latitude <= 90.0 && longitude >= -180.0 && longitude <= 180.0)
            return true;
        else
            System.out.println("Coordenadas Inválidas!");
        return false;
    }

    public String toStringLorrysWithin(double latitude,double longitude, double distance){
        if(distance > 0){
            double lorryDistance = 0.0;
            boolean existsLorryWithinDistance = false;
            String exportString = "***** Lista de Camiões A Uma Distância de "  + distance + "Km *****\n";
            for(Lorry lorry: registedLorrys){
                lorryDistance = distanceBetweenPoints(lorry, latitude, longitude);

                if(lorryDistance <= distance){
                    exportString += lorry.toString() + "\nDistância Ao Ponto De Coordenadas (" + longitude + ", " + longitude + "): "+ lorryDistance + "\n";
                    existsLorryWithinDistance = true;
                }
            }

            if(!existsLorryWithinDistance)
                exportString += "Nenhum Camião Dentro Da Distância Indicada!";

            return exportString;
        }
        else
            System.out.println("Distância Inválida!");
        return null;
    }

    public void showLorrysWithin(double latitude,double longitude, double distance){
        System.out.println(toStringLorrysWithin(latitude, longitude, distance));
    }

    private String toStringAllLorrys(){
        String exportString = "***** Lista de Camiões Registados *****\n";

        if(!registedLorrys.isEmpty())        
            for(Lorry lorry : registedLorrys)
                exportString += lorry.toString() + "\n";
        else
            exportString += "Nenhum Camião Registado!";

        return exportString;
    }

    /**
     * Mostra no ecrã toda informação sobre os contentores registados.
     * 
     */public void showAllRegistedContainers(){
        System.out.println(toStringAllLorrys());
    }

    private ArrayList<Lorry> getListOfLorrysThatNeedInspection(){
        ArrayList<Lorry> listOfLorrys = new ArrayList<Lorry>();

        registedLorrys.stream().filter(lorry -> lorry.needsInspection()).forEach(lorry -> listOfLorrys.add(lorry));  

        return listOfLorrys;
    }

    private String lorrysThatNeedInspection(){
        ArrayList<Lorry> listOfLorrys = getListOfLorrysThatNeedInspection();
        String exportString = "*** Lista De Camiões Que Necessitam Ser Inspecionados ***";

        exportString += "Número: " + listOfLorrys.size();
        if(!exportString.isEmpty())
            for(Lorry lorry: listOfLorrys)
                exportString += lorry.toString();
        else
            exportString += "Nenhum Camião Precisa de Inspeção!";

        return exportString;
    }

    public void showLorrysThatNeedInspection(){
        System.out.println(lorrysThatNeedInspection());
    }

    public int getNumberOfNewLorrys(){
        return (int) registedLorrys.stream().filter(lorry -> lorry.isNew()).count();
    }

    public int getNumberOfOldLorrys(){
        return (int) registedLorrys.stream().filter(lorry -> lorry.isOld()).count();
    }
}
