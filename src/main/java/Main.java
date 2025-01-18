import controllers.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repositories.ProductRepository;
import repositories.CustomerRepository;
import repositories.OrderRepository;
import repositories.UserRepository;
import services.ProductService;
import services.CustomerService;
import services.OrderService;
import services.UserService;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Приложение запущено.");

        UserRepository userRepository = new UserRepository("users.txt");
        ProductRepository productRepository = new ProductRepository("products.txt");
        CustomerRepository customerRepository = new CustomerRepository("customers.txt");
        OrderRepository orderRepository = new OrderRepository("orders.txt");

        UserService userService = new UserService(userRepository);
        ProductService productService = new ProductService(productRepository);
        CustomerService customerService = new CustomerService(customerRepository);
        OrderService orderService = new OrderService(orderRepository, customerService, productService);

        UserController userController = new UserController(userService);
        ProductController productController = new ProductController(productService);
        CustomerController customerController = new CustomerController(customerService);
        OrderController orderController = new OrderController(orderService);


        MainController mainController = new MainController(productController, customerController, orderController);

        if (userController.validationMenu()) {
            mainController.run();
        } else {
            logger.info("Выход из приложения.");
        }
    }
}
