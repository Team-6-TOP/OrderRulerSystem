package services;

import models.CustomerModel;
import repositories.CustomerRepository;

import java.util.List;

public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerModel addCustomer(String name) {
        CustomerModel newCustomer = new CustomerModel(null, name, null);
        CustomerModel savedCustomer = customerRepository.save(newCustomer);
        return savedCustomer;
    }
    public List<CustomerModel> getAll () {
        return customerRepository.findAllCustomers();
    }
    public CustomerModel getById () {
        return customerRepository.findById();
    }
}
