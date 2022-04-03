package se.nackademin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class CartHandler {
    private volatile boolean called;
    private ProductDao productdao = new ProductDao();
    private CustomerDao customerDao = new CustomerDao();
    private OrderDao orderDao = new OrderDao();
    static List<Product> UpdatedCart = new ArrayList<>();
    private Texthandler txthandler = new Texthandler();
    private Product product = new Product();
    private List<Product> inStock = new ArrayList<Product>();

    public synchronized void LoadGroceryList(Connection conn) {
        if (called)
            return;
        called = true;
        LoadGrocerylist(conn);
    }

    public void LoadGrocerylist(Connection conn) {
        List<Product> groceryList = productdao.QueryProducts(conn);
        inStock.addAll(groceryList);
    }

    public void Grocerylist() {
        System.out.println("\nHere is a full list of groceries");
        for (Product groceries : inStock) {
            System.out.println(groceries);
        }
    }

    public void AddToCart() {
        Grocerylist();
        int choice = promptInt.promptInts("Wich Item you want to add to cart? ");
        for (Product groceries : inStock) {
            if (groceries.getproductId() == choice) {
                int quantity = promptInt.promptInts("How many?");
                groceries.setQuantity(quantity);
                UpdatedCart.add(groceries);
                product.printCart(UpdatedCart);
            }
        }
    }

    // Remove from cart, using interator to delete both value and Index by using ID
    public void RemoveFromCart() {
        System.out.println(UpdatedCart);
        int choice = promptInt.promptInts("Which item you want to remove? ID: ");
        Iterator<Product> iterator = UpdatedCart.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getproductId() == choice) {
                iterator.remove();
            }
            System.out.println("Updated cart ");
            product.printCart(UpdatedCart);
        }
    }

    public Product getRandomItem() {
        int rand = new Random().nextInt(inStock.size());
        Product randProduct = inStock.get(rand);
        randProduct.setQuantity(1);
        UpdatedCart.add(randProduct);
        product.printCart(UpdatedCart);
        return randProduct;
    }

    public void CompleteOrder(Connection conn) throws SQLException {
        if (UpdatedCart.isEmpty()) {
            System.out.println("Cart is empty bruh");
            return;
        }

        else {
            System.out.println("We'll need some info from you.. :");
            int customerID = promptInt.promptInts("Input CustomerID");

            if (customerDao.CheckIfCustomerExists(conn, customerID))
                registerOrderExistingCustomer(conn, customerID);

            else
                registerOrderNonExistingCustomer(conn, customerID);

        }
    }

    public void registerOrderExistingCustomer(Connection conn, int customerID) throws SQLException {
        int total = product.SaveTotal(UpdatedCart);
        orderDao.InsertOrder(conn, total, customerID);
        System.out.println("Order Created!" + " Your orderID: " + orderDao.GetOrderID(conn));
        UpdatedCart.clear();
    }

    public void registerOrderNonExistingCustomer(Connection conn, int customerID) throws SQLException {
        System.out.println("Not in database, input your details...");
        List<Customer> newCustomer = txthandler.createNewCustomer();
        customerDao.InsertNewCustomer(conn, newCustomer);
        System.out.println("Heres your new customer ID: " + customerDao.GetLatestCustomerID(conn));
        int tot = product.SaveTotal(UpdatedCart);
        orderDao.InsertOrder(conn, tot, customerDao.GetLatestCustomerID(conn));
        UpdatedCart.clear();
        System.out.println("Order Created!" + " Your orderID: " + orderDao.GetOrderID(conn));
    }
}
