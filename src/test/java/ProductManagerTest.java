import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.Assert.*;

public class ProductManagerTest {

    public ProductManager productManager = new ProductManager();
    public Product product1 = new Product("Book A", 2222222222222L, BigDecimal.TEN, BigInteger.TEN);
    public Product IncorrectProduct2 = new Product("Book A", 2222222222L, BigDecimal.TEN, BigInteger.TEN);
    public Product product2 = new Product("Book C",9999999999990L , BigDecimal.TEN,
            BigInteger.TEN);

    @Before
    public void setProductManager(){
        productManager.getProductMap().put(9999999999999L,new Product("Book B",9999999999999L , BigDecimal.TEN,
                BigInteger.TEN) );
        productManager.getProductMap().put(product2.getId(),product2 );
    }

    @Test
    public void addCorrectProduct() {
        productManager.addProduct(product1);
        assertTrue(productManager.getProductMap().containsKey(product1.getId()));
    }

    @Test
    public void addIncorrectProduct() {
        productManager.addProduct(IncorrectProduct2);
        assertFalse(productManager.getProductMap().containsKey(IncorrectProduct2.getId()));
    }
    @Test
    public void addExistingProduct(){
        int beforeSize = productManager.getProductMap().size();
        productManager.getProductMap().put(product2.getId(), product2);
        productManager.addProduct(product2);
        assertEquals(beforeSize, productManager.getProductMap().size());
    }


    @Test
    public void sellNotAvailableProduct() {
        assertFalse(productManager.isAvailable(product2,new BigInteger("12")));
    }

    @Test
    public void sellAvailableProduct() {
        assertTrue(productManager.isAvailable(product2,new BigInteger("2")));
    }
    @Test
    public void subtractProduct(){
        BigInteger beforeAmount = productManager.getProductMap().get(product2.getId()).getAmount();
        productManager.subtractAmount(product2,beforeAmount);
        assertEquals(0, productManager.getProductMap().get(product2.getId()).getAmount().intValue());
    }

    @Test
    public void Search() {
        assertTrue(productManager.search(product2.getId()).isPresent());
        assertFalse(productManager.search(1000000000000L).isPresent());
    }
}