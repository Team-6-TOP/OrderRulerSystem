package testing;

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

    @Before
    public void setUp() {
        ProductRepository productRepository = new ProductRepository() {
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
    public void testGetProductById_found() {
        ProductModel foundProduct = productService.getProductById(2);
        assertEquals("Тест2", foundProduct.getName());
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
        List<ProductModel> foundProducts = productService.getAllProducts();
        assertEquals(4, foundProducts.size());
        assertEquals("Тест1", foundProducts.get(0).getName());
        assertEquals("Тест2", foundProducts.get(1).getName());
        assertEquals("Тест3", foundProducts.get(2).getName());
        assertEquals("Тест4", foundProducts.get(3).getName());
    }
}
