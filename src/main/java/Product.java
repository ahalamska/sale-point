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


    /**
     * sell product if it's possible
     * @param amount of removing product
     *
     */
  public void sell(BigInteger amount){
     if(this.amount.compareTo(amount) >= 0){
         this.amount = this.amount.subtract(amount);
     }
     else System.out.println("Product cannot be sold");
  }


    /**
     * Scan product form stdout
     * @return Scanned product
     */
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
