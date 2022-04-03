package se.nackademin;

public class Customer {
    private String firstName;
    private String lastName;
    private String adress;
    private double phoneNumber;
    private int customerID;

    public Customer(String firstName, String lastName, String adress, double phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.adress = adress;
        this.phoneNumber = phoneNumber;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public double getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "CustID: " + customerID + " Customer Name : " + firstName;
    }
}
