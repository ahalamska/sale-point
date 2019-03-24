import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SalePointManagerMenu {

    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        SaleManager saleManager = new SaleManager();

        ProductManager.getInstance().getProductMap().put(2222222222222L, new Product("Test1", 2222222222222L,
                BigDecimal.ONE, BigInteger.TEN));
        ProductManager.getInstance().getProductMap().put(2222222222220L, new Product("Test2", 2222222222220L,
                BigDecimal.ZERO, BigInteger.TEN));


        while(true){

            System.out.println("\n-------------------------------\n" +
                    "\n" + "__          __  _                          \n" + "\\ \\        / / | |                         \n" + " \\ \\  /\\  / /__| | ___ ___  _ __ ___   ___ \n" + "  \\ \\/  \\/ / _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\\n" + "   \\  /\\  /  __/ | (_| (_) | | | | | |  __/\n" + "    \\/  \\/ \\___|_|\\___\\___/|_| |_| |_|\\___|\n" + "                                           "

                    + "\n" + " _                         _                                    \n" + "(_)             " +
                    "          (_)                                   \n" + " _ _ __    _ __ ___   __ _ _ _ __    _ __ ___   ___ _ __  _   _ \n" + "| | '_ \\  | '_ ` _ \\ / _` | | '_ \\  | '_ ` _ \\ / _ \\ '_ \\| | | |\n" + "| | | | | | | | | | | (_| | | | | | | | | | | |  __/ | | | |_| |\n" + "|_|_| |_| |_| |_| |_|\\__,_|_|_| |_| |_| |_| |_|\\___|_| |_|\\__,_|\n" + "                                                                \n" + "  "+

                    " \n What would you like to do ? " +
                    "\n 1. Start selling" +
                    "\n 2. Print report " +
                    "\n 3. Add product to data base " +
                    "\n 4. Exit " +
                    "\n-------------------------------\n");
            int answer;
            try {
                answer = scanner.nextInt();
            }
            catch(InputMismatchException e ){
                System.out.println("wrong ans!");
                scanner = new Scanner(System.in);
                continue;
            }

            switch(answer){
                case 1: saleManager.start();
                break;

                case 2 : System.out.println(Wallet.getInstance().toString());
                break;

                case 3 : ProductManager.getInstance().enteringProduct();
                break;

                case 4: return;

                default : System.out.println("wrong ans!");
            }
            System.in.read();
            scanner.reset();

        }
    }
}
