package org.team6;

import controllers.CustomerController;
import repositories.CustomerRepository;
import services.CustomerService;

public class CustomerMain {
    public static void main(String[] args) {

        CustomerRepository customerRepository = new CustomerRepository();
        CustomerService customerService = new CustomerService(customerRepository);
        CustomerController customerController = new CustomerController(customerService);

        customerController.customerMenu();

    }
}
