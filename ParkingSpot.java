
/**
 * Escreva a descrição da classe ParkingSpot aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class ParkingSpot
{
    private Lorry lorry;
    private Container container;

    public ParkingSpot(Object object){
        if(object.getClass().getName().equals("Container")){
            lorry = null;
            container = (Container) object;        
        }else if(object.getClass().getName().equals("Lorry")){
            lorry = (Lorry) object;
            container = null;
        }else{
            lorry = null;
            container = null;
        }
    }

    public Object getParked(){
        if(lorry != null)
            return lorry; 
        else
            return container;
    }
}
