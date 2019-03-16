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


    public void addProduct(Product product)
    {
        if(productMap.containsKey(product.getId())) {
            System.out.println("Already exists product with this id");
            return;
        }
        productMap.put(product.getId(), product);
    }


    private void sellProduct(Product soldProduct, BigInteger amount){
        if(soldProduct.getAmount().compareTo(amount) >= 0){
            soldProduct.setAmount(soldProduct.getAmount().subtract(amount));
        }
        else System.out.println("Not enough product of name : " + soldProduct.getName() + " \n " +
                "Available only : " + soldProduct.getAmount());

    }


    private Optional<Product> search(Long id){
        return Optional.ofNullable(productMap.get(id));
    }




    public Optional<Product> SellingDeciding(Long id, BigInteger amount){
        Optional<Product> optionalProduct = search(id);

        optionalProduct.ifPresentOrElse(product -> sellProduct(product, amount), ()
                -> System.out.println("Impossible to sell ! : Product with this ID doesn't exist"));

        return optionalProduct;


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

        scanner = new Scanner(System.in);
        System.out.println("Enter new product Id:");
        String stringId = scanner.nextLine();
        Long id;
        if(stringId.matches("[0-9]{13}")){
            id = Long.getLong(stringId);
        }
        else {
            System.out.println("Wrong Id : Required 13 digit with no white sign");
            return;
        }


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

