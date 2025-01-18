package repositories;

import Enums.OrderCategory;
import exceptions.OrderNotFound;
import models.CustomerModel;
import models.OrderModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    private static final Logger logger = LoggerFactory.getLogger(OrderRepository.class);
    private final String orderFile;

    public OrderRepository(String orderFile) {
        this.orderFile = orderFile;
    }

    private final CustomerRepository customerRepository = new CustomerRepository("customers.txt");


    /**
     * Сохраняет ордер в .txt файл
     *
     * @param order
     */

    public void saveAnOrder(OrderModel order) {
        try (FileWriter orderFileWriter = new FileWriter(orderFile, true)) {
            saveFile(orderFileWriter, order);
            logger.info("Заказ сохранён! ID заказа: {}", order.getOrderID());
        } catch (IOException e) {
            logger.error("Произошла ошибка во время сохранения заказа: {}", e.getMessage());
            throw new RuntimeException("Ошибка при сохранении заказа: " + e.getMessage());
        }
    }


    /**
     * Загружает все заказы
     *
     * @return
     */

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
                if (orderParts.length >= 4) {
                    int orderID = Integer.parseInt(orderParts[0]);
                    int customerId = Integer.parseInt(orderParts[1]);
                    OrderCategory orderCategory = OrderCategory.valueOf(orderParts[2]);
                    List<Integer> orderProductIds = new ArrayList<>();
                    String[] productIds = orderParts[3].split(",");
                    for (String productId : productIds) {
                        orderProductIds.add(Integer.parseInt(productId));
                    }
                    CustomerModel customer = customerRepository.findById(customerId);
                    orders.add(new OrderModel(orderID, customer.getId(), orderProductIds, orderCategory));
                }
            }
        } catch (IOException e) {
            logger.error("Произошла ошибка во время загрузки заказов: {}", e.getMessage());
            throw new RuntimeException("Ошибка при загрузке заказов: " + e.getMessage());
        }
        return orders;
    }

    public void updateOrder(OrderModel order) {
        List<OrderModel> orders = loadAllOrders();
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderID() == order.getOrderID()) {
                orders.set(i, order);
                break;
            }
        }
        changeOrders(orders);
    }

    /**
     * Метод для изменений txt файла. Даёт менять статус заказа
     * @param orders
     */

    private void changeOrders(List<OrderModel> orders) {
        try (FileWriter orderFileWriter = new FileWriter(orderFile)) {
            for (OrderModel order : orders) {
                saveFile(orderFileWriter, order);
            }
            logger.info("Заказ перезаписан.");
        } catch (IOException e) {
            logger.error("Произошла ошибка во время сохранения заказов: {}", e.getMessage());
            throw new RuntimeException("Ошибка при сохранении заказов: " + e.getMessage());
        }
    }


    /**
     * Сохраняет файл и позволяет вносить изменения (например статус заказа)
     * @param orderFileWriter
     * @param order
     * @throws IOException
     */

    private void saveFile(FileWriter orderFileWriter, OrderModel order) throws IOException {
        String orderData = order.getOrderID() + ";"
                + order.getOrderCustomer() + ";"
                + order.getOrderCategory() + ";";
        for (Integer productId : order.getProductId()) {
            orderData += productId + ",";
        }
        if (!order.getProductId().isEmpty()) {
            orderData = orderData.substring(0, orderData.length() - 1);
        }
        orderData += System.lineSeparator();

        orderFileWriter.write(orderData);
    }

    /**
     * Метод для поиска заказа по ID
     *
     * @param orderID
     * @return
     */

    public OrderModel findOrderByID(int orderID) {
        return loadAllOrders().stream()
                .filter(orderModel -> orderModel.getOrderID() == orderID)
                .findFirst()
                .orElseThrow(() ->
                        new OrderNotFound("Заказ с ID " + orderID + " не найден! Попробуйте ещё раз."));
    }
}