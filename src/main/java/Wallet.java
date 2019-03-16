import lombok.Data;

import java.math.BigDecimal;

@Data
public class Wallet {

    private  static Wallet instance = null;

    private BigDecimal amountCash = new BigDecimal(0);

    private BigDecimal amountCard = new BigDecimal(0);

    private BigDecimal soldToday = new BigDecimal(0);


    public static  Wallet getInstance(){
        if(instance == null)
            return instance = new Wallet();
        return instance;
    }

    public String toString() {

        return " Amount Cash : " +  this.amountCash.toString() +
                "\nAmount Card : " + this.amountCard.toString() +
                "\nSold Today : " + this.soldToday.toString() ;
    }


    public void sellByCard(BigDecimal toPay) {
    }

    public void sellByCash(BigDecimal toPay) {
    }
}
