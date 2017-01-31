
/**
 * Escreva a descrição da classe AutomatedDistribution aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class AutomatedDistribution
{
    /**
     * COnstrutor para objetos da classe AutomatedDistribution
     */
    public AutomatedDistribution()
    {
    }

    /**
     * Simula a ia de um camião a uma estação de distribuição, este ser carregado com um dado bem, a sua ia a uma loja e a sua consequente descarga;
     * Este no final pode regressar à estação de distribuição se a variável returnToSupplyStation for verdadeira.
     * 
     * @param  lorry   O camião a ser usado para o transporte do bem.
     * @param  supplyStation   A estação de distribuição de onde o bem é proviniente.
     * @param  shop   A loja onde o bem deverá ser entrege.
     * @param  productCode   O código do produto a transportar.
     * @param  productQuantity   A quantidade do produto a transportar.
     * @param  returnToSupplyStation   Váriavel booleana que se for verdadeira permite o regresso do camião à estação de distribuição.
     * @return     a soma de x com y 
     */
    public void distribute(Lorry lorry, SupplyStation supplyStation, Shop shop , Integer productCode, int productQuantity, boolean returnToSupplyStation)
    {
        if(parametersAreValid(lorry, supplyStation, shop , productCode, productQuantity)){
            
            lorry.moveLorry(supplyStation.getLatitude(), supplyStation.getLongitude());
            
            supplyStation.loadLorry(lorry, productCode, productQuantity);
            
            lorry.moveLorry(shop.getLatitude(), shop.getLongitude());
            
            int containerCode = lorry.getContainer().getCode();
            shop.unloadLorry(lorry);
            shop.unloadContainerSingle(containerCode, productCode, productQuantity);
            shop.loadLorryWithContainer(lorry, containerCode);
            
            if(returnToSupplyStation)
                lorry.moveLorry(supplyStation.getLatitude(), supplyStation.getLongitude());
        }
    }

    private boolean parametersAreValid(Lorry lorry, SupplyStation supplyStation, Shop shop , Integer productCode, int productQuantity){
        if(lorry == null){
            System.out.println("Camião Inválido!");
            return false;
        }

        if(supplyStation == null){
            System.out.println("Estação De Distribuição Inválida!");
            return false;
        }

        if(shop == null){
            System.out.println("Loja Inválida!");
            return false;
        }

        if(productCode == null || productCode < 0){
            System.out.println("O Código Do Produto É Inválido!");
            return false;
        }

        if(!ProductManagement.productIsRegisted(productCode)){
            System.out.println("O Código Do Produto Não Está Registado!");
            return false;
        }
        
        if(productQuantity <= 0){
            System.out.println("Quantidade Inválida!");
            return false;
        }
        
        return true;
    }
}
