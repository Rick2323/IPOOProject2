import java.util.*;//ser mais preciso
/**
 * Esta classe representa um armazem, na qual sao armazenados packs
 * 
 * 
 * @author 160221052  Ricardo Carmo.
 * @author 160221072  Miguel Lobato.
 * @version 8/1/2017
 */public class Storage
{
    private ArrayList<Pack> packsInStorage;

    public Storage()
    {
        packsInStorage = new ArrayList<Pack>();
    }

    /**
     * Permite importar um produto para o armazém.
     * 
     * @param    pack    produto que se quer importar.
     * 
     */public void importPack(Pack pack){
        if(pack != null)
            if(!packsInStorage.isEmpty()){

                for(Pack storedPack: packsInStorage)
                    if(storedPack.getCode() == pack.getCode()){
                        storedPack.addQuantity(pack.getQuantity());
                        break;
                    }

            }
            else
                packsInStorage.add(pack);
            
        else
            System.out.print("Pack Inválido!");
    }

    /**
     * Permite exportar um produto se ele existir em armazém, se houver mais quantidade em armazem do que o que foi introduzido, 
     * a quantidade do pack em armazem é reduzida, caso seja igual o pack em armazem é retirado.
     * 
     * @param  code    Código do produto.
     * @param  quantity   Quantidade do produto.
     * @return um objeto da classe Pack com o código e quantidade igual ao introduzido.
     * 
     */public Pack exportPack(int code, int quantity){
        Pack packWithLowQuantity = null;
        boolean hasCode = false;

        if(code >= 0){
            if(quantity > 0){
                for(Pack storedPack: packsInStorage)
                    if(storedPack.getCode() == code)
                        if(storedPack.getQuantity() > quantity){
                            storedPack.removeQuantity(quantity);
                            hasCode = true;
                            return copyPack(code, storedPack.getName(), quantity, storedPack.getWeight(), storedPack.getVolume());

                        }
                        else if(packsInStorage[i].getQuantity() == quantity){                    
                            Pack exportPack = copyPack(code, packsInStorage[i].getName(), quantity, packsInStorage[i].getWeight(), packsInStorage[i].getVolume());
                            hasCode = true;
                            for (int j = i ; j < numberOfPacks ; j++){
                                packsInStorage[j] = packsInStorage[j+1];                                                              
                            }
                            numberOfPacks--;  

                            return exportPack;
                        }                     
                        else{
                            packWithLowQuantity = packsInStorage[i];
                            hasCode = true;
                        }                    
                

                if(!hasCode)
                    System.out.println("O Pack Com o Código Introduzido Não Existe em Armazém!");             

            }else 
                System.out.print("Quantidade Inválida!");
        }else 
            System.out.print("Código Inválido!");

        if(packWithLowQuantity != null)
            System.out.print("Não é possivel exportar um pack de código:" + code + " e quantidade:" + quantity + ", só é possivel com quantidade menor ou igual a "
                + packWithLowQuantity.getQuantity() + "  ");

        return null;
    }

    private Pack copyPack(int code, String name, int quantity, double weight, double volume){//copia um pack já feito
        return new Pack(code, name, quantity, weight, volume);        
    }

    /**
     * Produz um texto para mostrar a informação sobre os produtos em armazem.
     * 
     */public String toString(){
        String exportString = "";
        if(numberOfPacks > 0){
            exportString = "*** Informação Sobre os Packs em Armazém ***\n";
            for(int i=0; i < numberOfPacks; i++)
                exportString += "Código: " + packsInStorage[i].getCode() + "\tNome: " + packsInStorage[i].getName() + "\t\tQuantidade: " + packsInStorage[i].getQuantity()
                + "\t\tPeso: " + packsInStorage[i].getTotalWeight() + "\tVolume: " + packsInStorage[i].getTotalVolume() +"\n";
        }
        else
            exportString = "Armazém vazio!";
        return exportString;
    }

    /**
     * Mostra no ecrã a informação sobre os produtos em armazem. 
     * 
     */public void show(){
        System.out.print(toString()); 
    }
}
