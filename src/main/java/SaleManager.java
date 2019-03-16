import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

@NoArgsConstructor
public class SaleManager {

    private BigDecimal toPay;
    private Map<Long, BigInteger> boughtProducts;

    /**
     * initialise selling
     */
    public void start() {
        toPay = new BigDecimal("0");
        boughtProducts = new HashMap<Long, BigInteger>();
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
        if(boughtProducts.containsKey(product.getId())){
            boughtProducts.merge(product.getId(),amount, BigInteger::add);
        }
        else{
            boughtProducts.put(product.getId(), amount);
        }

    }


    /**
     * Scan product by Id (bar code) and send request to Database to sell Product
     */
    private void nextProduct() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Scan ID :");
        Long id = scanner.nextLong();
        System.out.println("Enter amount :");
        BigInteger amount = scanner.nextBigInteger();

        Optional<Product> optionalProduct = ProductManager.getInstance().SellingDeciding(id, amount);

        optionalProduct.ifPresent(product -> {
            updateAmount(product,amount);
            BigDecimal cost = (product.getPrice().multiply(new BigDecimal(amount)));
            toPay = toPay.add(cost);
            });
    }
}
