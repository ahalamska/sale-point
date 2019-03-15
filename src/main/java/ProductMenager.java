import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductMenager {

    private  Map<BigInteger, Product> productMap = new HashMap<BigInteger, Product>();

    private ProductMenager instance = null;

    public ProductMenager instanceOf(){
        if (instance == null){
            return instance = new ProductMenager();
        }
        return  instance;
    }

    public void addProduct(Product product){
        productMap.put(product.getId(), product);
    }

}

