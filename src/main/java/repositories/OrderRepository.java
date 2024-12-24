package repositories;

import models.OrderModel;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class OrderRepository {
    public static void main(String[] args) {
        String orderTxtName = "orders.txt";
        String orderDirectory = "src/main/java/repositories/";
        String orderPath = orderDirectory + orderTxtName;

        try {
            Path orderFilePath = Path.of(orderPath);
            Files.createFile(orderFilePath);
        } catch (FileAlreadyExistsException e) {
            System.out.println("Этот файл уже существует!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            Path orderFilePath = Path.of(orderPath);
            List<String> orderList = List.of();

            Files.write(orderFilePath, orderList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public OrderModel findOrderID(int orderID) {
        return findOrderID(orderID);
    }

    public void saveAnOrder(OrderModel orderModel) {
        saveAnOrder(orderModel);
    }

    public List<OrderModel> loadAllOrders() {
        return loadAllOrders();
    }
}