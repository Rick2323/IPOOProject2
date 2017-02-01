/**
 * Representa um lugar num parque de estacionamento, podendo este ter um camião ou um contentor.
 * 
 * @author 160221052  Ricardo Carmo.
 * @author 160221072  Miguel Lobato.
 * @version 31/1/2017
 */
public class ParkingSpot{
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
