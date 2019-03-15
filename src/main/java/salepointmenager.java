import java.util.Scanner;

public class salepointmenager {

    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        Wallet wallet = new Wallet();

        while(true){
            System.out.println("\n-------------------------------" +
                    "\n Welcome in main menu ! " +
                    " \n What would you like to do ? " +
                    "\n 1. Start selling" +
                    "\n 2. Info about current state \n");
            int answer = scanner.nextInt();
            switch(answer){
                case 1: Selling.beggining();
                break;
                case 2 : System.out.println(wallet.toString());
                break;
                default : System.out.println("wrong ans!");
            }

        }
    }
}
