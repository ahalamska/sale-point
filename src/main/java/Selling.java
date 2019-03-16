import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@NoArgsConstructor
public class Selling {

    private BigDecimal toPay;
    private Map<Product, BigInteger> boughtProducts;

    /**
     * initialise selling
     */
    public void start() {
        toPay = new BigDecimal("0");
        boughtProducts = new HashMap<Product, BigInteger>();
        sellingLoop();
    }

    /**
     * Main selling loop : asking if end selling or continue
     */
    @SuppressWarnings("InfiniteLoopStatement")
    private void sellingLoop(){
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("More product ? (y/n)");
            String answer = scanner.next();
            if (answer.equals("y")){
                nextProduct();
            }
            else {
                endSelling();
                return;
            }
        }
    }

    /**
     * finishing selling : ask about paying by card of cash
     */

    private void endSelling() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("To pay : " + toPay.toString() + "\n" + "Pay by Card (y/n) + \n");
            String answer = scanner.next();
            if (answer.equals("y")) {
                Wallet.getInstance().sellByCard(toPay);
                return;
            } else if (answer.equals("n")) {
                Wallet.getInstance().sellByCash(toPay);
                return;
            }
            else System.out.println("Wrong answer!");
        }
    }

    /**
     * Update amount in bought products database
     */
    private void updateAmount(Product product, BigInteger amount){
        BigInteger newAmount = boughtProducts.get(product).add(amount);
        boughtProducts.remove(product);
        boughtProducts.put(product,newAmount);
    }

    /**
     * Scan product by Id (bar code) and send request to Database to sell Product
     */
    private void nextProduct() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Scan ID :");
        BigInteger id = scanner.nextBigInteger();
        System.out.println("Enter amount :");
        BigInteger amount = scanner.nextBigInteger();

        Product product = ProductMenager.instanceOf().sellingDecider(id, amount);
        if(product != null){
            if(boughtProducts.containsKey(product)){
                updateAmount(product, amount);
            }
            boughtProducts.put(product, amount);
        }
    }
}
