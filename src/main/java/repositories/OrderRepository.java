package repositories;

import controllers.OrderController;
import exceptions.OrderNotFound;
import models.Enums.OrderCategory;
import models.OrderModel;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderRepository {
    private static final Logger logger = LoggerFactory.getLogger(OrderRepository.class);
    private final String orderFile = "orders.txt";

    public void saveAnOrder(OrderModel order) {
        try (FileWriter orderFileWriter = new FileWriter(orderFile, true)) {
            String orderData = order.getOrderCategory() + ", "
                    + order.getOrderProduct() + ", "
                    + order.getOrderCustomer() + ", "
                    + order.getOrderID() + ".";
            orderFileWriter.write(orderData);
            logger.info("Заказ сохранён! ");
        } catch (IOException e) {
            logger.error("Произошла ошибка во время сохранения заказа! ");
            throw new RuntimeException("Произошла ошибка при сохранении заказа! " + e.getMessage());
        }
    }

    public List<OrderModel> loadAllOrders() {
        List<OrderModel> orders = new ArrayList<>();

        try {
            Path orderPath = Paths.get(orderFile);

            if (!Files.exists(orderPath)) {
                Files.createFile(orderPath);
            }

            List<String> orderLines = Files.readAllLines(orderPath);
            for (String orderLine : orderLines) {
                String[] orderParts = orderLine.split(";");
                if (orderParts.length == 4) {
                    int orderID = Integer.parseInt(orderParts[0]);
                    String orderProduct = orderParts[1];
                    String orderCustomer = orderParts[2];
                    OrderCategory orderCategory = OrderCategory.valueOf(orderParts[3]);
                    orders.add(new OrderModel(orderID, orderProduct,
                            Collections.singletonList(orderCustomer), orderCategory));
                }
            }
        } catch (IOException e) {
            logger.error("Произошла ошибка во время загрузки заказов! ");
            throw new RuntimeException("Произошла ошибка при загрузке заказов! " + e.getMessage());
        }
        return orders;
    }

    public OrderModel findOrderID(int orderID) {
        return loadAllOrders().stream()
                .filter(orderModel -> orderModel.getOrderID() == orderID)
                .findFirst()
                .orElseThrow(() ->
                        new OrderNotFound("Заказ с ID " + orderID + " не найден! Попробуйте ещё раз."));
    }
}