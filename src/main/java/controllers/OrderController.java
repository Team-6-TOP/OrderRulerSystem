package controllers;

import models.OrderModel;
import services.OrderService.OrderService;

import java.util.List;
import java.util.Scanner;

public class OrderController {
    private final services.OrderService.OrderService orderService;

    public OrderController(services.OrderService.OrderService orderService) {
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
        Scanner addProductSc = new Scanner(System.in);
        System.out.println("Введите товары, что содержатся в заказе: ");
    }

    private int orderIdGenerator() {
        return orderService.getAllOrders().size() + 1;
    }
}
