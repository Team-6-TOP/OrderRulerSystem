package controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class findOrderID {
    public static void main(String[] args) {
        Path filePath = Paths.get("orders.txt");
        Scanner orderIdSc = new Scanner(System.in);
        System.out.print("Введите ID заказа: ");
        String orderIdFinder = orderIdSc.nextLine();

        try {
            List<String> lines = Files.readAllLines(filePath);
            boolean isFound = false;

            for (String line : lines) {
                if (line.contains(orderIdFinder)) {
                    System.out.println("Найдено: " + line);
                    isFound = true;
                }
            }

            if (!isFound) {
                System.out.println("Заказ не найден! Попробуйте ещё раз.");
            }
        } catch (IOException e) {
            System.out.println("Заказ не найден!");
        } finally {
            orderIdSc.close();
        }
    }
}
