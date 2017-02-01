import java.util.*;//ser mais preciso
/**
 * Representa um armazém, na qual são armazenados packs.
 * 
 * @author 160221052  Ricardo Carmo.
 * @author 160221072  Miguel Lobato.
 * @version 31/1/2017
 */
public class Storage{
    private ArrayList<Pack> packsInStorage;

    public Storage()
    {
        packsInStorage = new ArrayList<Pack>();
    }

    /**
     * Permite importar um produto para o armazém.
     * 
     * @param    pack    produto que se quer importar.
     */
    public void importPack(Pack pack){
        boolean packExists = false;
        if(pack != null)
            if(!packsInStorage.isEmpty()){
                for(Pack storedPack: packsInStorage)
                    if(storedPack.getCode() == pack.getCode()){
                        storedPack.addQuantity(pack.getQuantity());
                        packExists = true;
                        break;
                    }

                if(!packExists)
                    packsInStorage.add(pack);

            }
            else
                packsInStorage.add(pack);

        else
            System.out.print("Pack Inválido!");
    }

    /**
     * Permite exportar um produto se ele existir em armazém, se houver mais quantidade em armazem do que o que foi introduzido, 
     * a quantidade do pack em armazém é reduzida, caso seja igual o pack em armazém é retirado.
     * 
     * @param  code    Código do produto.
     * @param  quantity   Quantidade do produto.
     * @return um objeto da classe Pack com o código e quantidade igual ao introduzido.
     * 
     */public Pack exportPack(int code, int quantity){
        Pack packWithLowQuantity = null;
        boolean hasCode = false;

        if(!hasError(code, quantity)){

            for(Pack storedPack: packsInStorage)
                if(storedPack.getCode() == code)
                    if(storedPack.getQuantity() > quantity){
                        storedPack.removeQuantity(quantity);
                        hasCode = true;
                        return copyPack(code, quantity);
                    }
                    else if(storedPack.getQuantity() == quantity){                    
                        Pack exportPack = copyPack(code, quantity);
                        hasCode = true;
                        packsInStorage.remove(storedPack);
                        return exportPack;
                    }                     
                    else{
                        packWithLowQuantity = storedPack;
                        hasCode = true;
                    }                                  

            if(!hasCode)
                System.out.println("O Pack Com o Código Introduzido Não Existe em Armazém!");  
        }

        if(packWithLowQuantity != null)
            System.out.print("Não é possivel exportar um pack de código:" + code + " e quantidade:" + quantity + ", só é possivel com quantidade menor ou igual a "
                + packWithLowQuantity.getQuantity() + "  ");

        return null;
    }

    private boolean hasError(int code, int quantity){
        if(code <= 0){
            System.out.print("Código Inválido!");
            return true;
        }

        if(quantity <= 0){
            System.out.print("Quantidade Inválida!");
            return true;
        }
        
        return false;
    }

    private Pack copyPack(int code, int quantity){//copia um pack já feito
        return new Pack(code, quantity);        
    }

    /**
     * Devolve o número de packs guardados em armazém, usado para testes.
     * 
     * @return número de packs guardados em armazém.
     */
    public int getNumberOfStoredPacks(){
        return packsInStorage.size();
    }

    /**
     * Produz um texto para mostrar a informação sobre os produtos em armazem.
     * 
     */public String toString(){
        String exportString = "";
        if(!packsInStorage.isEmpty()){
            exportString = "*** Informação Sobre os Packs em Armazém ***\n";
            for(Pack storedPack: packsInStorage)
                storedPack.toString();
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
