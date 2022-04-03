package se.nackademin;

import java.util.ArrayList;

public class Texthandler {

    public ArrayList<Customer> createNewCustomer() {
        String firstname = promptInt.promptString("Input Firstname: ");
        String lastname = promptInt.promptString("Input Lastname: ");
        String adress = promptInt.promptString("Input Adress: ");
        double phonenumber = promptInt.promptInts("Input Phone Number");
        Customer customer = new Customer(firstname, lastname, adress, phonenumber);
        ArrayList<Customer> newCustomer = new ArrayList<Customer>();
        newCustomer.add(customer);
        return newCustomer;
    }
}
