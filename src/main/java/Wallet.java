import lombok.Data;

@Data
public class Wallet {

    private  Wallet instance = null;

    private Integer amountCash;

    private Integer amountCard;

    private Integer soldToday;


    public   Wallet getInstance(){
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


}
