package testing;

import models.ProductModel;
import org.junit.Before;
import org.junit.Test;
import repositories.ProductRepository;
import services.ProductService;

import java.util.ArrayList;
import java.util.List;

import static Enums.ProductCategory.FOOD;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    private ProductService productService;
    private ProductRepository productRepository;

    @Before
    public void setUp() {
        productRepository = mock(ProductRepository.class);
        productService = new ProductService(productRepository);
    }

    @Test
    public void testAddProduct() {
        ProductModel product = new ProductModel(1, "test", 22222.0, FOOD);
        productService.addProduct(product);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void testGetProductById() {
        int productId = 1;
        ProductModel mockProduct = new ProductModel(1, "test", 22222.0, FOOD);
        when(productRepository.findById(productId)).thenReturn(mockProduct);
        ProductModel foundProduct = productService.getProductById(productId);
        verify(productRepository, times(1)).findById(productId);
        assertEquals(mockProduct, foundProduct);
    }

    @Test
    public void testGetAllProducts() {
        List<ProductModel> mockProducts = new ArrayList<>();
        when(productRepository.loadAll()).thenReturn(mockProducts);
        List<ProductModel> products = productService.getAllProducts();
        verify(productRepository, times(1)).loadAll();
        assertEquals(mockProducts, products);
    }
}
