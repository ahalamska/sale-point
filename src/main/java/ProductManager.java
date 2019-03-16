import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductManager {

    private  Map<Long, Product> productMap = new HashMap<Long, Product>();

    private static ProductManager instance = null;

    public static ProductManager getInstance(){
        if (instance == null){
            return instance = new ProductManager();
        }
        return  instance;
    }

    /**
     * Scan and add product to Database
     */
    public void addProduct()
    {
        Product product = Product.scanProduct();
        if(productMap.containsKey(product.getId())) {
            System.out.println("Already exists product with this id");
            return;
        }
        productMap.put(product.getId(), product);
    }

    /**
     *
     * @param soldProduct product we sell
     * @param amount of product that we want to remove
     */
    private void sellProduct(Product soldProduct, BigInteger amount){
        if(soldProduct.getAmount().compareTo(amount) >= 0){
            soldProduct.setAmount(soldProduct.getAmount().subtract(amount));
        }
        else System.out.println("Not enough product of name : " + soldProduct.getName() + " \n " +
                "Available only : " + soldProduct.getAmount());

    }

    /**
     *
     * @param  id of searched product
     * @return Product if found, null if not found
     */
    private Optional<Product> search(Long id){
        return Optional.ofNullable(productMap.get(id));
    }



    /**
     * decides if it's able to sell product
     * @param id - id of product we want to sell
     * @param amount - amount of product we want to sell
     */
    public Optional<Product> SellingDeciding(Long id, BigInteger amount){
        Optional<Product> optionalProduct = search(id);

        optionalProduct.ifPresentOrElse(product -> sellProduct(product, amount), ()
                -> System.out.println("Impossible to sell ! : Product with this ID doesn't exist"));

        return optionalProduct;


    }



}

