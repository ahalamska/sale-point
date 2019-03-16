import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import java.math.BigInteger;
import java.util.Scanner;

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
  public static Product scanProduct (){
      Scanner scanner = new Scanner(System.in);
      System.out.println("Enter new product name:");
      String name = scanner.next();

      System.out.println("Enter new product price:");
      Float price = scanner.nextFloat();

      System.out.println("Enter new product amount:");
      BigInteger amount = scanner.nextBigInteger();

      System.out.println("Enter new product Id:");
      BigInteger id = scanner.nextBigInteger();

      return new Product(name, id, price, amount);

  }
}
