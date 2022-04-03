package se.nackademin;

import java.util.List;

public class Product {
    private int productId;
    private String foodName;
    private int quantity;
    private int price;
    private int totalPrice;
    public int totalpricer;

    public Product(String foodName, int price, int foodId) {
        this.foodName = foodName;
        this.quantity = 0;
        this.price = price;
        this.productId = foodId;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getproductId() {
        return productId;
    }

    public void setproductId(int foodId) {
        this.productId = foodId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product() {
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    @Override
    public String toString() {
        return "Product ID: " + productId + " Grocery name: " + foodName + " Price: " + price + "kr";
    }

    public int SaveTotal(List<Product> updatedCart) {
        int sum = 0;
        totalpricer = 0;
        for (Product InCart : updatedCart) {
            sum = calculator(InCart.price, InCart.quantity);
            totalpricer += sum;
        }
        return totalpricer;
    }

    public void printCart(List<Product> updatedCart) {
        int sum = 0;
        totalpricer = 0;
        System.out.println("\nProducts in Cart: ");
        SaveTotal(updatedCart);
        for (Product InCart : updatedCart) {
            sum = calculator(InCart.price, InCart.quantity);
            System.out.print(
                    "\n" + InCart + " Quantity: " + InCart.getQuantity() + "\nPrice for products: " + sum + "kr ");
        }
        System.out.println("\nTotal In Cart: " + totalpricer + "kr");

    }

    public int calculator(int x, int y) {
        return x * y;
    }

}
