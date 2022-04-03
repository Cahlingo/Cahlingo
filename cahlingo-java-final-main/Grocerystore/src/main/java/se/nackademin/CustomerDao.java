package se.nackademin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static se.nackademin.Databasecommands.*;
import java.util.Iterator;

public class CustomerDao {

  public boolean CheckIfCustomerExists(Connection conn, int choice) {
    String test = "SELECT * FROM Customers WHERE CUSTOMER_ID =" + choice;
    try (Statement stmt = conn.createStatement()) {
      ResultSet result = stmt.executeQuery(test);
      if (result.next()) {
        return true;
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return false;
  }

  public int GetLatestCustomerID(Connection conn) {
    int latestID = 0;
    try (Statement stmt = conn.createStatement()) {
      ResultSet result = stmt.executeQuery(QUERY_LAST_Customer_ID);

      if (result.next())
        latestID = result.getInt("Customer_ID");

    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return latestID;
  }

  public void InsertNewCustomer(Connection conn, List<Customer> newcustomer) {
    try (PreparedStatement insert = conn.prepareStatement(INSERT_INTO_CUSTOMERS)) {
      Iterator<Customer> it = newcustomer.iterator();
      while (it.hasNext()) {
        Customer c = it.next();
        insert.setString(1, c.getFirstName());
        insert.setString(2, c.getLastName());
        insert.setString(3, c.getAdress());
        insert.setDouble(4, c.getPhoneNumber());
        insert.executeUpdate();
      }
    } catch (SQLException e) {
      System.out.println(e);
    }
  }
}
