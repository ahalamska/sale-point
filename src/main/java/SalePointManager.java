import java.util.Scanner;

public class SalePointManager {

    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        Wallet wallet = new Wallet();

        while(true){
            System.out.println("\n-------------------------------\n" +
                    "\n Welcome in main menu ! " +
                    " \n What would you like to do ? " +
                    "\n 1. Start selling" +
                    "\n 2. Info about current state " +
                    "\n 3. Add product " +
                    "\n-------------------------------\n");
            int answer = scanner.nextInt();
            switch(answer){
                case 1:
                    SaleManager saleManager = new SaleManager();
                    saleManager.start();
                break;

                case 2 : System.out.println(wallet.toString());
                break;

                case 3 : ProductManager.getInstance().addProduct();
                break;

                default : System.out.println("wrong ans!");
            }

        }
    }
}
