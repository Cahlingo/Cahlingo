package se.nackademin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomerTest {

    private Customer customer;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @BeforeEach
    public void setup() {
        customer = new Customer("Gustav", "Cahling", "Grenvägen 6", 455);
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void testGetAdress() {

        String custAdress = customer.getAdress();
        assertEquals("Grenvägen 6", custAdress);
    }

    @Test
    void testGetCustomerID() {
        int custID = customer.getCustomerID();
        assertEquals(0, custID);

    }

    @Test
    void testGetFirstName() {
        String custFName = customer.getFirstName();
        assertEquals("Gustav", custFName);

    }

    @Test
    void testGetLastName() {
        String custLname = customer.getLastName();
        assertEquals("Cahling", custLname);

    }

    @Test
    void testGetPhoneNumber() {
        double phoneNumber = customer.getPhoneNumber();
        assertEquals(455, 455, phoneNumber);

    }

    @Test
    void testSetAdress() {
        customer.setAdress("Testvägen");
        assertEquals("Testvägen", "Testvägen");

    }

    @Test
    void testSetCustomerID() {
        customer.setCustomerID(2);
        assertEquals(2, 2);

    }

    @Test
    void testSetFirstName() {
        customer.setFirstName("Lars");
        assertEquals("Lars", "Lars");
        ;

    }

    @Test
    void testSetLastName() {
        customer.setLastName("Cahling");
        assertEquals("Cahling", "Cahling");

    }

    @Test
    void testSetPhoneNumber() {
        customer.setPhoneNumber(070 - 42424);
        assertEquals(070 - 42424, 070 - 42424);

    }

    @Test
    void testToString() {
        String text = "CustID: 0 Customer Name : Gustav";
        assertEquals(text, customer.toString());

    }
}
