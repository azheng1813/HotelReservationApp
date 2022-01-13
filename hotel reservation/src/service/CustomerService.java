package service;

import model.Customer;

import java.util.Collection;
import java.util.HashSet;

public class CustomerService {
    public static Collection<Customer> customerList = new HashSet<Customer>();

    static CustomerService customerService;

    private CustomerService(){

    }

    public static CustomerService getInstance(){
        if(customerService == null){
            customerService = new CustomerService();
        }
        return customerService;
    }

    public static void addCustomer(String email, String firstName, String lastName){
        Customer customer = new Customer(firstName, lastName, email);
        customerList.add(customer);
    }

    public static Customer getCustomer(String customerEmail){
        for(Customer potentialCustomer : customerList){
            if(potentialCustomer.getEmail().equals(customerEmail));
        }
        return null;
    }

    public Collection<Customer> getAllCustomers(){

        return customerList;
    }
}
