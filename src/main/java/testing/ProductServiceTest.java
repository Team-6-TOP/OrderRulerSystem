package testing;

import Enums.ProductCategory;
import models.ProductModel;
import repositories.ProductRepository;
import services.ProductService;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class ProductServiceTest {
    private static final String FILE = "bespoleznovoe.txt";
    private ProductService productService;
    private ProductRepository productRepository;

    @Before
    public void setUp() {
        productRepository = new ProductRepository() {
            @Override
            public void save(ProductModel product) {
                try (FileWriter writer = new FileWriter(FILE, true)) {
                    String productData = product.getId() + ";" +
                            product.getName() + ";" +
                            product.getPrice() + ";" +
                            product.getCategory().name() + System.lineSeparator();
                    writer.write(productData);
                } catch (IOException e) {
                    throw new RuntimeException("Ошибка при сохранении продукта: " + e.getMessage());
                }
            }

            @Override
            public List<ProductModel> loadAll() {
                return super.loadAll();
            }

            @Override
            public ProductModel findById(int id) {
                return super.findById(id);
            }
        };

        productService = new ProductService(productRepository);
        clearTestFile();
    }

    @After
    public void tearDown() {
        deleteTestFile();
    }

    private void clearTestFile() {
        try (FileWriter writer = new FileWriter(FILE, false)) {
            writer.write("");
        } catch (IOException e) {
            fail("Не удалось очистить тестовый файл: " + e.getMessage());
        }
    }

    private void deleteTestFile() {
        File file = new File(FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testAddProduct() {
        ProductModel product1 = new ProductModel(1, "Тест1", 999.0, ProductCategory.FOOD);

        productService.addProduct(product1);

        List<ProductModel> products = productService.getAllProducts();
        assertEquals(4, products.size());
        assertEquals(product1.getName(), products.get(0).getName());
    }

    @Test
    public void testGetProductById_found() {
        ProductModel product2 = new ProductModel(2, "Тест2", 545.0, ProductCategory.ELECTRONICS);
        productService.addProduct(product2);

        ProductModel foundProduct = productService.getProductById(2);

        assertEquals(product2.getName(), foundProduct.getName());
    }

    @Test
    public void testGetProductById_notFound() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            productService.getProductById(55);
        });
        assertEquals("Ошибка при поиске продукта: Товар с ID: 55 не найден.", exception.getMessage());
    }

    @Test
    public void testGetAllProducts() {
        ProductModel product3 = new ProductModel(3, "Тест3", 12.0, ProductCategory.ELECTRONICS);
        ProductModel product4 = new ProductModel(4, "Тест4", 444.0, ProductCategory.CLOTHING);

        productService.addProduct(product3);
        productService.addProduct(product4);

        List<ProductModel> foundProducts = productService.getAllProducts();

        assertEquals(4, foundProducts.size());
        assertEquals("Тест3", foundProducts.get(2).getName());
        assertEquals("Тест4", foundProducts.get(3).getName());
    }
}
