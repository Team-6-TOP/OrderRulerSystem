package controllers;

import models.ProductModel;
import services.ProductService;

import java.util.List;
import java.util.Scanner;

public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("===== Управление товарами =====");
            System.out.println("1. Добавить товар");
            System.out.println("2. Показать все товары");
            System.out.println("0. Назад");
            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();
            if (choice == 0) break;

            switch (choice) {
                case 1 -> addProduct();
                case 2 -> showAllProducts();
                default -> System.out.println("Некорректный выбор. Попробуйте еще раз.");
            }
        }
    }

    private void showAllProducts() {
        List<ProductModel> products = productService.getAllProducts();
        products.forEach(System.out::println);
    }

    private void addProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите название товара: ");
        String name = scanner.nextLine();
        System.out.print("Введите цену товара: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Введите категорию товара: FOOD, ELECTRONICS, CLOTHING. ");
        String category = scanner.nextLine().toUpperCase();
        if (!isValidCategory(category)) {
            System.out.println("Некорректная категория. Выберите что-то из: FOOD, ELECTRONICS, CLOTHING.");
            return;
        }
        ProductModel product = new ProductModel(generateProductId(), name, price, category);
        productService.addProduct(product);
        System.out.println("Товар добавлен: " + product);
    }

    private int generateProductId() {
        return productService.getAllProducts().size() + 1;
    }

    private boolean isValidCategory(String category) {
        return category.equals("FOOD") || category.equals("ELECTRONICS") || category.equals("CLOTHING");
    }
}
