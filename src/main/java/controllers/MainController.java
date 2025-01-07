package controllers;

import java.util.Scanner;

public class MainController {
    private final ProductController productController;

    public MainController(ProductController productController) {
        this.productController = productController;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("===== Главное меню =====");
            System.out.println("1. Управление товарами");
            System.out.println("2. Управление покупателями");
            System.out.println("3. Управление заказами");
            System.out.println("0. Выйти");
            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();
            if (choice == 0) break;
            switch (choice) {
                case 1 -> productController.showMenu();
                case 2 -> System.out.println("В разработке");
                case 3 -> System.out.println("В разработке х2");
                default -> System.out.println("Некорректный выбор. Попробуйте еще раз.");
            }
        }
    }
}
