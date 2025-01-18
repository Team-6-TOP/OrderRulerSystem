package testing;

import Enums.OrderCategory;
import models.OrderModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import repositories.OrderRepository;
import services.OrderService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class OrderServiceTest {
    private OrderService orderService;
    private final String TEST_FILE = "test_order.txt";

    @Before
    public void startUp() {
        OrderRepository orderRepository = new OrderRepository(TEST_FILE);
    }

    @After
    public void clearTestData() throws Exception {
        Path path = Paths.get(TEST_FILE);
        Files.deleteIfExists(path);
    }

    @Test
    public void addOrder() {
    }

    @Test
    public void changeOrderStatus() {
    }

    @Test
    public void orderIdGenerator() {
    }

    @Test
    public void findOrderByID() {
    }

    @Test
    public void getAllOrders() {
    }
}