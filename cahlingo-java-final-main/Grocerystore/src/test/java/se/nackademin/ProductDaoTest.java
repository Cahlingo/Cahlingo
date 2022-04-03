package se.nackademin;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.h2.tools.RunScript;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductDaoTest {
    private static ProductDao productDao;
    private static Connection conn;

    @BeforeEach
    void Setup() throws SQLException, FileNotFoundException {
        conn = DriverManager.getConnection("jdbc:h2:mem:supershop;");
        RunScript.execute(conn, new FileReader("test.sql"));
        productDao = new ProductDao();

    }

    @AfterEach
    void dropTables() throws SQLException {
        Statement statement = conn.createStatement();
        statement.execute("DROP ALL OBJECTS");
    }

    @Test
    void testInsertToGroceries() throws SQLException {
        productDao.InsertToProducts(conn, "Majs", 22);
        List<Product> test = (productDao.QueryProducts(conn));
        assertThat(test.get(test.size() - 1).getFoodName(), equalTo("Majs"));

    }

    @Test
    void testQueryProducts() throws SQLException {
        List<Product> test = (productDao.QueryProducts(conn));
        assertThat(test.get(test.size() - 1).getFoodName(), equalTo("Milk"));

    }
}
