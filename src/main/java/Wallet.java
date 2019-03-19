import java.math.BigDecimal;


public class Wallet {

    private  static Wallet instance = null;

    private BigDecimal amountCard = new BigDecimal(0);

    private BigDecimal soldToday = new BigDecimal(0);


    public static  Wallet getInstance(){
        if(instance == null)
            return instance = new Wallet();
        return instance;
    }


    public String toString() {

        return " Amount Cash : " +  (this.soldToday.subtract(amountCard)).toString() +
                "\nAmount Card : " + this.amountCard.toString() +
                "\nSold Today : " + this.soldToday.toString() ;
    }


    void sellByCard(BigDecimal toPay) {
        this.soldToday = this.soldToday.add(toPay);
        System.out.println("Sold" + soldToday);
        this.amountCard = this.amountCard.add(toPay);
    }

    void sellByCash(BigDecimal toPay) {
        this.soldToday = this.soldToday.add(toPay);
    }
}
