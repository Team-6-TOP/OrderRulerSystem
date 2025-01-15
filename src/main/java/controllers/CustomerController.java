package controllers;

import exceptions.CustomerNotFoundException;
import models.CustomerModel;
import Enums.CustomerType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.CustomerService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CustomerController {
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Показывает меню для управления покупателями
     */
    public void customerMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("===== Выберите опцию =====");
            System.out.println("1.Добавить покупателя");
            System.out.println("2.Показать список всех покупателей");
            System.out.println("3.Поиск покупателя по ID");
            int choice = scanner.nextInt();
            if (choice == 0) {
                break;
            }
            scanner.nextLine();
            try {
                switch (choice) {
                    case 1 -> addCustomer();
                    case 2 -> getAllCustomers();
                    case 3 -> getByIDCustomer();
                    default -> logger.warn("Данный выбор недоступен");
                }
            } catch (IndexOutOfBoundsException e) {
                logger.error(e.getMessage());
            } catch (InputMismatchException e) {
                logger.warn("Пожалуйста, введите корректное число.");
            }
        }
    }

    /**
     * Позволяет добавить покупателя
     */
    private void addCustomer() {
        logger.debug("Происходит добавление покупателя:");
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите имя покупателя: ");
        String customerName = sc.nextLine();
        System.out.println("Введите тип покупателя: NEW, REGULAR, VIP");
        String customerTypeChoice = sc.nextLine().toUpperCase();

        if (!CustomerType.isCorrectType(customerTypeChoice)) {
            logger.error("Неверный тип");
        }
        CustomerModel customer = new CustomerModel(generateCustomerId(), customerName,
                CustomerType.valueOf(customerTypeChoice));
        customerService.addCustomer(customer);
        logger.info(String.valueOf(customer));
    }

    /**
     * Показывает список всех покупателей
     */
    private void getAllCustomers() {
        logger.debug("Производится поиск списка покупателей:");
        List<CustomerModel> customers = customerService.getAll();
        logger.info(customers.toString());
    }

    /**
     * Ищет покупателя по ID
     */
    private void getByIDCustomer() {
        logger.debug("Происходит поиск покупателя по id:");
        Scanner idScan = new Scanner(System.in);
        System.out.println("Введите ID покупателя");
        int id = idScan.nextInt();

        try {
            CustomerModel customer = customerService.getById(id);
            logger.info(String.valueOf(customer));
        } catch (CustomerNotFoundException e) {
            logger.warn(e.getMessage());
        }
    }

    /**
     * Генерирует ID покупателей
     *
     * @return ID покупателя
     */
    private int generateCustomerId() {
        return customerService.getAll().size() + 1;
    }
}
