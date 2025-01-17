package controllers;

import models.OrderModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.OrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public void showOrderMenu() {
        Scanner orderSc = new Scanner(System.in);
        while (true) {
            logger.info("===== Управление заказами =====");
            logger.info("1. Создать заказ - ");
            logger.info("2. Показать все заказы - ");
            logger.info("3. Найти заказ по ID - ");
            logger.info("0. Назад. ");
            logger.info("Выберите действие: ");
            int peek = orderSc.nextInt();
            if (peek == 0) break;

            switch (peek) {
                case 1 -> addAnOrder();
                case 2 -> showAllOrders();
                case 3 -> findOrderByID();
                default -> logger.warn("Действие выбрано неверно. Попробуйте ещё раз.");
            }
        }
    }

    private void showAllOrders() {
        List<OrderModel> orders = orderService.getAllOrders();
        orders.forEach(orderModel -> logger.info("Заказ: {}", orderModel));
    }

    private void addAnOrder() {
        Scanner scanner = new Scanner(System.in);

        logger.info("Введите ID покупателя:");
        int customerId = scanner.nextInt();

        logger.info("Введите ID товаров через запятую (1,2): ");
        scanner.nextLine();
        String[] productIdsInput = scanner.nextLine().split(",");
        List<Integer> productIds = new ArrayList<>();

        for (String id : productIdsInput) {
            productIds.add(Integer.parseInt(id.trim()));
        }

        try {
            orderService.addOrder(customerId, productIds);
            logger.info("Заказ создан.");
        } catch (Exception e) {
            logger.error("Произошла ошибка при создании заказа! Пожалуйста, попробуйте ещё раз.");
        }
    }

    private void findOrderByID() {
        logger.debug("Поиск заказа по ID...");
        Scanner sc = new Scanner(System.in);
        logger.info("Введите ID заказа: ");
        int id = sc.nextInt();

        try {
            OrderModel order = orderService.findOrderByID(id);
            logger.info("Результаты поиска заказа по ID: {}", order);
        } catch (Exception e) {
            logger.error("Ошибка при поиске заказа! Пожалуйста, попробуйте ещё раз.");
        }
    }
}
