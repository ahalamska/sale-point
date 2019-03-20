import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

public class SaleManagerTest {

    public SaleManager saleManager = new SaleManager();
    public Product product1 = new Product("Book A", 2222222222222L, BigDecimal.TEN, BigInteger.TEN);
    public Product IncorrectProduct2 = new Product("Book A", 2222222222L, BigDecimal.TEN, BigInteger.TEN);
    public Product product2 = new Product("Book C",9999999999990L , BigDecimal.TEN,
            BigInteger.TEN);


    @Before
    public void setSaleManagerTest(){
        saleManager.getBoughtProducts().put(9999999999999L,BigInteger.TWO);
        saleManager.getBoughtProducts().put(product2.getId(),BigInteger.TWO);
        ProductManager.getInstance().getProductMap().put(product2.getId(), product2);

    }

    @Test
    public void updateAmountOfExistingProduct(){
       BigInteger beforeAmount = saleManager.getBoughtProducts().get(product2.getId());
       saleManager.updateAmount(product2, BigInteger.TWO);
       Assert.assertEquals(saleManager.getBoughtProducts().get(product2.getId()), beforeAmount.add(BigInteger.TWO));
    }

    @Test
    public void updateAmountOfNotExistingProduct(){
        saleManager.updateAmount(product1, BigInteger.TWO);
        Assert.assertEquals(saleManager.getBoughtProducts().get(product1.getId()), BigInteger.TWO);
    }

    @Test
    public void addNotExistingProduct(){
        BigDecimal toPayBefore = saleManager.getToPay();
        saleManager.addNextProduct(2221112221113L, BigInteger.ONE);
        Assert.assertEquals(saleManager.getToPay(),toPayBefore);
    }

    @Test
    public void addExistingProduct(){
        BigDecimal toPayBefore = saleManager.getToPay();
        saleManager.addNextProduct(product2.getId(), BigInteger.ONE);
        Assert.assertNotEquals(saleManager.getToPay(),toPayBefore);
    }
}