import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

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


    private void addProduct(Product product) {
        if(productMap.containsKey(product.getId())) {
            System.out.println("Already exists product with this id");
            return;
        }
        productMap.put(product.getId(), product);
    }


    public boolean sellProduct(Product soldProduct, BigInteger amount){
        if(soldProduct.getAmount().compareTo(amount) >= 0){
            soldProduct.setAmount(soldProduct.getAmount().subtract(amount));
            System.out.println(soldProduct.getName() + " in amount of " + amount.toString() + " is sold");
            return true;
        }
        else System.out.println("Not enough product of name : " + soldProduct.getName() + " \n " +
                "Available only : " + soldProduct.getAmount());
        return false;
    }


    public Optional<Product> search(Long id){
        return Optional.ofNullable(productMap.get(id));
    }



    public void enteringProduct(){


        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter new product name:");
        String name = scanner.nextLine();


        BigDecimal price;
        System.out.println("Enter new product price:");
        try {
            price = scanner.nextBigDecimal();
        }
        catch (InputMismatchException e){
            System.out.println("Wrong price: Maybe '.' instead ',' ");
            return;
        }


        System.out.println("Enter new product bar-code:");
        String stringId;
        try {
            stringId = scanner.next();
        }
        catch (InputMismatchException e){
            System.out.println("Invalid bar-code");
            return;
        }
        Long id;
        if(!Product.isBarCodeCorrect(stringId)) return;
        id = Long.parseLong(stringId);


        System.out.println("Enter new product amount:");
        BigInteger amount;
        try {
            amount = scanner.nextBigInteger();
        }
        catch (InputMismatchException e){
            System.out.println("Wrong amount. Required Integer");
            return;
        }

         addProduct(new Product(name, id, price, amount));
    }



}

