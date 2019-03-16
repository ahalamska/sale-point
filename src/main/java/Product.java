import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;

@Data
@AllArgsConstructor


public class Product {

   private String name;

   private Long id;

   private BigDecimal price;

   //TODO create new AmountManager class and add there amount-counting functionality
   private BigInteger amount;





}
