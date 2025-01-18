package repositories;

import models.ProductModel;
import Enums.ProductCategory;
import exceptions.ProductNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Репозиторий для работы с данными товаров.
 */
public class ProductRepository {
    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);
    private final String fileName;

    /**
     * Конструктор, принимающий имя файла для работы с продуктами.
     *
     * @param fileName Имя файла для сохранения продуктов.
     */
    public ProductRepository(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Сохраняет товар в файл.
     *
     * @param product Товар для сохранения.
     */
    public void save(ProductModel product) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            String productData = product.getId() + ";" +
                    product.getName() + ";" +
                    product.getPrice() + ";" +
                    product.getCategory().name() + System.lineSeparator();
            writer.write(productData);
            logger.debug("Товар сохранен: {}", product);
        } catch (IOException e) {
            logger.error("Ошибка при сохранении продукта: {}", e.getMessage());
        }
    }

    /**
     * Загружает все товары из файла.
     *
     * @return Список всех товаров.
     */
    public List<ProductModel> loadAll() {
        List<ProductModel> products = new ArrayList<>();
        try {
            Path path = Paths.get(fileName);
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                String[] parts = line.split(";");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    double price = Double.parseDouble(parts[2]);
                    ProductCategory category = ProductCategory.valueOf(parts[3]);
                    products.add(new ProductModel(id, name, price, category));
                }
            }
            logger.debug("Загружено продуктов: {}", products.size());
        } catch (IOException e) {
            logger.error("Ошибка при загрузке продуктов: {}", e.getMessage());
        }
        return products;
    }

    /**
     * Находит товар по его ID.
     *
     * @param id Идентификатор товара.
     * @return Найденный товар.
     */
    public ProductModel findId(int id) {
        logger.debug("Поиск товара по ID: {}", id);
        return loadAll().stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Товар с ID: " + id + " не найден."));
    }
}
