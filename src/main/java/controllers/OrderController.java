package controllers;

import Enums.OrderCategory;
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


    /**
     * Конструктор для контроллера
     *
     * @param orderService
     */

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    /**
     * Показывает меню управления заказами
     * Выбираете действие и продолжаете работу
     */

    public void showOrderMenu() {
        Scanner orderSc = new Scanner(System.in);
        while (true) {
            logger.info("===== Управление заказами =====");
            logger.info("1. Создать заказ - ");
            logger.info("2. Показать все заказы - ");
            logger.info("3. Найти заказ по ID - ");
            logger.info("4. Изменить статус заказа - ");
            logger.info("0. Назад. ");
            logger.info("Выберите действие: ");
            int peek = orderSc.nextInt();
            if (peek == 0) break;

            switch (peek) {
                case 1 -> addAnOrder();
                case 2 -> showAllOrders();
                case 3 -> findOrderByID();
                case 4 -> changeOrderStatus();
                default -> logger.warn("Действие выбрано неверно. Попробуйте ещё раз.");
            }
        }
    }


    /**
     * Показывает все заказы
     */

    private void showAllOrders() {
        List<OrderModel> orders = orderService.getAllOrders();
        orders.forEach(orderModel -> logger.info("Заказ: {}", orderModel));
    }


    /**
     * Метод для добавления заказа
     * Вводите ID покупателя, ID товаров и категорию
     * Добавлять покупателя и товар нужно заранее через другие контроллеры
     */

    private void addAnOrder() {
        Scanner scanner = new Scanner(System.in);

        logger.info("Введите ID покупателя:");
        int customerId = scanner.nextInt();

        logger.info("Введите ID товаров через запятую (1,2): ");
        scanner.nextLine();
        String[] productIdsInput = scanner.nextLine().split(",");
        List<Integer> productIds = new ArrayList<>();
        OrderCategory orderCategory = OrderCategory.NEW;
        for (String id : productIdsInput) {
            productIds.add(Integer.parseInt(id.trim()));
        }
        try {
            int orderId = orderService.orderIdGenerator();
            orderService.addOrder(customerId, productIds);
            OrderModel order = new OrderModel(orderId, customerId, productIds, orderCategory);
            logger.info("Заказ создан: {}", order);
        } catch (Exception e) {
            logger.error("Произошла ошибка при создании заказа! Пожалуйста, попробуйте ещё раз.");
        }
    }

    private void changeOrderStatus() {
        logger.info("Введите ID заказа для изменения статуса:");
        Scanner sc = new Scanner(System.in);
        int orderId = sc.nextInt();

        logger.info("Введите новый статус заказа. Выберите что-то из: NEW, PROCESSING, COMPLETED, CANCELLED.");
        String orderCategoryInput = sc.next().toUpperCase();

        if (!OrderCategory.isCorrectCategory(orderCategoryInput)) {
            logger.warn("Неверно выбрана категория! Выберите что-то из: NEW, PROCESSING, COMPLETED, CANCELLED.");
            return;
        }
        OrderCategory newCategory = OrderCategory.valueOf(orderCategoryInput);
        try {
            orderService.changeOrderStatus(orderId, newCategory);
            logger.info("Статус заказа с ID {} изменён на {}", orderId, newCategory);
        } catch (Exception e) {
            logger.error("Ошибка при изменении статуса заказа: {}", e.getMessage());
        }
    }

    /**
     * Метод для поиска заказа по ID
     */

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
