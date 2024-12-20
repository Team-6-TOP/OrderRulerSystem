package controllers;

import exceptions.CustomerNotFoundException;
import models.CustomerModel;
import services.CustomerService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CustomerController {
    private final CustomerService customerService;
    Scanner scanner = new Scanner(System.in);
    String customerName;
    Integer idNumber;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void customerMenu() {
        while (true) {
            System.out.println("Выберите опцию: ");
            System.out.println("1.Добавить покупателя");
            System.out.println("2.Показать список всех покупателей");
            System.out.println("3.Поиск покупателя по ID");
            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1 -> addCustomer();
                    case 2 -> getAllCustomers();
                    case 3 -> getByIDCustomer();
                    default -> System.out.println("Invalid choice");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Пожалуйста, введите корректное число.");
            }
        }
    }

    private void addCustomer() {
        System.out.println("Введите имя покупателя: ");
        customerName = scanner.nextLine();
        String view = customerService.addCustomer(customerName).toString();
        System.out.println(view);
    }

    private void getAllCustomers() {
        String list = customerService.getAll().toString();
        System.out.println(list);
    }

    private void getByIDCustomer() {
        try {
            System.out.println("Введите ID покупателя");
            idNumber = scanner.nextInt();
            CustomerModel customer = customerService.getById(idNumber);
            System.out.println(customer);
        } catch (CustomerNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
