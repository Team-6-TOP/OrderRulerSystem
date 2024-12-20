package repositories;

import exceptions.OrderNotFound;
import models.OrderModel;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderRepository {
    private final String orderPath = "orders.txt";

    public void saveAnOrder(OrderModel order) {
        try {
            BufferedWriter orderWriter = new BufferedWriter(new FileWriter(orderPath, true));
            orderWriter.write(order.getOrderID() + "; " + order.getOrderCustomer() + "; "
                    + order.getOrderProduct());
            orderWriter.newLine();
            orderWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Заказ не найден! Попробуйте ещё раз.");
        }
    }

    public List<OrderModel> loadAllOrders() {
        List<OrderModel> orders = new ArrayList<>();

        try (BufferedReader orderReader = new BufferedReader(new FileReader(orderPath))) {
            String string;
            while ((string = orderReader.readLine()) != null) {
                String[] part = string.split(";");
                int orderID = Integer.parseInt(part[0]);
                String orderProduct = part[1];
                String orderCustomer = part[2];
                orders.add(new OrderModel(orderID, orderProduct, Collections.singletonList(orderCustomer)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return orders;
    }

        public OrderModel findOrderID(int orderID) {
        for (OrderModel order : loadAllOrders()) {
            if (order.getOrderID() == orderID) {
                return order;
            }
        }
        throw new OrderNotFound("Указан неверный ID заказа! Попробуйте ещё раз.");
    }
}
