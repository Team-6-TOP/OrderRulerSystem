import controllers.MainController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repositories.ProductRepository;
import controllers.ProductController;
import services.ProductService;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Приложение запущено.");
        ProductRepository productRepository = new ProductRepository();
        ProductService productService = new ProductService(productRepository);
        ProductController productController = new ProductController(productService);
        MainController mainController = new MainController(productController);
        mainController.run();
    }
}
