package controllers;


import models.Enums.OrderCategory;

import models.OrderModel;
import models.ProductModel;
import services.CustomerService;
import services.OrderService;
import services.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderController {
    private final OrderService orderService;
    private final CustomerService customerService;
    private final ProductService productService;

    public OrderController(OrderService orderService, CustomerService customerService, ProductService productService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.productService = productService;
    }

    public void showOrderMenu() {
        Scanner orderSc = new Scanner(System.in);
        while (true) {
            System.out.println("===== Управление заказами =====");
            System.out.println("\n1. Создать заказ - ");
            System.out.println("\n2. Показать все заказы - ");
            System.out.println("\n0. Назад. ");
            System.out.println("\nВыберите действие: ");
            int peek = orderSc.nextInt();
            if (peek == 0) break;

            switch (peek) {
                case 1 -> addAnOrder();
                case 2 -> showAllOrders();
                default -> System.out.println("Действие выбрано неверно. Попробуйте ещё раз.");
            }
        }
    }

    private void showAllOrders() {
        List<OrderModel> orders = orderService.getAllOrders();
        orders.forEach(System.out::println);
    }

    private void addAnOrder() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите ID покупателя:");
        int customerId = scanner.nextInt();
        var customer = customerService.getById(customerId);

        System.out.println("Введите ID товаров через запятую (1,2):");
        scanner.nextLine();
        String[] productIdsInput = scanner.nextLine().split(",");
        List<ProductModel> products = new ArrayList<>();

        for (String id : productIdsInput) {
            int productId = Integer.parseInt(id.trim());
            ProductModel product = productService.getProductById(productId);
            products.add(product);
        }


        OrderModel order = new OrderModel(orderIdGenerator(), customer.getName(), products, OrderCategory.NEW);

        orderService.addOrder(order);
        System.out.println("Заказ создан: " + order);
    }

    private int orderIdGenerator() {
        return orderService.getAllOrders().size() + 1;
    }
}
