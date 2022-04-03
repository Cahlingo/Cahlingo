package se.nackademin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import static se.nackademin.Databasecommands.*;

public class ProductDao {

  public void InsertToProducts(Connection conn, String FoodName, int Price) throws SQLException {
    try (PreparedStatement insert = conn.prepareStatement(INSERT_INTO_GROCERIES)) {
      insert.setString(1, FoodName);
      insert.setInt(2, Price);
      insert.executeUpdate();
    } catch (SQLException e) {
      System.out.println(e);
    }
  }

  public List<Product> QueryProducts(Connection conn) {
    try (Statement stmt = conn.createStatement()) {
      ResultSet result = stmt.executeQuery(QUERY_GROCERY_LIST);
      List<Product> productlist = new ArrayList<>();
      while (result.next()) {
        Product currentProduct = new Product();
        currentProduct.setproductId(result.getInt("FOOD_ID"));
        currentProduct.setFoodName(result.getString("FOOD_NAME"));
        currentProduct.setPrice(result.getInt("Price"));
        productlist.add(currentProduct);
      }
      return productlist;
    } catch (SQLException e) {
      System.out.println(e);
    }
    return null;
  }
}
