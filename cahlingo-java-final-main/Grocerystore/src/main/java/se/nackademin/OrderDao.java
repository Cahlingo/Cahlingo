package se.nackademin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import static se.nackademin.Databasecommands.*;

public class OrderDao {
  long now = System.currentTimeMillis();
  Timestamp sqlTimestamp = new Timestamp(now);

  public void InsertOrder(Connection conn, int total, int customerID) throws SQLException {
    try (PreparedStatement insert = conn.prepareStatement(INSERT_INTO_ORDERS)) {
      insert.setTimestamp(1, sqlTimestamp);
      insert.setInt(2, customerID);
      insert.setInt(3, total);
      insert.executeUpdate();
    } catch (SQLException e) {
      System.out.println(e);
    }
  }

  public String GetOrderID(Connection conn) throws SQLException {
    String values = "";
    try (Statement stmt = conn.createStatement()) {
      ResultSet result = stmt.executeQuery(QUERY_LATEST_Order_ID);
      while (result.next()) {
        values = result.getString("Order_ID");
        return values;
      }
    } catch (SQLException e) {
      System.out.println(e);
    }
    return values;
  }

  public String GetOrdersSortedByDate(Connection conn) {
    System.out.println("---- Order list sorted on orderDate ----");
    System.out.println("Order_ID---" + "--Order Date----" + "Customer_ID " + "Total");
    String orderSortDated = "";
    try (Statement stmt = conn.createStatement()) {
      ResultSet result = stmt.executeQuery(QUERY_BY_ORDERDATE);
      while (result.next()) {
        orderSortDated += result.getString("Order_ID") +
            "\t" + result.getString("OrderDate") + "\t" + result.getString("Customer_ID") +
            "\t" + result.getString("Total") + "kr" + "\n";

      }
      return orderSortDated;
    } catch (SQLException e) {
      System.out.println(e);
    }
    return orderSortDated;
  }

  public String QueryBiggestOrders(Connection conn) {
    System.out.println("---- Full list Biggest orders all time ---- ");
    System.out.println("Order_ID---" + "--Order Date----" + "Customer_ID " + "Total");
    String biggestOrders = "";
    try (Statement stmt = conn.createStatement()) {
      ResultSet result = stmt.executeQuery(QUERY_BIGGEST_ORDERS);
      while (result.next()) {
        biggestOrders += result.getString("Order_ID") +
            "\t" + result.getString("OrderDate") + "\t" + result.getString("Customer_ID") +
            "\t" + result.getString("Total") + "kr\n";

      }
      return biggestOrders;

    } catch (SQLException e) {
      System.out.println(e);
    }
    return biggestOrders;
  }

  public String QueryCustomerHighestOrderValue(Connection conn) {
    System.out.println("---- Full list of customer with highest order value ---- ");
    System.out.println("CustID--" + "Firstname" + " " + "Lastname" + "\tTotal");
    String mostValuableCustomer = "";
    try (Statement stmt = conn.createStatement()) {
      ResultSet result = stmt.executeQuery(QUERY_MOST_VALUABLE_CUSTOMER);
      while (result.next()) {
        mostValuableCustomer += result.getString("Customer_ID") +
            "\t " + result.getString("Firstname") + "\t " + result.getString("LastName") +
            "\t" + result.getInt("total_value") + "kr\n";

      }
      return mostValuableCustomer;
    } catch (SQLException e) {
      System.out.println(e);
    }
    return mostValuableCustomer;
  }
}
