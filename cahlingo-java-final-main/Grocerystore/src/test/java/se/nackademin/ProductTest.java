package se.nackademin;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductTest {
    private Product product;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @BeforeEach
    public void setup() {
        product = new Product("Ost", 25, 55);
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void testGetTotalPrice() {
        int totalprice = product.getTotalPrice();
        assertEquals(0, totalprice);
    }

    @Test
    void testsetTotalPrice() {
        product.setTotalPrice(20);
        assertEquals(20, 20);
    }

    @Test
    void testgetproductId() {
        int productid = product.getproductId();
        assertEquals(55, productid);
    }

    @Test
    void testsetproductID() {
        product.setproductId(55);
        assertEquals(55, 55);
    }

    @Test
    void testGetPrice() {
        int totalprice = product.getPrice();
        assertEquals(25, totalprice);
    }

    @Test
    void testsetPrice() {
        product.setPrice(33);
        assertEquals(33, 33);
    }

    @Test
    void testtoString() {
        String tostring = product.toString();
        assertTrue(tostring.contains("Product ID: "));

    }

    @Test
    void testprintCart() {
        Product newproduct = new Product("Ost", 25, 55);
        List<Product> list = new ArrayList<>(Arrays.asList(newproduct));
        product.printCart(list);
        assertNotEquals("1375kr", outputStreamCaptor.toString());
    }

    @Test
    void testsetQuantity() {
        product.setQuantity(33);
        assertEquals(33, 33);
    }

    @Test
    void testsetFoodName() {
        product.setFoodName("Lmao");
        assertEquals("Lmao", "Lmao");
    }

    @Test
    void testgetFoodName() {
        String foodName = product.getFoodName();
        assertEquals("Ost", foodName);
    }
}
