package se.nackademin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    private OrderDao orderinfo = new OrderDao();
    private CartHandler carthndl = new CartHandler();
    public static Scanner scanner = new Scanner(System.in);

    public void printMenu(Connection conn) throws SQLException {
        String[] options = { "\n\n1.[Show Full Grocery List]",
                "2.[Add item to Cart]",
                "3.[Remove item from cart]",
                "4.[Add random item to cart]",
                "5.[Complete Order]",
                "6.[ORDER STATISTICS]" };
        for (String option : options) {
            System.out.println(option);
        }
        MenuMethod(conn);
    }

    public void MenuMethod(Connection conn) throws SQLException {
        carthndl.LoadGroceryList(conn);
        int option = 1;
        while (option != 8) {
            try {
                option = promptInt.promptInts("Choose your option:");
                switch (option) {

                    case 1:
                        carthndl.Grocerylist();
                        printMenu(conn);
                        break;

                    case 2:
                        carthndl.AddToCart();
                        printMenu(conn);
                        break;

                    case 3:
                        carthndl.RemoveFromCart();
                        printMenu(conn);
                        break;

                    case 4:
                        carthndl.getRandomItem();
                        printMenu(conn);
                        break;

                    case 5:
                        carthndl.CompleteOrder(conn);
                        printMenu(conn);
                        break;

                    case 6:
                        orderStatsSubMenu(conn);
                        printMenu(conn);
                        break;

                    case 7:
                        scanner.close();
                        System.exit(0);
                }
            } catch (Exception ex) {
                System.out.println("Please enter an integer value between 1 and 7" + ex);
                scanner.next();
            }
        }
    }

    private void orderStatsSubMenu(Connection conn) throws SQLException {
        System.out.println("        ORDER STATISTICS");
        System.out.println("===============================================");
        System.out.println("1.CUSTOMER WITH HIGHEST ORDER VALUE");
        System.out.println("2.ALL ORDERS SORTED BY DATE");
        System.out.println("3.BIGGEST ORDERS ALL TIME ");
        System.out.println("4.EXIT SUB MENU");
        System.out.println("===============================================");

        int choice = 1;
        while (choice != 5) {
            try {
                choice = promptInt.promptInts("");
                switch (choice) {

                    case 1:
                        String mostValuableCustomers = orderinfo.QueryCustomerHighestOrderValue(conn);
                        System.out.println(mostValuableCustomers);
                        orderStatsSubMenu(conn);
                        continue;

                    case 2:
                        String Sorted = orderinfo.GetOrdersSortedByDate(conn);
                        System.out.println(Sorted);
                        orderStatsSubMenu(conn);
                        continue;

                    case 3:
                        String biggestOrders = orderinfo.QueryBiggestOrders(conn);
                        System.out.println(biggestOrders);
                        orderStatsSubMenu(conn);
                        continue;

                    case 4:
                        printMenu(conn);
                        break;
                }
            } catch (Exception ex) {
                System.out.println("Please enter an int between 1-4");
                scanner.next();
            }
        }
    }
}
