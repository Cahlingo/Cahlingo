package se.nackademin;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Databasehandler dbhandler = new Databasehandler();
        Connection conn = dbhandler.getConnection();
        ProductDao productDao = new ProductDao();
        dbhandler.createDatabase(conn);
        dbhandler.createTable(conn);
        productDao.InsertToProducts(conn,"Banana",5);
        productDao.InsertToProducts(conn,"Apple",3);
        productDao.InsertToProducts(conn,"Entrecote",200);
        productDao.InsertToProducts(conn,"Corn Flakes",30);
        productDao.InsertToProducts(conn,"Gorbys Pirog",11);
        productDao.InsertToProducts(conn,"Vanilla Icecream",30);
        productDao.InsertToProducts(conn,"NOCCO",25);
        productDao.InsertToProducts(conn,"Celsius",20);
        productDao.InsertToProducts(conn,"Cookies",50);
        productDao.InsertToProducts(conn,"Vanilla Bun",10);
        productDao.InsertToProducts(conn,"Minced Meat",150);
        productDao.InsertToProducts(conn,"Flour",30);
        productDao.InsertToProducts(conn,"Spaghetti",45);
        productDao.InsertToProducts(conn,"Caviar",75);
        productDao.InsertToProducts(conn,"Milk",35);
        Menu menu = new Menu();
        menu.printMenu(conn);
    }
}