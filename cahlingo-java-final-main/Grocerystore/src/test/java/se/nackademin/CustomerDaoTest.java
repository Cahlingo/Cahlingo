package se.nackademin;

import org.junit.jupiter.api.BeforeEach;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerDaoTest {
    CustomerDao customerdao;
    Databasehandler databasehandler;
    Connection conn;

    @BeforeEach
    void Setup() throws SQLException, FileNotFoundException {
        conn = DriverManager.getConnection("jdbc:h2:mem:supershop;");
        RunScript.execute(conn, new FileReader("test.sql"));
        databasehandler = new Databasehandler();
        customerdao = new CustomerDao();

    }

    @AfterEach
    void dropTables() throws SQLException {
        Statement statement = conn.createStatement();
        statement.execute("DROP ALL OBJECTS");
    }

    @Test
    void testCheckCustomerExists() throws SQLException {
        int testID = 1;
        int testWrongID = 4;
        boolean inList = customerdao.CheckIfCustomerExists(conn, testID);
        boolean notInList = customerdao.CheckIfCustomerExists(conn, testWrongID);
        assertEquals(true, inList);
        assertEquals(false, notInList);
        System.out.println(inList + " " + notInList);
    }

    @Test
    void testGetLatestCustomerID() throws SQLException {
        int custID = customerdao.GetLatestCustomerID(conn);
        System.out.println(custID);
        assertEquals(3, custID);

    }
}
