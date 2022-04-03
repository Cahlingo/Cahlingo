package se.nackademin;

public class Databasecommands {
    public final static String DROP_ALL_OBJECTS = "DROP ALL OBJECTS";
    public final static String QUERY_GROCERY_LIST = "select * from Groceries";
    public final static String INSERT_INTO_GROCERIES = "INSERT INTO Groceries (FOOD_NAME,PRICE) VALUES (?,?)";
    public final static String INSERT_INTO_ORDERS = "INSERT INTO Orders (OrderDate,Customer_ID,Total) VALUES (?,?,?)";
    public final static String INSERT_INTO_CUSTOMERS = "INSERT INTO Customers (Firstname,LastName,Adress,PhoneNumber) VALUES (?,?,?,?)";
    public final static String QUERY_BY_ORDERDATE = "SELECT * FROM Orders ORDER BY OrderDate";
    public final static String QUERY_LAST_Customer_ID = "SELECT Customer_ID FROM Customers ORDER BY Customer_ID DESC LIMIT 1;";
    public final static String QUERY_BIGGEST_ORDERS = "SELECT * FROM Orders ORDER BY Total DESC";
    public final static String QUERY_CUSTOMER_LIST = "SELECT * FROM Customers WHERE CUSTOMER_ID =";
    public final static String QUERY_LATEST_Order_ID = "SELECT Order_ID FROM Orders ORDER BY Order_ID DESC LIMIT 1;";
    public final static String CREATE_TABLE_GROCERIES = """
            create table Groceries (FOOD_ID INT NOT NULL AUTO_INCREMENT,
            FOOD_NAME varchar(32) NOT NULL,
            PRICE numeric(10,2) NOT NULL,
            PRIMARY KEY (FOOD_ID)
            )
            """;
    public final static String CREATE_TABLE_CUSTOMER = """
            create table Customers (Customer_ID INT NOT NULL AUTO_INCREMENT,
            Firstname varchar(32) NOT NULL,
            LastName varchar(32) NOT NULL,
            Adress varchar(32) NOT NULL,
            PhoneNumber INT NOT NULL,
            PRIMARY KEY (Customer_ID))
            """;
    public final static String CREATE_TABLE_ORDERS = """
            create table Orders (Order_ID INT NOT NULL AUTO_INCREMENT,
            OrderDate DATETIME NOT NULL,
            Customer_ID int NOT NULL,
            Total int NOT NULL,
            PRIMARY KEY (Order_ID),
            CONSTRAINT FK_CustomerOrders FOREIGN KEY (Customer_ID)
            REFERENCES Customers(Customer_ID))
            """;

    public final static String QUERY_MOST_VALUABLE_CUSTOMER = """
            SELECT  a.Customer_ID, a.Firstname,a.LastName,
            SUM(b.Total) total_value
            FROM Customers a
            LEFT JOIN Orders b
            ON a.Customer_ID = b.Customer_ID
            GROUP BY a.Customer_ID, a.Firstname
            ORDER BY total_value DESC""";

}
