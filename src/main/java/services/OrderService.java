package services;

import Enums.OrderCategory;
import models.OrderModel;
import repositories.OrderRepository;

import java.util.List;

public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final ProductService productService;


    /**
     * Конструктор сервиса
     * @param orderRepository
     * @param customerService
     * @param productService
     */

    public OrderService(OrderRepository orderRepository, CustomerService customerService, ProductService productService) {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
        this.productService = productService;
    }

    /**
     * Метод добавления заказа
     * @param customerId
     * @param productIds
     */

    public void addOrder(int customerId, List<Integer> productIds) {
        OrderModel order = new OrderModel(orderIdGenerator(), customerId, productIds, OrderCategory.NEW);
        orderRepository.saveAnOrder(order);
    }


    /**
     * Метод для генерации ID
     * @return
     */

    public int orderIdGenerator() {
        return getAllOrders().size() + 1;
    }


    /**
     * Метод для поиска заказа по ID
     * @param orderID
     * @return
     */

    public OrderModel findOrderByID(int orderID) {
        return orderRepository.findOrderByID(orderID);
    }


    /**
     * Метод, загружающий заказы
     * @return
     */

    public List<OrderModel> getAllOrders() {
        return orderRepository.loadAllOrders();
    }
}
