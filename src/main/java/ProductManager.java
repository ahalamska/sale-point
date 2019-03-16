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

    private  Map<Long, Product> productMap = new HashMap<>();

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


    private void sellProduct(Product soldProduct, BigInteger amount){
        if(soldProduct.getAmount().compareTo(amount) >= 0){
            soldProduct.setAmount(soldProduct.getAmount().subtract(amount));
        }
        else System.out.println("Not enough product of name : " + soldProduct.getName() + " \n " +
                "Available only : " + soldProduct.getAmount());

    }


    private Optional<Product> search(Long id){
        return Optional.ofNullable(productMap.get(id));
    }




    public Optional<Product> SellingDeciding(Long id, BigInteger amount){
        Optional<Product> optionalProduct = search(id);

        optionalProduct.ifPresentOrElse(product -> sellProduct(product, amount), ()
                -> System.out.println("Impossible to sell ! : Product with this ID doesn't exist"));

        return optionalProduct;


    }



}

