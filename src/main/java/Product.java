import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import java.math.BigInteger;

@Data
@AllArgsConstructor

@Entity
public class Product {

   private String name;
   private BigInteger id;
   private Float price;
   private BigInteger amount;

  public Integer buy(BigInteger amount){
     if(this.amount.compareTo(amount) < 0){
        return -1;
     }
     this.amount = this.amount.subtract(amount);
     return 1;

  }
  public Product (){
      System.out.println("P");
  }
}
