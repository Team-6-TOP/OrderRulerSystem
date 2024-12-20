import repositories.ProductRepository;
import controllers.ProductController;
import services.ProductService;


public class Main {
    public static void main(String[] args) {
        ProductRepository productRepository = new ProductRepository();
        ProductService productService = new ProductService(productRepository);
        ProductController productController = new ProductController(productService);
        productController.showMenu();
    }
}