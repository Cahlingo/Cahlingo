package se.nackademin;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderDaoTest {
    OrderDao orderDao;
    CustomerDao customerDao;
    Databasehandler databasehandler;
    Connection conn;

    @BeforeEach
    void Setup() throws SQLException, FileNotFoundException {
        conn = DriverManager.getConnection("jdbc:h2:mem:supershop;");
        RunScript.execute(conn, new FileReader("test.sql"));
        databasehandler = new Databasehandler();
        orderDao = new OrderDao();

    }

    @AfterEach
    void dropTables() throws SQLException {
        Statement statement = conn.createStatement();
        statement.execute("DROP ALL OBJECTS");
    }

    @Test
    void testQueryBiggestOrders() throws SQLException {
        orderDao.InsertOrder(conn, 22, 1);
        String biggestOrder = orderDao.QueryBiggestOrders(conn);
        assertTrue(biggestOrder.contains("22"));
    }

    @Test
    void testGetOrderID() throws SQLException {
        orderDao.InsertOrder(conn, 22, 1);
        String getID = orderDao.GetOrderID(conn);
        assertEquals("1", getID);
    }

    @Test
    void testGetOrdersSortedByDate() throws SQLException {
        orderDao.InsertOrder(conn, 25, 1);
        orderDao.InsertOrder(conn, 22, 1);
        String sortDate = orderDao.GetOrdersSortedByDate(conn);
        assertTrue(sortDate.contains("20"));

    }

    @Test
    void testQueryCustomerHighestOrderValue() throws SQLException {
        orderDao.InsertOrder(conn, 25, 2);
        orderDao.InsertOrder(conn, 22, 1);
        String mostValuableCustomer = orderDao.QueryCustomerHighestOrderValue(conn);
        assertTrue(mostValuableCustomer.contains("Testson"));


    }
}
