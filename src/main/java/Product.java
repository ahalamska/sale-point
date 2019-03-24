import lombok.AllArgsConstructor;
import lombok.Data;


import java.math.BigDecimal;
import java.math.BigInteger;


@Data
@AllArgsConstructor


public class Product {

   private String name;

   private Long id;

   private BigDecimal price;

   //TODO create new AmountManager class and add there amount-counting functionality
   private BigInteger amount;



}
