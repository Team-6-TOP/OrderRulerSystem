package controllers;

import exceptions.OrderNotFound;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class findOrderID {
    private static final Logger logger = LoggerFactory.getLogger(findOrderID.class);

    public static void main(String[] args) {
        logger.debug("Происходит поиск заказа по ID...");
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
        } catch (OrderNotFound e) {
            logger.warn(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
