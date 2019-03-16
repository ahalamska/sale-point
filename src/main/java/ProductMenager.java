import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductMenager {

    private  Map<BigInteger, Product> productMap = new HashMap<BigInteger, Product>();

    private static ProductMenager instance = null;

    public static ProductMenager instanceOf(){
        if (instance == null){
            return instance = new ProductMenager();
        }
        return  instance;
    }

    /**
     * Scan and add product to Database
     */
    public void addProduct()
    {
        Product product = Product.scanProduct();
        productMap.put(product.getId(), product);
    }

    /**
     *
     * @param soldProduct product we sell
     * @param amount of product that we want to remove
     * @return soldProduct or null if it's impossible to sell
     */
    private Product sellProduct(Product soldProduct, BigInteger amount){
        soldProduct.sell(amount);
        return soldProduct;
    }

    /**
     *
     * @param  id of searched product
     * @return Product if found, null if not found
     */
    private Product search(BigInteger id){
        return productMap.get(id);
    }

    /**
     *
     * @param product we want to check
     * @return true if product is available false otherwise
     */
    private boolean isAvailable(Product product, BigInteger amount){
        if(product == null){
            return false;
        }
        return product.getAmount().compareTo(amount) >= 0;
    }

    /**
     * decides if it's able to sell product
     * @param id - id of product we want to sell
     * @param amount - amount of product we want to sell
     */
    public Product sellingDecider(BigInteger id, BigInteger amount){
        Product product = search(id);
        if(isAvailable(product, amount)){
            sellProduct(product, amount);
            return product;
        }
        System.out.println("Impossible to sell !");
        return null;


    }



}

