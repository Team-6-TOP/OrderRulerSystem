package controllers;

import models.OrderModel;
import services.OrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
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

        System.out.println("Введите ID товаров через запятую (1,2):");
        scanner.nextLine();
        String[] productIdsInput = scanner.nextLine().split(",");
        List<Integer> productIds = new ArrayList<>();

        for (String id : productIdsInput) {
            productIds.add(Integer.parseInt(id.trim()));
        }

        try {
            orderService.addOrder(customerId, productIds);
            System.out.println("Заказ создан.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
