/**
 * Gere as coodenadas dos objetos das classes Lorry, Garage, Shop e SupplyStation
 * 
 * @author 160221052  Ricardo Carmo.
 * @author 160221072  Miguel Lobato.
 * @version 8/1/2017
 * 
 */public class Coordinates
{
    private double latitude;
    private double longitude;

    public Coordinates(double latitude,double longitude){
        if(coordinatesAreCorrect(latitude, longitude)){
            this.latitude = latitude;
            this.longitude = longitude;
        }
        else{
            this.latitude = 0.0;
            this.longitude = 0.0;
        }
    }

    private boolean coordinatesAreCorrect(double latitude,double longitude){
        if(latitude >= -90.0 && latitude <= 90.0 && longitude >= -180.0 && longitude <= 180.0)
            return true;
        return false;
    }

    /**
     * Modifica a latitude e longitude
     * 
     * @param   latitude    Implementa a latitude de um objecto atraves de um número real.
     * @param   longitude   Implementa a longitude de um objecto atraves de um numero real.
     * 
     */public void setCoordinates(double latitude,double longitude){
        if(coordinatesAreCorrect(latitude, longitude)){
            this.latitude = latitude;
            this.longitude = longitude;
        }
        else
            System.out.println("Coordenadas Inválidas!");
    }

    /**
     * Permite obter a latitude
     * 
     * @return   A latitude de um objecto.
     * 
     */public double getLatitude(){
        return latitude;
    }

    /**
     * Permite obter a longitude
     * 
     * @return   A longitude de um objecto.
     * 
     */public double getLongitude(){
        return longitude;
    }

    private double integralPart(double coordinate){ //calculos internos
        return coordinate - fractionalPart(coordinate);
    }   

    private double fractionalPart(double coordinate){ //calculos internos
        return coordinate % 1;
    }

    /**
     * Permite calcular a distância de um ponto (coordenadas atuais) ao outro (coodenadas intruduzidas)
     * 
     * @param  newLatitude   A nova latitude de um objecto dado por um número real.
     * @param  newLongitude  A nova longitude de um objecto dado por um número real.
     * 
     * @return   Um número real  da distancia percorrida.
     * 
     */
    public double distanceTravelled(double newLatitude, double newLongitude){ 
        if(coordinatesAreCorrect(latitude, longitude))
            return (Math.sqrt(Math.pow(latitude - newLatitude,2) + Math.pow(longitude - newLongitude,2)) * (60*1852)) / 1000;   
        return 0.0;
    }

    /**
     * Permite mover um objecto para outro local atraves das coordenadas.
     * 
     * @param  newLatitude   A nova latitude dada por um número real.
     * @param  newLongitude  A nova longitude dada por um número real.
     * 
     */
    public void moveTo(double newLatitude, double newLongitude){
        if(coordinatesAreCorrect(newLatitude, newLongitude)){            
            latitude = newLatitude;
            longitude = newLongitude;
        }
        else
            System.out.println("Coordenadas Inválidas!");
    }

    /**
     * Permite comparar coordenada
     * 
     * @return   devolve true caso sejam iguais e false se não forem
     * 
     */
    public boolean compareCoordinates(Lorry lorry){
        if((Math.abs(latitude - lorry.getLatitude()) < 0.001) && (Math.abs(longitude - lorry.getLongitude()) < 0.001))
            return true;
        return false;

        /*String lorryLatitude = "" + lorry.getLatitude();
        String lorryLongitude = "" + lorry.getLongitude();
        String shopLatitude = "" + latitude;
        String shopLongitude = "" + longitude;

        if(shopLatitude.equals(lorryLatitude))
        if(shopLongitude.equals(lorryLongitude))
        return true;
        return false;*/
    }

    private String toStringDecimal(){//dá-nos a longitude e latitude em decimal.
        return "" + latitude + "º " + longitude + "º";   
    }

    private String toStringGMS(){ //graus, minutos e segundos
        String exportCoordinate = ""; 

        double latitudeToConvert = latitude;
        char cardinalPointLatitude = ' ';

        double longitudeToConvert = longitude;
        char cardinalPointLongitude = ' ';

        cardinalPointLatitude= (latitudeToConvert<0) ? 'S' : 'N';
        latitudeToConvert=Math.abs(latitudeToConvert);
        cardinalPointLongitude= (longitudeToConvert<0) ? 'W'  : 'E';        
        longitudeToConvert=Math.abs(longitudeToConvert);

        exportCoordinate = (int) integralPart(latitudeToConvert) + "º ";        
        latitudeToConvert = fractionalPart(latitudeToConvert)*60;        
        exportCoordinate += (int) integralPart(latitudeToConvert) + "' ";        
        latitudeToConvert = fractionalPart(latitudeToConvert)*60;        
        exportCoordinate += (int) Math.round(latitudeToConvert) + "'' " + cardinalPointLatitude + "  ";

        exportCoordinate += (int) integralPart(longitudeToConvert) + "º ";        
        longitudeToConvert = fractionalPart(longitudeToConvert)*60;        
        exportCoordinate  += (int) integralPart(longitudeToConvert) + "' ";        
        longitudeToConvert = fractionalPart(longitudeToConvert)*60;        
        exportCoordinate  += (int) Math.round(longitudeToConvert) + "'' " + cardinalPointLongitude;     

        return exportCoordinate;        
    }

    /**
     * Produz um texto com as coordenadas em decimal e em GMS
     * 
     * @return    Um texto com as coordenadas em decimal e GMS.
     * 
     */public String toString(){        
        return "Coordenadas Decimais: " + toStringDecimal() + "\n" + "Coordenadas GMS: " + toStringGMS();        
    }

    /**
     * Mostra no ecrã as coordenadas do objecto.
     * 
     */public void show(){
        System.out.println(toString());
    }
}