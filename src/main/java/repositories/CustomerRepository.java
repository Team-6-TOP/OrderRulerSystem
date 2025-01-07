package repositories;

import models.CustomerModel;
import exceptions.CustomerNotFoundException;
import models.Enums.CustomerType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerRepository {
    private final List<CustomerModel> customers;
    private int countID;
    Scanner scanner = new Scanner(System.in);

    public CustomerRepository() {
        this.customers = new ArrayList<>();
        this.countID = 0;
    }

    public CustomerModel save(CustomerModel customer) {
        customer.setId(++countID);
        System.out.println("Выберите тип покупателя: \n1 - новый покупатель, \n2 - постоянный покупатель, " +
                "\n3 - VIP-клиент");

        CustomerType type1 = CustomerType.NEW;
        CustomerType type2 = CustomerType.REGULAR;
        CustomerType type3 = CustomerType.VIP;

        String customerType = scanner.nextLine();

        customerType = switch (customerType) {
            case "1" -> CustomerType.NEW.getType();
            case "2" -> CustomerType.REGULAR.getType();
            case "3" -> CustomerType.VIP.getType();
            default -> "Такого типа покупателя не существует";
        };
        customer.setType(customerType);

        if (customers.add(customer)) {
            return customer;
        }
        return null;

    }

    public List<CustomerModel> findAllCustomers() {
        return customers;
    }

    public CustomerModel findById(Integer id) {
        if (id <= 0 || id > customers.size()) {
            throw new CustomerNotFoundException("Такой покупатель не найден с ID: " + id);
        }
        return customers.get(id - 1);
    }
}
