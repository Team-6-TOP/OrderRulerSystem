package testing;

import models.ProductModel;
import Enums.ProductCategory;
import repositories.ProductRepository;
import services.ProductService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Тестовый класс для проверки функциональности сервиса продуктов ProductService.
 */
public class ProductServiceTest {

    /**
     * Экземпляр сервиса продуктов, используемый в тестах.
     */
    private ProductService productService;

    /**
     * Имя файла, используемого для тестов.
     */
    private final String TEST_FILE = "test_products.txt";

    /**
     * Метод, выполняемый перед каждым тестом.
     * Инициализирует ProductService с использованием репозитория, связанного с временным файлом.
     */
    @Before
    public void startUp() {
        ProductRepository productRepository = new ProductRepository(TEST_FILE);
        productService = new ProductService(productRepository);
    }

    /**
     * Метод, выполняемый после каждого теста.
     * Удаляет временный файл, чтобы очистить данные теста.
     *
     * @throws Exception если возникает ошибка при удалении файла.
     */
    @After
    public void clearTestData() throws Exception {
        Path path = Paths.get(TEST_FILE);
        Files.deleteIfExists(path);
    }

    /**
     * Тестирует добавление нескольких продуктов в сервис.
     * Проверяет, что добавленные продукты правильно сохраняются и могут быть получены.
     */
    @Test
    public void TestAddProduct() {
        ProductModel test1 = new ProductModel(1, "Тест1", 999.00, ProductCategory.FOOD);
        ProductModel test11 = new ProductModel(2, "Тест2", 99.00, ProductCategory.FOOD);
        productService.addProduct(test1);
        productService.addProduct(test11);

        List<ProductModel> products = productService.getAllProducts();
        assertEquals(2, products.size());
        assertEquals(test1, products.get(0));
        assertEquals(test11, products.get(1));
    }

    /**
     * Тестирует получение продукта по его ID.
     * Проверяет, что продукт, полученный по ID, совпадает с добавленным.
     */
    @Test
    public void testGetProductId() {
        ProductModel test2 = new ProductModel(1, "Тест1", 9.00, ProductCategory.FOOD);
        ProductModel test22 = new ProductModel(2, "Тест2", 99.00, ProductCategory.CLOTHING);
        ProductModel test222 = new ProductModel(3, "Тест3", 999.00, ProductCategory.ELECTRONICS);
        productService.addProduct(test2);
        productService.addProduct(test22);
        productService.addProduct(test222);

        ProductModel productId = productService.getProductById(2);
        assertEquals(test22, productId);
    }

    /**
     * Тестирует сценарий, когда продукт не найден по заданному ID.
     * Проверяет, что выбрасывается правильное исключение с ожидаемым сообщением.
     */
    @Test
    public void testProductNotFound() {
        try {
            productService.getProductById(55);
            fail("Ожидалось исключение при поиске несуществующего товара.");
        } catch (RuntimeException e) {
            String trueLie = "Товар с ID: 55 не найден.";
            assertTrue(e.getMessage().contains(trueLie));
        }
    }

    /**
     * Тестирует загрузку всех продуктов после добавления нескольких.
     * Проверяет, что количество загруженных продуктов соответствует количеству добавленных.
     */
    @Test
    public void testLoadAllProductsAfterAdding() {
        ProductModel test4 = new ProductModel(1, "Тест1", 44.00, ProductCategory.CLOTHING);
        ProductModel test44 = new ProductModel(2, "Тест2", 22.00, ProductCategory.FOOD);
        ProductModel test444 = new ProductModel(2, "Тест2", 22.00, ProductCategory.ELECTRONICS);
        ProductModel test4444 = new ProductModel(2, "Тест2", 22.00, ProductCategory.FOOD);

        productService.addProduct(test4);
        productService.addProduct(test44);
        productService.addProduct(test444);
        productService.addProduct(test4444);

        List<ProductModel> products = productService.getAllProducts();
        assertEquals(4, products.size());
        assertTrue(products.contains(test4));
        assertTrue(products.contains(test44));
        assertTrue(products.contains(test444));
        assertTrue(products.contains(test4444));
    }
}
