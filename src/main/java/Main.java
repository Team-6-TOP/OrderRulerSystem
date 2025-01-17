import controllers.MainController;
import controllers.ProductController;
import controllers.CustomerController;
import controllers.OrderController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repositories.ProductRepository;
import repositories.CustomerRepository;
import repositories.OrderRepository;
import services.ProductService;
import services.CustomerService;
import services.OrderService;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Приложение запущено.");

        ProductRepository productRepository = new ProductRepository();
        CustomerRepository customerRepository = new CustomerRepository();
        OrderRepository orderRepository = new OrderRepository();

        ProductService productService = new ProductService(productRepository);
        CustomerService customerService = new CustomerService(customerRepository);
        OrderService orderService = new OrderService(orderRepository, customerService, productService);

        ProductController productController = new ProductController(productService);
        CustomerController customerController = new CustomerController(customerService);
        OrderController orderController = new OrderController(orderService);

        MainController mainController = new MainController(productController, customerController, orderController);

        mainController.run();
    }
}
