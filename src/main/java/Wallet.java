import lombok.Data;

import java.math.BigDecimal;

@Data
public class Wallet {

    private  static Wallet instance = null;

    private Integer amountCash;

    private Integer amountCard;

    private Integer soldToday;


    public static  Wallet getInstance(){
        if(instance == null)
            return instance = new Wallet();
        return instance;
    }

    public String toString() {

        return " Amount Cash : " + Integer.toString(this.amountCash) +
                "\nAmount Card : " + Integer.toString(this.amountCard) +
                "\nSold Today : " + Integer.toString(this.amountCard) ;
    }

    public Wallet(){
        this.amountCard = 0;
        this.amountCash = 0;
        this.soldToday = 0;
    }


    public void sellByCard(BigDecimal toPay) {
    }

    public void sellByCash(BigDecimal toPay) {
    }
}
