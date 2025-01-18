package controllers;

import models.ProductModel;
import Enums.ProductCategory;
import services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;

/**
 * Контроллер для управления товарами.
 */
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    /**
     * Конструктор контроллера товаров.
     *
     * @param productService Сервис для работы с товарами.
     */
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Отображает меню управления товарами.
     */
    public void showProductMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            logger.info("===== Управление товарами =====");
            logger.info("1. Добавить товар");
            logger.info("2. Показать все товары");
            logger.info("3. Найти товар по ID");
            logger.info("0. Назад");
            logger.info("Выберите действие: ");
            int choice = scanner.nextInt();
            if (choice == 0) break;

            switch (choice) {
                case 1 -> addProduct();
                case 2 -> showAllProducts();
                case 3 -> findProductId();
                default -> logger.warn("Некорректный выбор. Попробуйте еще раз.");
            }
        }
    }

    /**
     * Отображает все товары.
     */
    private void showAllProducts() {
        List<ProductModel> products = productService.getAllProducts();
        products.forEach(product -> logger.info("Товар: {}", product));
    }

    /**
     * Добавляет новый товар.
     */
    private void addProduct() {
        Scanner scanner = new Scanner(System.in);
        logger.info("Введите название товара: ");
        String name = scanner.nextLine();
        logger.info("Введите цену товара: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        logger.info("Введите категорию товара - FOOD, ELECTRONICS, CLOTHING: ");
        String categoryInput = scanner.nextLine().toUpperCase();

        if (!ProductCategory.isValidCategory(categoryInput)) {
            logger.warn("Некорректная категория. Выберите что-то из: FOOD, ELECTRONICS, CLOTHING.");
            return;
        }

        ProductModel product = new ProductModel(generateProductId(), name, price, ProductCategory.valueOf(categoryInput));
        productService.addProduct(product);
        logger.debug("Товар добавлен: {}", product);
    }

    /**
     * Ищет товар по ID.
     */
    private void findProductId() {
        Scanner scanner = new Scanner(System.in);
        logger.info("Введите ID товара для поиска: ");
        int id = scanner.nextInt();
        logger.debug("Поиск товара с ID: {}", id);

        try {
            ProductModel product = productService.getProductId(id);
            logger.info("Найденный товар: {}", product);
        } catch (Exception e) {
            logger.error("Ошибка при поиске товара: {}", e.getMessage());
        }
    }

    /**
     * Генерирует уникальный ID для нового товара.
     *
     * @return Уникальный ID товара.
     */
    private int generateProductId() {
        return productService.getAllProducts().size() + 1;
    }

}