package services;
//Для сдачи дз
import models.ProductModel;
import repositories.ProductRepository;

import java.util.List;

/**
 * Сервис для работы с товарами.
 */
public class ProductService {
    private final ProductRepository productRepository;

    /**
     * Конструктор сервиса.
     *
     * @param productRepository Репозиторий для работы с товарами.
     */
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Добавляет товар.
     *
     * @param product Товар для добавления.
     */
    public void addProduct(ProductModel product) {
        productRepository.save(product);
    }

    /**
     * Получает товар по его ID.
     *
     * @param id Идентификатор товара.
     * @return Найденный товар.
     */
    public ProductModel getProductById(int id) {
        return productRepository.findById(id);
    }

    /**
     * Получает список всех товаров.
     *
     * @return Список всех товаров.
     */
    public List<ProductModel> getAllProducts() {
        return productRepository.loadAll();
    }
}
