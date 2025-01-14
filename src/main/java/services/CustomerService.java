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
     * @param customerModel
     */
    public void addCustomer(CustomerModel customerModel) {
        customerRepository.saveCustomer(customerModel);
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
    public CustomerModel getById(int id) {
        return customerRepository.findById(id);
    }
}
