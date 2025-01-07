package controllers;
//Для сдачи дз
import models.ProductModel;
import models.ProductCategory;
import services.ProductService;

import java.util.List;
import java.util.Scanner;

/**
 * Контроллер для управления товарами.
 */
public class ProductController {
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
    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("===== Управление товарами =====");
            System.out.println("1. Добавить товар");
            System.out.println("2. Показать все товары");
            System.out.println("3. Найти товар по ID");
            System.out.println("0. Назад");
            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();
            if (choice == 0) break;

            switch (choice) {
                case 1 -> addProduct();
                case 2 -> showAllProducts();
                case 3 -> findProductById();
                default -> System.out.println("Некорректный выбор. Попробуйте еще раз.");
            }
        }
    }

    /**
     * Отображает все товары.
     */
    private void showAllProducts() {
        List<ProductModel> products = productService.getAllProducts();
        products.forEach(System.out::println);
    }

    /**
     * Добавляет новый товар.
     */
    private void addProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите название товара: ");
        String name = scanner.nextLine();
        System.out.print("Введите цену товара: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Введите категорию товара - FOOD, ELECTRONICS, CLOTHING: ");
        String categoryInput = scanner.nextLine().toUpperCase();

        if (!ProductCategory.isValidCategory(categoryInput)) {
            System.out.println("Некорректная категория. Выберите что-то из: FOOD, ELECTRONICS, CLOTHING.");
            return;
        }

        ProductModel product = new ProductModel
                (generateProductId(), name, price, ProductCategory.valueOf(categoryInput));
        productService.addProduct(product);
        System.out.println("Товар добавлен: " + product);
    }

    /**
     * Ищет товар по ID.
     */
    private void findProductById() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID товара для поиска: ");
        int id = scanner.nextInt();

        try {
            ProductModel product = productService.getProductById(id);
            System.out.println("Найденный товар: " + product);
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
