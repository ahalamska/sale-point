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

   public static boolean isBarCodeCorrect(String id){
      if(!id.matches("[0-9]{13}")){
         System.out.println("Invalid bar-code: Required 13 digit with no white sign");
         return false;
      }
      return true;
   }

}
