package se.nackademin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import static se.nackademin.Databasecommands.*;

public class Databasehandler {
  private final String db = "ICAONLINE";
  private String hostname = "localhost";
  private String userName = "username";
  private String password = "password"; // Never add hardcoded passwords to your code
  private Connection connection = null;
  private String port = "3306";

  OrderDao orderDetails = new OrderDao();

  public void setUsername(String userName) {
    this.userName = userName;
  }

  public String getUserName() {
    return userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void createDatabase(Connection conn) throws SQLException {
    String createString = "create database IF NOT EXISTS " + db;
    try (Statement stmt = conn.createStatement()) {
      stmt.executeUpdate(createString);
      conn.setCatalog(db);
    } catch (SQLException e) {
      System.out.println(e);
    }
  }

  public void createTable(Connection conn) throws SQLException {
    try (Statement CreateTable = conn.createStatement()) {
      CreateTable.executeUpdate(CREATE_TABLE_GROCERIES);
      CreateTable.executeUpdate(CREATE_TABLE_CUSTOMER);
      CreateTable.executeUpdate(CREATE_TABLE_ORDERS);
    } catch (SQLException e) {
      System.out.println(e);
    }
  }

  public void DropTable(Connection conn, String wich_table) throws SQLException {
    String createString = "drop table " + wich_table;
    try (Statement DropTable = conn.createStatement()) {
      DropTable.executeUpdate(createString);
    } catch (SQLException e) {
      System.out.println(e);
    }
  }

  public Connection getConnection() {
    if (connection != null)
      return connection;
    loginScreen();
    return connection;
  }

  private void loginScreen() {
    do {
      System.out.println("Username: ");
      String username = Menu.scanner.nextLine();
      System.out.println("Password: ");
      String password = Menu.scanner.nextLine();
      approveConnection(username, password);
    } while (connection == null);
  }

  private void approveConnection(String username, String password) {
    try {
      Properties connectionProps = new Properties();
      connectionProps.put("user", username);
      connectionProps.put("password", password);
      connection = DriverManager.getConnection("jdbc:mysql://" + this.hostname + ":" + this.port,
          connectionProps);
      System.out.println("Connected to db");
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }
}
