package services;

import models.OrderModel;
import repositories.OrderRepository;

import java.util.List;

public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void addOrder(OrderModel orderModel) {
        orderRepository.saveAnOrder(orderModel);
    }

    public OrderModel findOrderID(int orderID) {
        return orderRepository.findOrderID(orderID);
    }

    public List<OrderModel> getAllOrders() {
        return orderRepository.loadAllOrders();
    }
}
