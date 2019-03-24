import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class WalletTest {


    @Test
    public void sellByCardTest() {
    BigDecimal amountCardBefore = new BigDecimal(String.valueOf(Wallet.getInstance().getAmountCard()));
    BigDecimal soldTodayBefore = new BigDecimal(String.valueOf(Wallet.getInstance().getSoldToday()));
    Wallet.getInstance().sellByCard(BigDecimal.TEN);

    Assert.assertEquals(Wallet.getInstance().getAmountCard(), amountCardBefore.add(BigDecimal.TEN));
    Assert.assertEquals(Wallet.getInstance().getSoldToday(), soldTodayBefore.add(BigDecimal.TEN));
    }

    @Test
    public void sellByCash() {
        BigDecimal soldTodayBefore = new BigDecimal(String.valueOf(Wallet.getInstance().getSoldToday()));
        Wallet.getInstance().sellByCash(BigDecimal.TEN);

        Assert.assertEquals(Wallet.getInstance().getSoldToday(), soldTodayBefore.add(BigDecimal.TEN));
    }

}