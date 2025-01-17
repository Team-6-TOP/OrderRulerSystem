package services;

import Enums.OrderCategory;
import models.OrderModel;
import repositories.OrderRepository;

import java.util.List;

public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final ProductService productService;

    public OrderService(OrderRepository orderRepository, CustomerService customerService, ProductService productService) {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
        this.productService = productService;
    }

    public void addOrder(int customerId, List<Integer> productIds) {
        if (customerService.getById(customerId) == null) {
            throw new IllegalArgumentException("Покупатель с ID " + customerId + " не найден.");
        }
        for (Integer productId : productIds) {
            if (productService.getProductById(productId) == null) {
                throw new IllegalArgumentException("Продукт с ID " + productId + " не найден.");
            }
        }
        OrderModel order = new OrderModel(orderIdGenerator(), customerId, productIds, OrderCategory.NEW);
        orderRepository.saveAnOrder(order);
    }

    public int orderIdGenerator() {
        return getAllOrders().size() + 1;
    }

    public OrderModel findOrderID(int orderID) {
        return orderRepository.findOrderID(orderID);
    }

    public List<OrderModel> getAllOrders() {
        return orderRepository.loadAllOrders();
    }
}
