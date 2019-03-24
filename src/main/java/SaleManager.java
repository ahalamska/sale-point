import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@NoArgsConstructor
@Getter
public class SaleManager {

    private BigDecimal toPay = new BigDecimal(0);
    private Map<Long, BigInteger> boughtProducts= new HashMap<>();  ;


    public void start() {
        toPay = new BigDecimal("0");
        boughtProducts = new HashMap<>();
        sellingLoop();
    }


    public void updateAmount(Product product, BigInteger amount){
        if(boughtProducts.containsKey(product.getId())){
            boughtProducts.merge(product.getId(),amount, BigInteger::add);
        }
        else{
            boughtProducts.put(product.getId(), amount);
        }
    }


    @SuppressWarnings("InfiniteLoopStatement")
    private void sellingLoop(){
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Add next product ? (y/n) \n exit (e)");
            String answer;
            try {
                answer = scanner.nextLine();
            }
            catch (InputMismatchException e){
                System.out.println("Wrong answer !");
                scanner = new Scanner(System.in);
                continue;
            }
            switch (answer) {
                case "y":
                    scanNextProduct();
                    break;
                case "n":
                    endSelling();
                    return;
                case "e":
                    return;
                default:
                    System.out.println("Wrong answer !");
                    break;
            }
        }
    }

    /**
     * finishing selling : ask about paying by card of cash
     */

    private void endSelling() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("To pay : " + toPay.toString() + " PLN" + "\n" + "Pay by Card (y/n)?\n exit (e) + \n");
            String answer;
            try {
                answer = scanner.nextLine();
            }
            catch (InputMismatchException e){
                System.out.println("Wrong answer !");
                scanner = new Scanner(System.in);
                continue;
            }
            switch (answer) {
                case "y":
                    Wallet.getInstance().sellByCard(toPay);
                    printReceipt();
                    return;
                case "n":
                    Wallet.getInstance().sellByCash(toPay);
                    printReceipt();
                    return;
                case "e":
                    return;
                default:
                    System.out.println("Wrong answer!");
                    return;
            }
        }

    }

    private void printReceipt() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        System.out.println("No-name\n" +
                "everything you want\n" +
                "ul.BestInternship 20/48\n" +
                "https://www.linkedin.com/in/alicja-halamska-965875166/\n" +
                dateFormat.format(date)+
                "-----------------------------------------28657\n" +
                "Paragon Fiskalny\n" );
        for(Map.Entry<Long,BigInteger> entry : boughtProducts.entrySet()){
            Optional<Product> optionalProduct = ProductManager.getInstance().search(entry.getKey());
            optionalProduct.ifPresent(product -> {
                System.out.println(product.getName() + "     " +
                        entry.getValue().toString() + "szt. x" +
                        product.getPrice().toString() + "\n" + " PLN");
            });
        }
        System.out.println("-------------------------------\n"+
                "SUMA PLN    " + this.toPay);

    }


    /**
     * Scan product by Id (bar code) and send request to Database to sell Product
     */
    private void scanNextProduct() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Scan bar-code :");
        String stringId;
        try {
            stringId = scanner.next();
        }
        catch(InputMismatchException e){
            System.out.println("Wrong ID scanned, try again");
            return;
        }
        Long id;
        if(ProductManager.isBarCodeIncorrect(stringId)) return;
        id = Long.parseLong(stringId);


        System.out.println("Enter amount :");
        BigInteger amount;
        try {
            amount = scanner.nextBigInteger();
        }
        catch (InputMismatchException e){
            System.out.println("Wrong amount scanned, try again");
            return;
        }
        addNextProduct(id,amount);
    }


    public void addNextProduct(Long id, BigInteger amount) {
        Optional<Product> optionalProduct = ProductManager.getInstance().search(id);

        optionalProduct.ifPresentOrElse(product -> {
                    if(ProductManager.getInstance().isAvailable(product, amount)){
                        updateAmount(product,amount);
                        BigDecimal cost = (product.getPrice().multiply(new BigDecimal(amount)));
                        toPay = toPay.add(cost);
                    }}
                ,() -> System.out.println("Product not found : Product with this ID doesn't exist"));

    }
}
