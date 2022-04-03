package se.nackademin;

import org.h2.tools.RunScript;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class CartHandlerTest {
  CartHandler cartHandler;
  CustomerDao customerDao;
  OrderDao orderDao;
  Databasehandler databasehandler;
  Connection conn;
  MockedStatic<promptInt> input;
  List<Product> checker;

  @BeforeEach
  void Setup() throws SQLException, FileNotFoundException {
    input = mockStatic(promptInt.class);
    conn = DriverManager.getConnection("jdbc:h2:mem:supershop;");
    RunScript.execute(conn, new FileReader("test.sql"));
    customerDao = new CustomerDao();
    orderDao = new OrderDao();
    databasehandler = new Databasehandler();
    cartHandler = new CartHandler();
    cartHandler.LoadGroceryList(conn);
    checker = CartHandler.UpdatedCart;

  }

  @AfterEach
  void tearDown() throws SQLException {
    input.close();
    Statement statement = conn.createStatement();
    statement.execute("DROP ALL OBJECTS");
    checker.clear();

  }

  // Adding procuct Apple -
  @Test
  void testAddToCart() {
    input.when(() -> promptInt.promptInts(anyString())).thenReturn(1).thenReturn(5);
    cartHandler.AddToCart();

    assertEquals(1, checker.size());
    assertThat(checker.get(checker.size() - 1).getFoodName(), equalTo("Apple"));
  }

  // Adding product Apple then removing it.
  @Test
  void testRemoveFromCart() {
    input.when(() -> promptInt.promptInts(anyString())).thenReturn(1).thenReturn(5).thenReturn(1);
    cartHandler.AddToCart();
    assertEquals(1, checker.size());
    cartHandler.RemoveFromCart();
    assertEquals(0, checker.size());

  }

  @Test
  void testGetRandomItem() {
    assertEquals(0, checker.size());
    cartHandler.getRandomItem();
    assertEquals(checker, checker);
    assertEquals(1, checker.size());
  }

  @Test
  void testCompleteOrderExistingCustomer() throws SQLException {
    input.when(() -> promptInt.promptInts(anyString())).thenReturn(1).thenReturn(5);
    cartHandler.AddToCart();
    input.when(() -> promptInt.promptInts("Input CustomerID")).thenReturn(3);
    cartHandler.CompleteOrder(conn);
    String getOrderID = orderDao.GetOrderID(conn);
    int getExisting = customerDao.GetLatestCustomerID(conn);
    String order = getOrderID + " " + getExisting;
    assertEquals("1 3", order);

  }

  @Test
  void testCompleteOrderNewCustomer() throws SQLException {
    input.when(() -> promptInt.promptInts(anyString())).thenReturn(1).thenReturn(5);
    cartHandler.AddToCart();
    input.when(() -> promptInt.promptInts("Input CustomerID")).thenReturn(4);
    input.when(() -> promptInt.promptString(anyString())).thenReturn("Dempa").thenReturn("Cahling")
        .thenReturn("Testway");
    input.when(() -> promptInt.promptInts("Input Phone Number")).thenReturn(35353535);
    cartHandler.CompleteOrder(conn);
    int getNewCustID = customerDao.GetLatestCustomerID(conn);
    assertEquals(4, getNewCustID);

  }
}
