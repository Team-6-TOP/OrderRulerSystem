package controllers;

import models.OrderModel;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import services.OrderService;
import java.util.InputMismatchException;

import services.OrderService;


import java.util.List;
import java.util.Scanner;

public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
    private final services.OrderService orderService;

    public OrderController(services.OrderService orderService) {

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

            orderSc.nextLine();
            try {
                switch (peek) {
                    case 1 -> addAnOrder();
                    case 2 -> showAllOrders();
                    default -> logger.warn("Действие выбрано неверно. Попробуйте ещё раз.");
                }
            } catch (IndexOutOfBoundsException e) {
                logger.error(e.getMessage());
            } catch (InputMismatchException e) {
                logger.warn("Пожалуйста, выберите корректное действие.");
            }
        }
    }

    private void showAllOrders() {
        logger.debug("Загружается список всех заказов...");
        List<OrderModel> orders = orderService.getAllOrders();
        logger.info(orders.toString());
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
