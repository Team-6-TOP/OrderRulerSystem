package controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class MainController {
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    private final ProductController productController;

    public MainController(ProductController productController) {
        this.productController = productController;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            logger.info("===== Главное меню =====");
            logger.info("1. Управление товарами");
            logger.info("2. Управление покупателями");
            logger.info("3. Управление заказами");
            logger.info("0. Выйти");
            logger.info("Выберите действие: ");
            int choice = scanner.nextInt();
            if (choice == 0) {
                logger.info("Выход из приложения.");
                break;
            }
            switch (choice) {
                case 1 -> {
                    logger.info("Переход к меню управления товарами.");
                    productController.showMenu();
                }
                case 2 -> logger.warn("в разработке.");
                case 3 -> logger.warn("в разработке 2х.");
                default -> logger.error("Некорректный выбор. Попробуйте еще раз.");
            }
        }
        scanner.close();
    }
}