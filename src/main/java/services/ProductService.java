package services;

import models.ProductModel;
import repositories.ProductRepository;

import java.util.List;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(ProductModel product) {
        productRepository.save(product);
    }

    public ProductModel getProductById(int id) {
        return productRepository.findById(id);
    }

    public List<ProductModel> getAllProducts() {
        return productRepository.loadAll();
    }
}
