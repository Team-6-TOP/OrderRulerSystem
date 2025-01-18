package controllers;

import models.UserModel;
import services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public boolean validationMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            logger.info("===== Управление пользователями =====");
            logger.info("1. Регистрация");
            logger.info("2. Вход");
            logger.info("0. Выход");
            logger.info("Выберите действие: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 0) return false;

            switch (choice) {
                case 1 -> registerUser(scanner);
                case 2 -> {
                    if (loginUser(scanner)) {
                        return true;
                    }
                }
                default -> logger.warn("Некорректный выбор. Попробуйте еще раз.");
            }
        }
    }

    private void registerUser(Scanner scanner) {
        logger.info("Введите имя пользователя: ");
        String username = scanner.nextLine();

        if (userService.userExists(username)) {
            logger.warn("Пользователь с таким именем уже существует. Выберите другое имя.");
            return;
        }

        logger.info("Введите пароль: ");
        String password = scanner.nextLine();

        UserModel newUser = userService.addUser(username, password);
        logger.info("Регистрация успешна! Пользователь добавлен: {}", newUser);
    }

    private boolean loginUser(Scanner scanner) {
        logger.info("Введите имя пользователя: ");
        String username = scanner.nextLine();
        logger.info("Введите пароль: ");
        String password = scanner.nextLine();

        if (userService.authenticate(username, password)) {
            logger.info("Вход выполнен успешно!");
            return true;
        } else {
            logger.error("Неверные имя пользователя или пароль.");
            return false;
        }
    }
}