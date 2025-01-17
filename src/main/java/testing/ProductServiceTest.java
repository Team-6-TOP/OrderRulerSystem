package testing;
//для дз

import models.ProductModel;
import repositories.ProductRepository;
import services.ProductService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ProductServiceTest {
    private ProductService productService;

    @Before
    public void setUp() {
        ProductRepository productRepository = new ProductRepository() {
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
