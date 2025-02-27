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
     *
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
     *
     * @param customerId
     * @param productIds
     */

    public void addOrder(int customerId, List<Integer> productIds) {
        if (customerService.getById(customerId) == null) {
            throw new IllegalArgumentException("Покупатель с ID " + customerId + " не найден.");
        }
        for (Integer productId : productIds) {
            if (productService.getProductId(productId) == null) {
                throw new IllegalArgumentException("Продукт с ID " + productId + " не найден.");
            }
        }

        OrderModel order = new OrderModel(orderIdGenerator(), customerId, productIds, OrderCategory.NEW);
        orderRepository.saveAnOrder(order);
    }

    public void changeOrderStatus(int orderID, OrderCategory newCategory) {
        OrderModel order = findOrderByID(orderID);
        order.setOrderCategory(newCategory);
        orderRepository.updateOrder(order);
    }

    /**
     * Метод для генерации ID
     *
     * @return
     */

    public int orderIdGenerator() {
        return getAllOrders().size() + 1;
    }


    /**
     * Метод для поиска заказа по ID
     *
     * @param orderID
     * @return
     */

    public OrderModel findOrderByID(int orderID) {
        return orderRepository.findOrderByID(orderID);
    }


    /**
     * Метод, загружающий заказы
     *
     * @return
     */

    public List<OrderModel> getAllOrders() {
        return orderRepository.loadAllOrders();
    }
}
