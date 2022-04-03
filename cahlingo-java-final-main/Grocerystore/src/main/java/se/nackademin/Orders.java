package se.nackademin;

public class Orders {
    private int order_ID;
    private String orderDate;
    private int total;
    private int customer_ID;

    public Orders(String orderDate, int total, int customer_ID) {
        this.orderDate = orderDate;
        this.total = total;
        this.customer_ID = customer_ID;
    }

    public int getCustomer_ID() {
        return customer_ID;
    }

    public void setCustomer_ID(int customer_ID) {
        this.customer_ID = customer_ID;
    }

    @Override
    public String toString() {
        return "\n" + "Customers with biggest order value" + customer_ID + " They bought for a total of: " + total
                + "kr";
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getOrder_ID() {
        return order_ID;
    }

    public void setOrder_ID(int order_ID) {
        this.order_ID = order_ID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
}
