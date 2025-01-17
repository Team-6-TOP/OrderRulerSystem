package repositories;

import models.CustomerModel;
import exceptions.CustomerNotFoundException;
import Enums.CustomerType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {
    private static final Logger logger = LoggerFactory.getLogger(CustomerRepository.class);
    private final String customerFile;

    public CustomerRepository(String customerFile) {
        this.customerFile = customerFile;
    }

    /**
     * Сохраняет покупателя в файл
     *
     * @param customer
     */
    public void saveCustomer(CustomerModel customer) {
        try (FileWriter customerFileWriter = new FileWriter(customerFile)) {
            String customerData = customer.getId() + ";"
                    + customer.getName() + ";"
                    + customer.getType() + System.lineSeparator();
            customerFileWriter.write(customerData);
            logger.info("Покупатель сохранен: {}", customer);
        } catch (IOException e) {
            logger.error("Произошла ошибка при сохранении покупателя! {}", e.getMessage());
            throw new RuntimeException("Произошла ошибка при сохранении покупателя! " + e.getMessage());
        }
    }

    /**
     * Показывает всех сохраненных покупателей
     *
     * @return покупателей
     */
    public List<CustomerModel> findAllCustomers() {
        List<CustomerModel> customers = new ArrayList<>();
        try {
            Path customerPath = Paths.get(customerFile);

            if (!Files.exists(customerPath)) {
                Files.createFile(customerPath);
            }

            List<String> customerLines = Files.readAllLines(customerPath);
            for (String customerLine : customerLines) {
                String[] customerParts = customerLine.split(";");
                if (customerParts.length == 3) {
                    int customerID = Integer.parseInt(customerParts[0]);
                    String customerName = customerParts[1];
                    CustomerType customerType = CustomerType.valueOf(customerParts[2]);
                    customers.add(new CustomerModel(customerID, customerName, customerType));
                }
            }
        } catch (IOException e) {
            logger.error("Произошла ошибка при загрузке покупателей! {}", e.getMessage());
            throw new RuntimeException("Произошла ошибка при загрузке покупателей! " + e.getMessage());
        }
        return customers;
    }

    /**
     * Ищет покупателя по ID
     *
     * @param id покупателя
     * @return возвращает найденного по заданному ID покупателя
     */
    public CustomerModel findById(int id) {
        return findAllCustomers().stream()
                .filter(customerModel -> customerModel.getId() == id)
                .findFirst()
                .orElseThrow(() ->
                        new CustomerNotFoundException("Покупатель с ID " + id + " не найден! Попробуйте ещё раз."));
    }
}
