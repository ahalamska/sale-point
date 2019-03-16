import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

@Data
@AllArgsConstructor


public class Product {

   private String name;

   private Long id;

   private BigDecimal price;

   private BigInteger amount;



    /**
     * Scan product form stdout
     * @return Scanned product
     */
  public static Product scanProduct (){
      Scanner scanner = new Scanner(System.in);
      System.out.println("Enter new product name:");
      String name = scanner.next();

      System.out.println("Enter new product price:");
      BigDecimal price = scanner.nextBigDecimal();

      System.out.println("Enter new product amount:");
      BigInteger amount = scanner.nextBigInteger();

      System.out.println("Enter new product Id:");
      Long id = scanner.nextLong();

      return new Product(name, id, price, amount);

  }
}
