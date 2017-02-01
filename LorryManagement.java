import java.util.*;//ser mais preciso
/**
 * Gere os camiões.
 * 
 * @author 160221052  Ricardo Carmo.
 * @author 160221072  Miguel Lobato.
 * @version 31/1/2017
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

    /**
     * Regista o camião indicado no HasSet 
     * 
     * @param lorry O a ser registado
     */
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

    /**
     * Verifica se um camião já está registado, usando o código do mesmo.
     * 
     * @param code Código do camião.
     * 
     * @return Retorna verdadeiro se houver um camião com o mesmo código já registado.
     */
    public boolean lorryIsRegisted(Integer code){ //é public para ser usado nos testes
        if(codeIsValid(code))
            for(Lorry lorry: registedLorrys)
                if(lorry.getCode() == code)
                    return true;
        return false;
    }

    private boolean lorryIsRegisted(Lorry lorry){//Verifica se um camião já está registado, usando o código em sí.
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

    /**
     * Anula o registo previo de um camião através do seu código.
     * 
     * @param code O código do camião.
     */
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

    /**
     * Devolve uma String com a informação dos camiões a uma dada distância(fornecida pelo utilizador)
     * de um ponto de Coordenadas(também fornecidas pelo utilizador).
     * 
     * @param latitude Coordenada do ponto.
     * @param longitude Coordenada do ponto.
     * @param distance Distância ao ponto.
     * 
     * @return Uma String com a informação.
     */
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

    /**
     * Mostra no ecrã a informação dos camiões a uma dada distância(fornecida pelo utilizador)
     * de um ponto de Coordenadas(também fornecidas pelo utilizador).
     * 
     * @param latitude Coordenada do ponto.
     * @param longitude Coordenada do ponto.
     * @param distance Distância ao ponto.
     */
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
     * Mostra no ecrã toda informação sobre os camiões registados.
     */
    public void showAllRegistedLorrys(){
        System.out.println(toStringAllLorrys());
    }

    private ArrayList<Lorry> getListOfLorrysThatNeedInspection(){
        ArrayList<Lorry> listOfLorrys = new ArrayList<Lorry>();

        registedLorrys.stream().filter(lorry -> lorry.needsInspection()).forEach(lorry -> listOfLorrys.add(lorry));  

        return listOfLorrys;
    }

    /**
     * Devolve uma String com a informação dos camiões que precisão de inspeção.
     * 
     * @return @return Uma String com a informação.
     */
    public String lorrysThatNeedInspection(){
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

    /**
     * Mostrano ecrã a informação dos camiões que precisão de inspeção.
     */
    public void showLorrysThatNeedInspection(){
        System.out.println(lorrysThatNeedInspection());
    }

    /**
     * Devolve o número de camiões considerados novos.
     * 
     * @return número de camiões considerados novos.
     */
    public int getNumberOfNewLorrys(){
        return (int) registedLorrys.stream().filter(lorry -> lorry.isNew()).count();
    }

    /**
     * Devolve o número de camiões considerados vellhos.
     * 
     * @return número de camiões considerados novos.
     */
    public int getNumberOfOldLorrys(){
        return (int) registedLorrys.stream().filter(lorry -> lorry.isOld()).count();
    }
    
    /**
     * Limpa o HasMap dos camiões registados, é usado para testes.
     */
    public static void clearRegistedProducts(){
        registedLorrys.clear();    
    }
}
