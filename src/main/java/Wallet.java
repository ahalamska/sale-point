import lombok.Data;

import java.math.BigDecimal;

@Data
public class Wallet {

    private  static Wallet instance;

    private BigDecimal amountCard = new BigDecimal(0);

    private BigDecimal soldToday = new BigDecimal(0);


    public static synchronized Wallet getInstance(){
        if(instance == null) instance = new Wallet();
        return instance;
    }


    public String toString() {

        return " Amount Cash : " +  (this.soldToday.subtract(amountCard)).toString() +
                "\nAmount Card : " + this.amountCard.toString() +
                "\nSold Today : " + this.soldToday.toString() ;
    }


    void sellByCard(BigDecimal toPay) {
        this.soldToday = this.soldToday.add(toPay);
        this.amountCard = this.amountCard.add(toPay);
    }

    void sellByCash(BigDecimal toPay) {
        this.soldToday = this.soldToday.add(toPay);
    }
}
