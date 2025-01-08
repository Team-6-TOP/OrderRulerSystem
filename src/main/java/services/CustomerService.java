package services;

import models.CustomerModel;
import repositories.CustomerRepository;

import java.util.List;

public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Добавляет покупателя
     * @param name
     * @return добавленного покупателя
     */
    public CustomerModel addCustomer(String name) {
        CustomerModel newCustomer = new CustomerModel(null, name, null);
        CustomerModel savedCustomer = customerRepository.save(newCustomer);
        return savedCustomer;
    }

    /**
     * Показывает список всех покупателей
     * @return всех покупателей
     */
    public List<CustomerModel> getAll() {
        return customerRepository.findAllCustomers();
    }

    /**
     * Ищет покупателя по ID
      * @param id
     * @return найденного по ID покупателя
     */
    public CustomerModel getById(Integer id) {
        return customerRepository.findById(id);
    }
}
