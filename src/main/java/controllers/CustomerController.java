package controllers;

import exceptions.CustomerNotFoundException;
import models.CustomerModel;
import models.Enums.CustomerType;
import services.CustomerService;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CustomerController {
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
                    default -> System.out.println("Данный выбор недоступен");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Пожалуйста, введите корректное число.");
            }
        }
    }

    /**
     * Позволяет добавлять покупателя
     */
    private void addCustomer() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите имя покупателя: ");
        String customerName = sc.nextLine();
        System.out.println("Введите тип покупателя: NEW, REGULAR, VIP");
        String customerTypeChoice = sc.nextLine().toUpperCase();

        if (!CustomerType.isCorrectType(customerTypeChoice)) {
            System.out.println("Неверный тип");
        }
        CustomerModel customer = new CustomerModel(generateCustomerId(), customerName,
                CustomerType.valueOf(customerTypeChoice));
        customerService.addCustomer(customer);
        System.out.println(customer);
    }

    /**
     * Показывает всех покупателей
     */
    private void getAllCustomers() {
        List<CustomerModel> customers = customerService.getAll();
        System.out.println(customers);
    }

    /**
     * Ищет покупателя по ID
     */
    private void getByIDCustomer() {
        Scanner idScan = new Scanner(System.in);
        System.out.println("Введите ID покупателя");
        int id = idScan.nextInt();

        try {
            CustomerModel customer = customerService.getById(id);
            System.out.println(customer);
        } catch (CustomerNotFoundException e) {
            System.out.println(e.getMessage());
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
