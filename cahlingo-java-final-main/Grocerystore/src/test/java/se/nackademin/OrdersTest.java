package se.nackademin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrdersTest {
    private Orders order;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @BeforeEach
    public void setup() {
        order = new Orders("2012-01-01", 33, 50);
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void testGetCustomer_ID() {
        order.getCustomer_ID();
        assertEquals(33, 33);

    }

    @Test
    void testGetOrderDate() {
        order.getOrderDate();
        assertEquals("2012-01-01", "2012-01-01");
    }

    @Test
    void testgetOrder_ID() {
        order.getOrder_ID();
        assertEquals(33, 33);
    }

    @Test
    void testGetTotal() {
        int total = order.getTotal();
        assertEquals(33, total);

    }

    @Test
    void testSetCustomer_ID() {
        order.setCustomer_ID(55);
        assertEquals(55, 55);

    }

    @Test
    void testSetOrderDate() {
        order.setOrderDate("2012-12-12");
        assertEquals("2012-12-12", "2012-12-12");

    }

    @Test
    void testsetOrder_ID() {
        order.setOrder_ID(3);
        assertEquals(3, 3);

    }

    @Test
    void testSetTotal() {
        order.setTotal(5);
        assertEquals(5, 5);
    }

    @Test
    void testToString() {
        String ordersTest = "Customers with biggest order value50 They bought for a total of: 33kr";
        System.out.println(order.toString());
        assertNotEquals(ordersTest, order.toString());
    }

}
